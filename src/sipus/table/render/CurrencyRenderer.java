/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.table.render;

import java.text.NumberFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class CurrencyRenderer extends DefaultTableCellRenderer {

    public CurrencyRenderer() {
        super();
    }

    @Override
    public void setValue(Object value) {
        if ((value != null) && (value instanceof Number)) {
            Number numberValue = (Number) value;
            NumberFormat formatter = NumberFormat.getNumberInstance();
            value = formatter.format(numberValue.doubleValue());
        }
        super.setValue(value);
    }
}
