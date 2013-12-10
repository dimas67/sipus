/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class PanelCard extends JPanel {

    private String title;

    public PanelCard() {
        setLayout(new GridBagLayout());
        setTitle("");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setPaint(new GradientPaint(0, 0, Color.white, 0, 20, Color.blue.brighter().brighter()));
        gd.fillRect(0, 0, getWidth(), 20);
        gd.setFont(getFont().deriveFont(Font.BOLD));
        gd.setColor(Color.white);
        gd.drawString("• " + getTitle(), 10, 15);
        gd.dispose();
    }
}
