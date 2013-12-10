package sipus.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class ActiveFrameListCellRenderer extends DefaultListCellRenderer {

    public ActiveFrameListCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        if ((value instanceof ActiveFrame)) {
            super.setIcon(new ImageIcon(getClass().getResource("/sipus/icon/frame.png")));
        }
        if (isSelected) {            
            setBackground(Color.black);
            setForeground(Color.white);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
