/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Administrator
 */
public class VerticalButtonUI extends BasicButtonUI {

    protected int angle;

    public VerticalButtonUI(int angle) {
        super();
        this.angle = angle;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension dim = super.getPreferredSize(c);
        return new Dimension(dim.height, dim.width);
    }
    private static Rectangle paintIconR = new Rectangle();
    private static Rectangle paintTextR = new Rectangle();
    private static Rectangle paintViewR = new Rectangle();
    private static Insets paintViewInsets = new Insets(0, 0, 0, 0);

    @Override
    public void paint(Graphics g, JComponent c) {
        JToggleButton button = (JToggleButton) c;
        String text = button.getText();
        Icon icon = (button.isEnabled()) ? button.getIcon() : button.getDisabledIcon();

        if ((icon == null) && (text == null)) {
            return;
        }

        FontMetrics fm = g.getFontMetrics();
        paintViewInsets = c.getInsets(paintViewInsets);

        paintViewR.x = paintViewInsets.left;
        paintViewR.y = paintViewInsets.top;

        // Use inverted height &amp; width
        paintViewR.height = c.getWidth() - (paintViewInsets.left + paintViewInsets.right);
        paintViewR.width = c.getHeight() - (paintViewInsets.top + paintViewInsets.bottom);

        paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
        paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Tahomoa", Font.PLAIN, 11));
        AffineTransform tr = g2.getTransform();

        if (angle == 90) {
            g2.rotate(Math.PI / 2);
            g2.translate(0, -c.getWidth());
            paintViewR.x = c.getHeight() / 2 - (int) fm.getStringBounds(text, g).getWidth() / 2;
            paintViewR.y = c.getWidth() / 2 - (int) fm.getStringBounds(text, g).getHeight() / 2;
        } else if (angle == 270) {
            g2.rotate(-Math.PI / 2);
            g2.translate(-c.getHeight(), 0);
            paintViewR.x = c.getHeight() / 2 - (int) fm.getStringBounds(text, g).getWidth() / 2;
            paintViewR.y = c.getWidth() / 2 - (int) fm.getStringBounds(text, g).getHeight() / 2;
        }

        if (icon != null) {
            icon.paintIcon(c, g, paintIconR.x, paintIconR.y + 3);
        }

        if (text != null) {
            int textX = paintTextR.x;
            int textY = paintTextR.y + fm.getAscent();

            if (button.isEnabled()) {
                paintText(g, c, new Rectangle(paintViewR.x + 10, paintViewR.y - 2, textX, textY), text);
            } else {
                paintText(g, c, new Rectangle(paintViewR.x + 10, paintViewR.y - 2, textX, textY), text);
            }
        }

        g2.setTransform(tr);
    }
}