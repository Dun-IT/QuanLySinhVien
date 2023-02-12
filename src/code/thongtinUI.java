package code;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Objects;

public class thongtinUI extends JDialog {
    private DefaultTableModel dtmTableInfoMember;
    private JTable tblInfoMember;

    public thongtinUI(){
        addControls();
        showDialog();
    }

    private void addControls(){
        //tao panel ten Project
        JPanel pnSoict = new JPanel();
        pnSoict.setLayout(new BorderLayout());
        JLabel lblSoict = new JLabel();
        lblSoict.setIcon(new ImageIcon(Objects.requireNonNull(thongtinUI.class.getResource("/image/thongtinUI/soict.png"))));
        lblSoict.setHorizontalTextPosition(JLabel.CENTER);
        lblSoict.setVerticalTextPosition(JLabel.CENTER);
        lblSoict.setVerticalAlignment(JLabel.CENTER);
        lblSoict.setHorizontalAlignment(JLabel.CENTER);
        pnSoict.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        //pnSoict.setBackground(new Color(93, 167, 232));
        pnSoict.add(lblSoict, BorderLayout.CENTER);

        //Tao panel thong tin thanh vien
        JPanel ppnThongTinThanhVien = new JPanel();
        ppnThongTinThanhVien.setLayout(new BorderLayout());
        ppnThongTinThanhVien.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        dtmTableInfoMember = new DefaultTableModel();
        dtmTableInfoMember.addColumn("Họ tên");
        dtmTableInfoMember.addColumn("MSSV");
        dtmTableInfoMember.addColumn("Lớp");
        dtmTableInfoMember.addColumn("Email");
        tblInfoMember = new JTable(dtmTableInfoMember);
        tblInfoMember.setEnabled(false);
        JTableHeader tblHeader = tblInfoMember.getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer = (DefaultTableCellRenderer) tblHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        Font headerFont = new Font("Bahnschrift", Font.BOLD, 15);
        tblHeader.setFont(headerFont);
        tblHeader.setBackground(new Color(165, 213, 255));
        tblInfoMember.getColumnModel().getColumn(3).setPreferredWidth(180);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblInfoMember.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblInfoMember.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblInfoMember.setRowHeight(tblInfoMember.getRowHeight() + 15);
        tblInfoMember.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        tblInfoMember.setShowGrid(true);
        dtmTableInfoMember.addRow(new Object[]{"Nguyễn Hải Phong", "20207624", "IT-LTU 01-K65", "phong.nh207624@sis.hust.edu.vn"});
        dtmTableInfoMember.addRow(new Object[]{"Nguyễn Khoa Đoàn", "20207642", "IT-LTU 01-K65", "doan.nk207642@sis.hust.edu.vn"});
        dtmTableInfoMember.addRow(new Object[]{"Đỗ Trung Hiếu", "20207604", "IT-LTU 01-K65", "hieu.dt207604@sis.hust.edu.vn"});
        JScrollPane scrollMember = new JScrollPane(tblInfoMember, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ppnThongTinThanhVien.add(scrollMember, BorderLayout.CENTER);


        JPanel pnThongTinHocPhan = new JPanel();
        pnThongTinHocPhan.setLayout(new BorderLayout());
        JLabel lblThongTin = new JLabel("Project java nâng cao");
        lblThongTin.setFont(new Font("Bahnschrift", Font.BOLD, 25));
        lblThongTin.setVerticalAlignment(JLabel.CENTER);
        lblThongTin.setHorizontalAlignment(JLabel.CENTER);
        JLabel lblTTin1 = new JLabel("Tên môn học: Java nâng cao");
        customLabel(lblTTin1);
        JLabel lblTTin2 = new JLabel("Mã học phần: IT3680Q");
        customLabel(lblTTin2);
        JLabel lblTTin3 = new JLabel("Mã lớp: 131224");
        customLabel(lblTTin3);
        JLabel lblTTin4 = new JLabel("Giảng viên phụ trách: Nguyễn Tuấn Dũng");
        customLabel(lblTTin4);
        Font font = new Font("Bahnschritf", Font.PLAIN, 15);
        lblTTin1.setFont(font);
        lblTTin2.setFont(font);
        lblTTin3.setFont(font);
        lblTTin4.setFont(font);
        JPanel pnTTin = new JPanel();
        pnTTin.setLayout(new BoxLayout(pnTTin, BoxLayout.Y_AXIS));
        pnTTin.add(lblTTin1);
        pnTTin.add(lblTTin2);
        pnTTin.add(lblTTin3);
        pnTTin.add(lblTTin4);
        pnTTin.setBorder(BorderFactory.createEmptyBorder(10,280,10,0));
        pnThongTinHocPhan.add(lblThongTin, BorderLayout.NORTH);
        pnThongTinHocPhan.add(pnTTin, BorderLayout.CENTER);

        //Tao panel logo
        JPanel pnLogo = new JPanel();
        pnLogo.setLayout(new FlowLayout());
        pnLogo.setBorder(BorderFactory.createEmptyBorder(6,0,8,0));
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/thongtinUI/logoThongTin.svg"))));
        lblLogo.setHorizontalTextPosition(JLabel.CENTER);
        lblLogo.setVerticalTextPosition(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        //pnLogo.setBackground(new Color(252, 228, 192));
        pnLogo.add(lblLogo);


        //Tao container
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        JPanel pnHocPhan = new JPanel();
        pnHocPhan.setLayout(new BorderLayout());
        pnMain.add(pnSoict, BorderLayout.NORTH);
        pnHocPhan.add(pnThongTinHocPhan, BorderLayout.NORTH);
        pnHocPhan.add(ppnThongTinThanhVien, BorderLayout.CENTER);
        //pnTTin.setBackground(new Color(143, 184, 255));
        pnMain.add(pnHocPhan, BorderLayout.CENTER);
        pnMain.add(pnLogo, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    public void customLabel(JLabel lbl){
        lbl.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
    }

    public void showDialog(){
        this.setTitle("Thông tin");
        this.setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/thongtinUI/icon.svg")));
        this.setSize(800,650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        thongtinUI ui = new thongtinUI();
        ui.showDialog();
    }

}
