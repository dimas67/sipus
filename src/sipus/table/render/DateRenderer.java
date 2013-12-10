/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.table.render;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class DateRenderer extends DefaultTableCellRenderer {

    public DateRenderer() {
        super();
    }

    @Override
    public void setValue(Object value) {
        if ((value != null) && (value instanceof Date)) {
            Date date = (Date) value;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            value = dateFormat.format(date);
        }
        super.setValue(value);
    }
}
