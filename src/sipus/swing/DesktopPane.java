/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author Administrator
 */
public class DesktopPane extends JDesktopPane {

    private Image image;

    public DesktopPane() {
        image = new ImageIcon(getClass().getResource("/sipus/icon/background.JPG")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        gd.dispose();
    }
}
