package code;

import Model.SinhVien;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class ThemSinhVienGUI extends JFrame {
    //Attribute
    private int width = 900;
    private int height = 600;
    private JButton btnThem, btnQuayLai;
    private JTextField txtHoTen, txtKhoa, txtMSSV, txtLop, txtGPA, txtDRL;
    private JLabel lbHoTen, lbKhoa, lbMSSV, lbLop, lbGPA, lbDRL;
    private Vector<SinhVien> dsSinhVien;
    private JPanel pnAnh;

    //Constructor
    public ThemSinhVienGUI() {
        addControls();
        addEvents();
        showWindows();
    }

    //AddControls
    public void addControls() {
        //Thông tin sinh viên
        JPanel pnThongTin = new JPanel();
        pnThongTin.setPreferredSize(new Dimension(0,80));
        pnThongTin.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbThongTin = new JLabel("Thông tin sinh viên");
        lbThongTin.setFont(new Font("Bahnschrift", Font.BOLD, 40));
        lbThongTin.setVerticalTextPosition(JLabel.CENTER);
        pnThongTin.add(lbThongTin);
        pnThongTin.setBackground(new Color(143, 184, 255));

        //Nhập thông tin sinh viên
        JPanel pnNhapThongTin = new JPanel();
        pnNhapThongTin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel[] pnBang = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            pnBang[i] = new JPanel();
            pnBang[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            pnBang[i].setBackground(new Color(0, 0, 0, 0));
        }
        //Tên
        lbHoTen = new JLabel("Họ và tên: ");
        customLabel(lbHoTen);
        txtHoTen = new JTextField(25);
        customTextField(txtHoTen);
        pnBang[0].add(lbHoTen);
        pnBang[0].add(txtHoTen);
        //Khoa
        lbKhoa = new JLabel("Khoá: ");
        customLabel(lbKhoa);
        txtKhoa = new JTextField(25);
        customTextField(txtKhoa);
        pnBang[1].add(lbKhoa);
        pnBang[1].add(txtKhoa);
        //MSSV
        lbMSSV = new JLabel("MSSV: ");
        customLabel(lbMSSV);
        txtMSSV = new JTextField(25);
        customTextField(txtMSSV);
        pnBang[2].add(lbMSSV);
        pnBang[2].add(txtMSSV);
        //Lop
        lbLop = new JLabel("Lớp: ");
        customLabel(lbLop);
        txtLop = new JTextField(25);
        customTextField(txtLop);
        pnBang[3].add(lbLop);
        pnBang[3].add(txtLop);
        //GPA
        lbGPA = new JLabel("GPA: ");
        customLabel(lbGPA);
        txtGPA = new JTextField(25);
        customTextField(txtGPA);
        pnBang[4].add(lbGPA);
        pnBang[4].add(txtGPA);
        //ĐRL
        lbDRL = new JLabel("ĐRL: ");
        customLabel(lbDRL);
        txtDRL = new JTextField(25);
        customTextField(txtDRL);
        pnBang[5].add(lbDRL);
        pnBang[5].add(txtDRL);

        pnNhapThongTin.setLayout(new BoxLayout(pnNhapThongTin, BoxLayout.Y_AXIS));
        pnNhapThongTin.add(Box.createVerticalGlue());

        for (int i = 0; i < 6; i++) {
            pnNhapThongTin.add(pnBang[i]);
        }

        //pnNhapThongTin.add(Box.createVerticalGlue());
        pnNhapThongTin.setBackground(new Color(252, 228, 192));

        //Ảnh trang trí
        JLabel lbAnh = new JLabel();
        lbAnh.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/ThemSinhVienUI/student.svg"))));

        //Chức năng
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 25));
        btnThem = new JButton("Thêm");
        btnQuayLai = new JButton("Quay lại");
        customButton(btnThem);
        customButton(btnQuayLai);
        pnChucNang.add(btnThem);
        pnChucNang.add(btnQuayLai);
        pnChucNang.setBackground(new Color(143, 184, 255));

        //Containter
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnThongTin, BorderLayout.NORTH);
        pnMain.add(pnChucNang, BorderLayout.SOUTH);
        pnMain.add(pnNhapThongTin, BorderLayout.CENTER);
        pnMain.add(lbAnh, BorderLayout.EAST);
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnMain.setBackground(new Color(143, 184, 255));
        con.add(pnMain);
    }

    //AddEvents
    public void addEvents() {
        //Chức năng nút thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ten = txtHoTen.getText().trim();
                if (ten != null && ten.length() > 0) {
                    String khoa = txtKhoa.getText().trim();
                    if (khoa != null && khoa.length() > 0) {
                        String mssv = txtMSSV.getText().trim();
                        if (mssv != null && mssv.length() > 0) {
                            if (!kiemTraSinhVienTonTai(mssv)) {
                                String lop = txtLop.getText().trim();
                                if (lop != null && lop.length() > 0) {
                                    String gpa = txtGPA.getText().trim();
                                    if (gpa != null && gpa.length() > 0) {
                                        String drl = txtDRL.getText().trim();
                                        if (drl != null && drl.length() > 0) {
                                            SinhVien sv = new SinhVien(ten, Integer.parseInt(khoa), mssv, lop, Double.parseDouble(gpa), Integer.parseInt(drl));
                                            boolean status = ServiceSV.themSinhVien(sv);
                                            if (status) {
                                                JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Thêm sinh viên thành công !");
                                                hienThiDanhSachSinhVien();
                                                setVisible(false);
//                                                new quanlysvUI();
                                            }
                                        } else if (drl != null && drl.equals("")) {
                                            JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Điểm rèn luyện không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                                        }
                                    } else if (gpa != null && gpa.equals("")) {
                                        JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "GPA không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else if (lop != null && lop.equals("")) {
                                    JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Lớp không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Sinh viên đã tồn tại");
                            }
                        } else if (mssv != null && mssv.equals("")) {
                            JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "MSSV không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                        }
                    } else if (khoa != null && khoa.equals("")) {
                        JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Khoá không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (ten != null && ten.equals("")) {
                    JOptionPane.showMessageDialog(ThemSinhVienGUI.this, "Họ và tên không được để trống", "Waring", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //Chức năng nút quay lại
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new quanlysvUI();
            }
        });
    }

    /**
     * Phương thức dùng để chỉnh sửa đồng bộ các nút có cùng kích thước
     * @param btn
     */
    public void customButton(JButton btn) {
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(130, 50));
//        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     * Phương thức dùng để chỉnh sửa đồng bộ các label có cùng kích thước
     * @param lbl
     */
    public void customLabel(JLabel lbl){
        lbl.setPreferredSize(new Dimension(100, 25));
        lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    }

    /**
     * Phương thức dùng để chỉnh sửa đồng bộ các textfield có cùng kích thước
     * @param txt
     */
    public void customTextField(JTextField txt){
        txt.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        txt.setPreferredSize(new Dimension(0,30));
        txt.setAlignmentY(CENTER_ALIGNMENT);
    }

    /**
     * Kiểm tra sinh viên có tồn tại trong cơ sở dữ liệu hay không
     * @param MSSV
     * @return
     */
    private boolean kiemTraSinhVienTonTai(String MSSV) {
        docDuLieu();
        for (SinhVien sv : dsSinhVien) {
            if (sv.getMssv().equalsIgnoreCase(MSSV)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Đọc dữ liệu từ cơ sở dữ liệu vào 1 vector
     */
    private void docDuLieu() {
        dsSinhVien = ServiceSV.layDanhSachSinhVien();
    }

    public void hienThiDanhSachSinhVien(){
        docDuLieu();
        quanlysvUI.dtmTableDanhSach.setRowCount(0);
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
            quanlysvUI.dtmTableDanhSach.addRow(vec);
        }
    }


    /**
     * Hiển thị cửa sổ
     */
    public void showWindows() {
        setTitle("Thêm sinh viên");
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/ThemSinhVienUI/icon.svg")));
//        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //TestMain
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new ThemSinhVienGUI();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
