/*
 * Author      : Dimas Agil Tejo Baskoro
 * E-mail      : dimas.agil.67@gmail.com
 * Web-Blog    : http://tanahdjawa.wordpress.com
 * Comment     : Anda boleh menggunakan/ mengembangkan kode ini (opensource),
 *               tapi sesuai kode etik, Anda dilarang mengganti nama author
 *               dan selalu menyertakan Original Author.
 */
package sipus.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import sipus.util.OptionPane;

public class ApplicationChooser extends JComponent implements ActionListener {

    private DefaultListModel lsModel;
    private JList list;
    private Object object;
    private JPopupMenu popupMenu;
    private JMenuItem mnClose = new JMenuItem("Tutup");
    private JMenuItem mnCloseAll = new JMenuItem("Tutup Semua");
    private JDesktopPane desktopPane;

    public ApplicationChooser() {
        initComponent();
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    private void initComponent() {
        lsModel = new DefaultListModel();
        lsModel.removeAllElements();
        list = new JList(lsModel);

        list.setCellRenderer(new ActiveFrameListCellRenderer());
        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                onListItemClick();
            }
        });

        mnClose.addActionListener(this);
        mnCloseAll.addActionListener(this);

        popupMenu = new JPopupMenu();
        popupMenu.add(mnClose);
        popupMenu.add(mnCloseAll);
        list.setComponentPopupMenu(popupMenu);

        JScrollPane sp = new JScrollPane(list);
        sp.setBorder(new EtchedBorder(1));
        JPanel p = new JPanel(new BorderLayout());
        p.add(sp, "Center");
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);
    }

    public void addListElement(Object o) {
        lsModel.addElement(o);
        setSelectedObject(o);
    }

    public void removeListElement(Object o) {
        this.lsModel.removeElement(o);
        if (this.lsModel.getSize() == 0) {
            setSelectedObject(null);
        }
    }

    public final void setSelectedObject(Object obj) {
        object = obj;
    }

    public void onListItemClick() {
        Object o = this.list.getSelectedValue();
        if ((o instanceof ActiveFrame)) {
            ActiveFrame af = (ActiveFrame) o;
            JInternalFrame jif = af.getJInternalFrame();
            try {
                jif.setSelected(true);
            } catch (Exception ex) {
            }
            setSelectedObject(o);
        }
    }

    public void onListItemClickRemove() {
        Object o = this.list.getSelectedValue();
        if ((o instanceof ActiveFrame)) {
            ActiveFrame af = (ActiveFrame) o;
            JInternalFrame jif = af.getJInternalFrame();
            try {
                removeListElement(o);
                jif.setVisible(false);
            } catch (Exception ex) {
            }
            setSelectedObject(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnClose) {
            if (list.getSelectedIndex() < 0) {
                OptionPane.showWarningMessage("Silakan pilih window");
            } else {
                onListItemClickRemove();
            }
        } else if (e.getSource() == mnCloseAll) {
            if (OptionPane.showConfirmMessage("Tutup semua window yang aktif?") == JOptionPane.YES_OPTION) {
                lsModel.removeAllElements();
                getDesktopPane().removeAll();
            }
        }
    }
}
