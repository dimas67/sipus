/*
 * Author      : Dimas Agil Tejo Baskoro
 * E-mail      : dimas.agil.67@gmail.com
 * Web-Blog    : http://tanahdjawa.wordpress.com
 * Comment     : Anda boleh menggunakan/ mengembangkan kode ini (opensource),
 *               tapi sesuai kode etik, Anda dilarang mengganti nama author
 *               dan selalu menyertakan Original Author.
 */
package sipus.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Sharie Dimmas
 */
public class OptionPane {

    public static void showMessage(Object object) {
        JOptionPane.showMessageDialog(null, object, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(Object object) {
        JOptionPane.showMessageDialog(null, object, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningMessage(Object object) {
        JOptionPane.showMessageDialog(null, object, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static int showConfirmMessage(Object object) {
        return JOptionPane.showConfirmDialog(null, object, "Confirm", JOptionPane.YES_NO_OPTION);
    }

    public static int showQuestionBox(String message, String option[]) {
        return JOptionPane.showOptionDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
    }
}
