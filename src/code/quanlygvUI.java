package code;

import Model.GiaoVien;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceGV;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

public class quanlygvUI extends JFrame {
    public JButton btnThem, btnXoa, btnSua, btnThoat;
    public static DefaultTableModel dtmTableDanhSach;
    public JTable tblDanhSach;
    public JTextField txtSearch;
    public JLabel lblSearch;
    public Vector<GiaoVien> dsGiaoVien;
    public int rowSelected = -1;

    public quanlygvUI() {
        addControls();
        addEvent();
        showWindow();
    }

    private void showWindow() {
        this.setTitle("Quản lý giáo viên");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(new Dimension(900,600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addEvent() {
        //Nút thoát
        btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThoat.addActionListener(e -> {
            setVisible(false);
            new mainUI();
        });

        //Nút thêm
        btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThem.addActionListener(e -> {
            new ThemGiaoVienUI();
        });

        //Nút xoá
        btnXoa.addActionListener(e -> {
            rowSelected = tblDanhSach.getSelectedRow();
            if (rowSelected != -1) {
                String maGiaoVien = (String) tblDanhSach.getValueAt(rowSelected, 5);
                int chon = JOptionPane.showConfirmDialog(quanlygvUI.this, "Xác nhận xoá?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    boolean status = ServiceGV.xoaGiaoVien(maGiaoVien);
                    if (status) {
                        locThongTin();
                        JOptionPane.showMessageDialog(quanlygvUI.this, "Xoá giáo viên thành công");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(quanlygvUI.this, "Vui lòng chọn thông tin muốn xoá!");
            }
        });

        //Nút sửa
        btnSua.addActionListener(e -> {
            rowSelected = tblDanhSach.getSelectedRow();
            if (rowSelected != -1) {
                int colSelected = tblDanhSach.getSelectedColumn();
                GiaoVien gv = new GiaoVien();
                gv.setTen((String) tblDanhSach.getValueAt(rowSelected, 1));
                gv.setTuoi((int) tblDanhSach.getValueAt(rowSelected, 2));
                gv.setMail((String) tblDanhSach.getValueAt(rowSelected, 3));
                gv.setSdt((String) tblDanhSach.getValueAt(rowSelected, 4));
                gv.setMaGiaoVien((String) tblDanhSach.getValueAt(rowSelected, 5));
                gv.setVienDaoTao((String) tblDanhSach.getValueAt(rowSelected, 6));

                switch (colSelected) {
                    case 1:
                        String ten = JOptionPane.showInputDialog(quanlygvUI.this, "Họ và tên: ", gv.getTen());
                        if (ten != null && ten.trim().length() > 0) {
                            gv.setTen(ten);
                            boolean status = ServiceGV.chinhSuaThongTinGiaoVien(gv);
                            if (status) {
                                locThongTin();
                                JOptionPane.showMessageDialog(quanlygvUI.this, "Chỉnh sửa tên giáo viên thành công!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(quanlygvUI.this, "Tên không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                    case 2:
                        String tuoi = JOptionPane.showInputDialog(quanlygvUI.this, "Tuổi: ", gv.getTuoi());
                        if (tuoi != null && tuoi.trim().length() > 0) {
                            gv.setTuoi(Integer.parseInt(tuoi));
                            boolean status = ServiceGV.chinhSuaThongTinGiaoVien(gv);
                            if (status) {
                                locThongTin();
                                JOptionPane.showMessageDialog(quanlygvUI.this, "Chỉnh sửa tuổi thành công!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(quanlygvUI.this, "Tuổi không được để trống", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                    case 3:
                        String mail = JOptionPane.showInputDialog(quanlygvUI.this, "Mail: ", gv.getMail());
                        if (mail != null && mail.trim().length() > 0) {
                            gv.setMail(mail);
                            boolean status = ServiceGV.chinhSuaThongTinGiaoVien(gv);
                            if (status) {
                                locThongTin();
                                JOptionPane.showMessageDialog(quanlygvUI.this, "Chỉnh sửa mail thành công!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(quanlygvUI.this, "Mail không được để trống!", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                    case 4:
                        String sdt = JOptionPane.showInputDialog(quanlygvUI.this, "SĐT: ", gv.getSdt());
                        if (sdt != null && sdt.trim().length() > 0) {
                            gv.setSdt(sdt);
                            boolean status = ServiceGV.chinhSuaThongTinGiaoVien(gv);
                            if (status) {
                                locThongTin();
                                JOptionPane.showMessageDialog(quanlygvUI.this, "Chỉnh sửa SĐT thành công!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(quanlygvUI.this, "SĐT không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                    case 5:
                        JOptionPane.showMessageDialog(quanlygvUI.this, "Không thể chỉnh sửa mã GV!");
                        break;

                    case 6:
                        String vienDaoTao = JOptionPane.showInputDialog(quanlygvUI.this, "Viện: ", gv.getVienDaoTao());
                        if (vienDaoTao != null && vienDaoTao.trim().length() > 0) {
                            gv.setVienDaoTao(vienDaoTao);
                            boolean status = ServiceGV.chinhSuaThongTinGiaoVien(gv);
                            if (status) {
                                locThongTin();
                                JOptionPane.showMessageDialog(quanlygvUI.this, "Chỉnh sửa viện thành công!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(quanlygvUI.this, "Viện không được để trống", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                }
            }else{
                JOptionPane.showMessageDialog(quanlygvUI.this, "Vui lòng chọn thông tin muốn sửa");
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
     * Đọc dữ liệu từ cơ sở dữ liệu và lưu vào 1 vector dsSinhVien
     */
    public void docDuLieu() {
        dsGiaoVien = ServiceGV.layDanhSachGiaoVien();
    }

    /**
     * Lọc thông tin khi người dùng nhập vào thanh tìm kiếm
     */
    public void locThongTin() {
        docDuLieu();
        String duLieuLoc = txtSearch.getText();
        Vector<GiaoVien> dsGiaoVienDaLoc = new Vector<>();
        for (GiaoVien giaoVien : dsGiaoVien) {
            if (giaoVien.getTen().toUpperCase().contains(duLieuLoc.toUpperCase())
                    || giaoVien.getMaGiaoVien().toUpperCase().contains(duLieuLoc.toUpperCase())) {
                dsGiaoVienDaLoc.add(giaoVien);
            }
            if (duLieuLoc.trim().length() > 0) {
                hienThiDanhSachGiaoVien(dsGiaoVienDaLoc);
            } else {
                hienThiDanhSachGiaoVien();
            }
        }
    }

    /**
     * Hiển thị danh sách sinh viên sau khi người dùng nhập dữ liệu
     * vào thanh tìm kiếm
     */
    public void hienThiDanhSachGiaoVien(Vector<GiaoVien> dsGiaoVienDaLoc) {
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (GiaoVien GiaoVien : dsGiaoVienDaLoc) {
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(GiaoVien.getTen());
            vec.add(GiaoVien.getTuoi());
            vec.add(GiaoVien.getMail());
            vec.add(GiaoVien.getSdt());
            vec.add(GiaoVien.getMaGiaoVien());
            vec.add(GiaoVien.getVienDaoTao());
            dtmTableDanhSach.addRow(vec);
        }
    }

    /**
     * Hiển thị danh sách sinh viên sau khi người dùng nhập dữ liệu
     * vào thanh tìm kiếm
     */
    public void hienThiDanhSachGiaoVien() {
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (GiaoVien giaoVien : dsGiaoVien) {
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(giaoVien.getTen());
            vec.add(giaoVien.getTuoi());
            vec.add(giaoVien.getMail());
            vec.add(giaoVien.getSdt());
            vec.add(giaoVien.getMaGiaoVien());
            vec.add(giaoVien.getVienDaoTao());
            dtmTableDanhSach.addRow(vec);
        }
    }


    private void addControls() {
        //Tạo panel danh sách giáo viên
        JPanel pnDanhSach = new JPanel();
        pnDanhSach.setBackground(new Color(165,213,255));
        pnDanhSach.setLayout(new BorderLayout());
        pnDanhSach.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));

        //Tạo bảng
        dtmTableDanhSach = new DefaultTableModel();
        dtmTableDanhSach.addColumn("STT");
        dtmTableDanhSach.addColumn("Họ tên");
        dtmTableDanhSach.addColumn("Tuổi");
        dtmTableDanhSach.addColumn("Mail");
        dtmTableDanhSach.addColumn("SĐT");
        dtmTableDanhSach.addColumn("Mã GV");
        dtmTableDanhSach.addColumn("Viện");
        tblDanhSach = new JTable(dtmTableDanhSach);
        tblDanhSach.setBackground(Color.WHITE);
        tblDanhSach.setRowHeight(tblDanhSach.getRowHeight() + 15);
        tblDanhSach.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        Font headerFont = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        JTableHeader tblHeader = tblDanhSach.getTableHeader();  //Tạo header cho bảng
        tblHeader.setBackground(new Color(93, 167, 232));
        tblHeader.setFont(headerFont);
        tblDanhSach.setDefaultEditor(Object.class, null);
        tblDanhSach.getColumnModel().getColumn(0).setPreferredWidth(10);    //Thay đổi kích thước các cột trong bảng
        tblDanhSach.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDanhSach.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblDanhSach.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblDanhSach.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblDanhSach.setShowHorizontalLines(true);   //Hiển thị dòng ngang và dọc
        tblDanhSach.setShowVerticalLines(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();   //Tạo biến trung gian để căn chỉnh bảng
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i <= 6; i++) {
            tblDanhSach.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblDanhSach.setAutoCreateRowSorter(true);

        //Thêm thanh cuộn cho bảng
        JScrollPane scrollDanhSach = new JScrollPane(tblDanhSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hienThiDanhSachGiaoVien();
        pnDanhSach.add(scrollDanhSach, BorderLayout.CENTER);

        //Tạo panel tìm kiếm
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setBackground(new Color(165, 213, 255));
        pnTimKiem.setLayout(new GridBagLayout());
        pnTimKiem.setPreferredSize(new Dimension(100, 70));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        txtSearch.setPreferredSize(new Dimension(30, 35));
        lblSearch = new JLabel();
        lblSearch.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlygvUI/search.svg"))));
        pnTimKiem.add(txtSearch);
        pnTimKiem.add(lblSearch);

        //Tạo panel chức năng
        JPanel pnChucNang = new JPanel();
        pnChucNang.setBackground(new Color(165, 213, 255));
        pnChucNang.setLayout(new GridBagLayout());
        pnChucNang.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        btnThem = new JButton();
        btnThem.setText("Thêm");
        btnThem.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlygvUI/add.svg"))));
        customButton(btnThem);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 0, 8, 0);
        pnChucNang.add(btnThem, gbc);

        btnXoa = new JButton();
        btnXoa.setText("Xoá");
        btnXoa.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlygvUI/erase.svg"))));
        customButton(btnXoa);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnChucNang.add(btnXoa, gbc);

        btnSua = new JButton();
        btnSua.setText("Sửa");
        btnSua.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlygvUI/edit.svg"))));
        customButton(btnSua);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnChucNang.add(btnSua, gbc);

        btnThoat = new JButton();
        btnThoat.setText("Quay lại");
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/quanlygvUI/close.svg"))));
        customButton(btnThoat);
        gbc.gridx = 0;
        gbc.gridy = 8;
        pnChucNang.add(btnThoat, gbc);

        //Tạo container
        Container con = getContentPane();
        JPanel pnQuanLyDS = new JPanel();
        pnQuanLyDS.setLayout(new BorderLayout());
        pnQuanLyDS.add(pnDanhSach, BorderLayout.CENTER);
        pnQuanLyDS.add(pnChucNang, BorderLayout.EAST);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnMain.setBackground(new Color(165, 213, 255));
        pnMain.add(pnQuanLyDS, BorderLayout.CENTER);
        pnMain.add(pnTimKiem, BorderLayout.NORTH);
        con.add(pnMain);
    }

    public void customButton(JButton btn) {
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/quanlygvUI/icon.svg")));
        btn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(150, 70));
//        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public static void main(String[] args) {
        new quanlygvUI();
    }
}
