/*
 * Author      : Dimas Agil Tejo Baskoro
 * E-mail      : dimas.agil.67@gmail.com
 * Web-Blog    : http://tanahdjawa.wordpress.com
 * Comment     : Anda boleh menggunakan/ mengembangkan kode ini (opensource),
 *               tapi sesuai kode etik, Anda dilarang mengganti nama author
 *               dan selalu menyertakan Original Author.
 */
package sipus.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Torry Ndory
 */
public class JTime extends JLabel implements ActionListener {

    private Timer timer;
    private Date date;
    private SimpleDateFormat dateFormat, dayFormat;
    private String day = "";

    public JTime() {
        setText(" ");
        date = new Date();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy / HH:mm:ss");
        dayFormat = new SimpleDateFormat("EEEE");
        if (dayFormat.format(date).toString().equals("Sunday")) {
            day = "Minggu";
        } else if (dayFormat.format(date).toString().equals("Monday")) {
            day = "Senin";
        } else if (dayFormat.format(date).toString().equals("Tuesday")) {
            day = "Selasa";
        } else if (dayFormat.format(date).toString().equals("Wednesday")) {
            day = "Rabu";
        } else if (dayFormat.format(date).toString().equals("Thursday")) {
            day = "Kamis";
        } else if (dayFormat.format(date).toString().equals("Friday")) {
            day = "Jumat";
        } else if (dayFormat.format(date).toString().equals("Saturday")) {
            day = "Sabtu";
        }
        timer = new Timer(1000, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        date.setTime(System.currentTimeMillis());
        setText(day + ", " + dateFormat.format(date));
        Random random = new Random();
    }
}
