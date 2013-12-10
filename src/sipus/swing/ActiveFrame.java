/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.swing;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Administrator
 */
public class ActiveFrame {

    private JInternalFrame jif;
    private JDesktopPane desktopPane;

    public ActiveFrame(JInternalFrame jif) {
        this.jif = jif;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public JInternalFrame getJInternalFrame() {
        return this.jif;
    }

    @Override
    public String toString() {
        return this.jif.getTitle();
    }
}
