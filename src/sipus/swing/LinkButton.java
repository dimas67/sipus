/*
 * Author      : Dimas Agil Tejo Baskoro
 * E-mail      : dimas.agil.67@gmail.com
 * Web-Blog    : http://tanahdjawa.wordpress.com
 * Comment     : Anda boleh menggunakan/ mengembangkan kode ini (opensource),
 *               tapi sesuai kode etik, Anda dilarang mengganti nama author
 *               dan selalu menyertakan Original Author.
 */
package sipus.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author dimas
 */
public class LinkButton extends JButton {

    public LinkButton() {
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.black);
            }
        });
    }

    @Override
    public void setText(String text) {
        super.setText("<html><u>" + text + "</u></html>");
    }
}
