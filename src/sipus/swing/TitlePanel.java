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
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class TitlePanel extends JPanel {

    private final String JUDUL = "Sistem Informasi Perpustakaan";

    public TitlePanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setPaint(new GradientPaint(0, 0, Color.blue.brighter().brighter(), 0, getHeight(), Color.white));
        gd.fillRect(0, 0, getWidth(), getHeight());
        gd.setFont(getFont().deriveFont(Font.BOLD, 26F));
        gd.setColor(Color.black);
        gd.drawString(JUDUL, 12, 45);
        gd.setColor(Color.white);
        gd.drawString(JUDUL, 10, 43);
        gd.dispose();
    }
}
