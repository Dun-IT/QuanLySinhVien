package code;

import Model.SinhVien;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceSV;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class quanlysvUI extends JFrame {
    private JButton btnThem, btnXoa, btnSua, btnThoat;
    public static DefaultTableModel dtmTableDanhSach;
    private JTable tblDanhSach;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private Vector<SinhVien> dsSinhVien;
    private int rowSelected = -1;

    public quanlysvUI(){
        addControls();
        addEvents();
        showWindows();
    }

    public void addControls(){
        //Tao panel danh sach
        JPanel pnDanhSach = new JPanel();
        pnDanhSach.setLayout(new BorderLayout());
        pnDanhSach.setBackground(new Color(165, 213, 255));
        pnDanhSach.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));
        //Tạo bảng
        dtmTableDanhSach = new DefaultTableModel();
        dtmTableDanhSach.addColumn("STT");
        dtmTableDanhSach.addColumn("Họ tên");
        dtmTableDanhSach.addColumn("Khoá");
        dtmTableDanhSach.addColumn("MSSV");
        dtmTableDanhSach.addColumn("Lớp");
        dtmTableDanhSach.addColumn("GPA");
        dtmTableDanhSach.addColumn("ĐRL");
        tblDanhSach = new JTable(dtmTableDanhSach);
        tblDanhSach.setBackground(Color.WHITE);
        tblDanhSach.setRowHeight(tblDanhSach.getRowHeight() + 15);
        tblDanhSach.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        Font headerFont = new Font(Font.SANS_SERIF, Font.BOLD,22);
        JTableHeader tblHeader = tblDanhSach.getTableHeader();      //Tạo header cho bảng
        tblHeader.setBackground(new Color(93, 167, 232));
        tblHeader.setFont(headerFont);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();   //Tạo biến trung gian để căn chỉnh header
        headerRenderer = (DefaultTableCellRenderer) tblHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblDanhSach.setDefaultEditor(Object.class, null);
        tblDanhSach.getColumnModel().getColumn(0).setPreferredWidth(20);    //Thay đổi kích thước các cột trong bảng
        tblDanhSach.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDanhSach.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblDanhSach.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblDanhSach.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblDanhSach.setShowHorizontalLines(true);       //Hiển thị dòng ngang và dọc
        tblDanhSach.setShowVerticalLines(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();   //Tạo biến trung gian để căn chỉnh bảng
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i <= 6; i++){
            tblDanhSach.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //Thêm thanh cuộn cho bảng
        JScrollPane scrollDanhSach = new JScrollPane(tblDanhSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hienThiDanhSachSinhVien();
        pnDanhSach.add(scrollDanhSach, BorderLayout.CENTER);


        //Tao panel tim kiem
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setBackground(new Color(165, 213, 255));
        pnTimKiem.setLayout(new GridBagLayout());
        pnTimKiem.setPreferredSize(new Dimension(100, 70));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        txtSearch.setPreferredSize(new Dimension(30,35));
        lblSearch = new JLabel();
        lblSearch.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlysvUI/search.svg"))));
        pnTimKiem.add(txtSearch);
        pnTimKiem.add(lblSearch);

        //Tao panel chuc nang
        JPanel pnChucNang = new JPanel();
        pnChucNang.setBackground(new Color(165, 213, 255));
        pnChucNang.setLayout(new GridBagLayout());
        pnChucNang.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Bahnschrift", Font.PLAIN, 20);
        btnThem = new JButton("Thêm");
        btnThem.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlysvUI/add.svg"))));
        customButton(btnThem);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8,0,8,0);
        pnChucNang.add(btnThem,gbc);
        btnXoa = new JButton("Xoá");
        btnXoa.setIconTextGap(10);
        btnXoa.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlysvUI/erase.svg"))));
        customButton(btnXoa);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnChucNang.add(btnXoa,gbc);
        btnSua = new JButton("Sửa");
        btnSua.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlysvUI/edit.svg"))));
        customButton(btnSua);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnChucNang.add(btnSua,gbc);
        btnThoat = new JButton("Quay lại");
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlysvUI/close.svg"))));
        customButton(btnThoat);
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnChucNang.add(btnThoat, gbc);

        //Tao container
        Container con = getContentPane();
        JPanel pnQuanLyDS = new JPanel();
        pnQuanLyDS.setLayout(new BorderLayout());
        pnQuanLyDS.add(pnDanhSach, BorderLayout.CENTER);
        pnQuanLyDS.add(pnChucNang, BorderLayout.EAST);
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pnMain.setBackground(new Color(165, 213, 255));
        pnMain.add(pnQuanLyDS, BorderLayout.CENTER);
        pnMain.add(pnTimKiem, BorderLayout.NORTH);
        con.add(pnMain);
    }

    /**
     * Đọc dữ liệu từ cơ sở dữ liệu và lưu vào 1 vector dsSinhVien
     */
    public void docDuLieu(){
        dsSinhVien = ServiceSV.layDanhSachSinhVien();
    }

    /**
     * Lọc thông tin khi người dùng nhập vào thanh tìm kiếm
     */
    public void locThongTin(){
        docDuLieu();
        String duLieuLoc = txtSearch.getText();
        Vector<SinhVien> dsSinhVienDaLoc = new Vector<>();
        for (SinhVien sinhVien : dsSinhVien){
            if (sinhVien.getTen().toUpperCase().contains(duLieuLoc.toUpperCase())
                    || sinhVien.getMssv().toUpperCase().contains(duLieuLoc.toUpperCase())){
                dsSinhVienDaLoc.add(sinhVien);
            }
            if (duLieuLoc.trim().length() > 0){
                hienThiDanhSachSinhVien(dsSinhVienDaLoc);
            }
            else {
                hienThiDanhSachSinhVien();
            }
        }
    }

    /**
     * Hiển thị danh sách sinh viên
     */
    public void hienThiDanhSachSinhVien(){
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (SinhVien sinhVien : dsSinhVien){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    /**
     * Hiển thị danh sách sinh viên sau khi người dùng nhập dữ liệu
     * vào thanh tìm kiếm
     */
    public void hienThiDanhSachSinhVien(Vector<SinhVien> dsSinhVienDaLoc){
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (SinhVien sinhVien : dsSinhVienDaLoc){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    public void addEvents(){
        //Chức năng nút thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemSinhVienGUI();
//                setVisible(false);
            }
        });
        //Chức năng nút xoá
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowSelected = tblDanhSach.getSelectedRow();
                if (rowSelected != -1){
                    String mssv = (String) tblDanhSach.getValueAt(rowSelected,3);
                    int chon = JOptionPane.showConfirmDialog(quanlysvUI.this,"Xác nhận xoá?","Confirm",JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION){
                        boolean status = ServiceSV.xoaSinhVien(mssv);
                        if (status){
                            locThongTin();
                            JOptionPane.showMessageDialog(quanlysvUI.this,"Xoá sinh viên thành công");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(quanlysvUI.this, "Vui lòng chọn thông tin muốn xoá!");
                }
            }
        });
        //Chức năng nút sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowSelected = tblDanhSach.getSelectedRow();
                if (rowSelected != -1){
                    int colSelected = tblDanhSach.getSelectedColumn();
                    SinhVien sv = new SinhVien();
                    sv.setTen((String) tblDanhSach.getValueAt(rowSelected, 1));
                    sv.setKhoa((int) tblDanhSach.getValueAt(rowSelected, 2));
                    sv.setMssv((String) tblDanhSach.getValueAt(rowSelected, 3));
                    sv.setLop((String) tblDanhSach.getValueAt(rowSelected, 4));
                    sv.setGpa((double) tblDanhSach.getValueAt(rowSelected, 5));
                    sv.setDrl((int) tblDanhSach.getValueAt(rowSelected, 6));

                    switch (colSelected){
                        case 1:
                            String ten = JOptionPane.showInputDialog(quanlysvUI.this, "Họ và tên: ", sv.getTen());
                            if(ten != null && ten.trim().length() > 0){
                                sv.setTen(ten);
                                boolean status = ServiceSV.chinhSuaThongTinSinhVien(sv);
                                if(status){
                                    locThongTin();
                                    JOptionPane.showMessageDialog(quanlysvUI.this, "Chỉnh sửa tên sinh viên thành công!");
                                }
                            }else {
                                JOptionPane.showMessageDialog(quanlysvUI.this, "Tên không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 2:
                            String khoa = JOptionPane.showInputDialog(quanlysvUI.this, "Khoá: ", sv.getKhoa());
                            if(khoa != null && khoa.trim().length() > 0){
                                sv.setKhoa(Integer.parseInt(khoa));
                                boolean status = ServiceSV.chinhSuaThongTinSinhVien(sv);
                                if(status){
                                    locThongTin();
                                    JOptionPane.showMessageDialog(quanlysvUI.this, "Chỉnh sửa khoá thành công!");
                                }
                            }else {
                                JOptionPane.showMessageDialog(quanlysvUI.this, "Khoá không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(quanlysvUI.this, "Không thể thay đổi MSSV!", "Error!", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 4:
                            String lop = JOptionPane.showInputDialog(quanlysvUI.this, "Lớp: ", sv.getLop());
                            if(lop != null && lop.trim().length() > 0){
                                sv.setLop(lop);
                                boolean status = ServiceSV.chinhSuaThongTinSinhVien(sv);
                                if(status){
                                    locThongTin();
                                    JOptionPane.showMessageDialog(quanlysvUI.this, "Chỉnh sửa lớp thành công!");
                                }
                            }else {
                                JOptionPane.showMessageDialog(quanlysvUI.this, "Lớp không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 5:
                            String gpa = JOptionPane.showInputDialog(quanlysvUI.this, "GPA: ", sv.getGpa());
                            if(gpa != null && gpa.trim().length() > 0){
                                sv.setGpa(Double.parseDouble(gpa));
                                boolean status = ServiceSV.chinhSuaThongTinSinhVien(sv);
                                if(status){
                                    locThongTin();
                                    JOptionPane.showMessageDialog(quanlysvUI.this, "Chỉnh sửa GPA thành công!");
                                }
                            }else {
                                JOptionPane.showMessageDialog(quanlysvUI.this, "GPA không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case 6:
                            String drl = JOptionPane.showInputDialog(quanlysvUI.this, "ĐRL: ", sv.getDrl());
                            if(drl != null && drl.trim().length() > 0){
                                sv.setDrl(Integer.parseInt(drl));
                                boolean status = ServiceSV.chinhSuaThongTinSinhVien(sv);
                                if(status){
                                    locThongTin();
                                    JOptionPane.showMessageDialog(quanlysvUI.this, "Chỉnh sửa ĐRL thành công!");
                                }
                            }else {
                                JOptionPane.showMessageDialog(quanlysvUI.this, "ĐRL không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(quanlysvUI.this, "Vui lòng chọn thông tin muốn sửa!");
                }
            }
        });
        //Chắc năng nút thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainUI();
            }
        });
        //Chức năng thanh tìm kiếm
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locThongTin();
            }
        });
    }

    /**
     * Phương thức dùng để thay đổi các nút có cùng kích thước trong chương trình
     * @param btn
     */
    public void customButton(JButton btn) {
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        btn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(150, 70));
//        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     * Hiển thị cửa sổ
     */
    public void showWindows(){
        setTitle("Quản lý sinh viên");
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/quanlysvUI/icon.svg")));
//        setUndecorated(true);
        setExtendedState(MAXIMIZED_BOTH);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new quanlysvUI();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

