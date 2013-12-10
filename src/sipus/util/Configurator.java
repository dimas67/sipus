/*
 * $Id: Configurator.java,v 1.3 2005/10/10 18:01:35 rbair Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package sipus.util;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.TextAction;

/**
 * This class contains only static utility methods that can be used to set up
 * automatic completion for some Swing components.
 * <p>Usage examples:</p>
 * <p><code>
 * JComboBox comboBox = [...];<br/>
 * Configurator.<b>enableAutoCompletion</b>(comboBox);<br/>
 * &nbsp;<br/>
 * JList list = [...];<br/>
 * JTextField textField = [...];<br/>
 * Configurator.<b>enableAutoCompletion</b>(list, textField);
 * </p></code>
 *
 * @author Thomas Bierhance
 */
public class Configurator {

    /**
     * Enables automatic completion for the given JTextComponent based on the
     * items contained in the given JList. The two components will be
     * synchronized. The automatic completion will always be strict.
     *
     * @param table a list
     * @param textComponent the text component that will be used for automatic
     * completion.
     */
     public static void enableAutoCompletion(JList list, JTextComponent textComponent) {
        AbstractComponentAdaptor adaptor = new ListAdaptor(list, textComponent);
        Document document = new Document(adaptor, true);
        configureTextComponent(textComponent, document, adaptor);
    }

     public static void enableAutoCompletionTable(JTable table, JTextComponent textComponent) {
        AbstractComponentAdaptor adaptor = new ListAdaptor(table, textComponent);
        Document document = new Document(adaptor, true);
        configureTextComponent(textComponent, document, adaptor);
    }

    /**
     * Configures a given text component for automatic completion using the
     * given Document and AbstractComponentAdaptor.
     * @param textComponent a text component that should be configured
     * @param document the Document to be installed on the text component
     * @param adaptor the AbstractComponentAdaptor to be used
     */
    public static void configureTextComponent(JTextComponent textComponent, Document document, final AbstractComponentAdaptor adaptor) {
        // install the document on the text component
        textComponent.setDocument(document);

        // mark entire text when the text component gains focus
        // otherwise the last mark would have been retained which is quiet confusing
        textComponent.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextComponent textComponent = (JTextComponent) e.getSource();
                adaptor.markEntireText();
            }
        });

        // Tweak some key bindings
        InputMap editorInputMap = textComponent.getInputMap();
        if (document.isStrictMatching()) {
            // move the selection to the left on VK_BACK_SPACE
            editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), DefaultEditorKit.selectionBackwardAction);
            // ignore VK_DELETE and CTRL+VK_X and beep instead when strict matching
            editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0), errorFeedbackAction);
            editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK), errorFeedbackAction);
        } else {
            ActionMap editorActionMap = textComponent.getActionMap();
            // leave VK_DELETE and CTRL+VK_X as is
            // VK_BACKSPACE will move the selection to the left if the selected item is in the list
            // it will delete the previous character otherwise
            editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), "nonstrict-backspace");
            editorActionMap.put("nonstrict-backspace", new NonStrictBackspaceAction(
                    editorActionMap.get(DefaultEditorKit.deletePrevCharAction),
                    editorActionMap.get(DefaultEditorKit.selectionBackwardAction),
                    adaptor));
        }
    }

    static class NonStrictBackspaceAction extends TextAction {
        Action backspace;
        Action selectionBackward;
        AbstractComponentAdaptor adaptor;

        public NonStrictBackspaceAction(Action backspace, Action selectionBackward, AbstractComponentAdaptor adaptor) {
            super("nonstrict-backspace");
            this.backspace = backspace;
            this.selectionBackward = selectionBackward;
            this.adaptor = adaptor;
        }

        public void actionPerformed(ActionEvent e) {
            if (adaptor.listContainsSelectedItem()) {
                selectionBackward.actionPerformed(e);
            } else {
                backspace.actionPerformed(e);
            }
        }
    }

    /**
     * A TextAction that provides an error feedback for the text component that invoked
     * the action. The error feedback is most likely a "beep".
     */
    static Object errorFeedbackAction = new TextAction("provide-error-feedback") {
        public void actionPerformed(ActionEvent e) {
            UIManager.getLookAndFeel().provideErrorFeedback(getTextComponent(e));
        }
    };
}