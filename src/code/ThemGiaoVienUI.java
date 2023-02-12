package code;

import Model.GiaoVien;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceGV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class ThemGiaoVienUI extends JFrame {
    public JButton btnThem, btnQuayLai;
    public JTextField txtHoTen, txtTuoi, txtMail, txtSdt, txtMa, txtVien;
    public JLabel lblHoTen, lblTuoi, lblMail, lblSdt, lblMa, lblVien;
    public Vector<GiaoVien> dsGiaoVien;

    public ThemGiaoVienUI() {
        addControls();
        addEvents();
        showWindow();
    }

    private void showWindow() {
        setTitle("Thêm giáo viên");
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/ThemGiaoVienUI/icon.svg")));
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addEvents() {
        btnQuayLai.addActionListener(e -> {
            setVisible(false);
//            new quanlygvUI();
        });

        //Chức năng nút thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ten = txtHoTen.getText().trim();
                if (ten != null && ten.length() > 0) {
                    String tuoi = txtTuoi.getText().trim();
                    if (tuoi != null && tuoi.length() > 0) {
                        String mail = txtMail.getText().trim();
                        if (mail != null && mail.length() > 0) {
                            String sdt = txtSdt.getText().trim();
                            if (sdt != null && sdt.length() > 0) {
                                String maGiaoVien = txtMa.getText().trim();
                                if (maGiaoVien != null && mail.length() > 0) {
                                    if (!kiemTraGiaoVienTonTai(maGiaoVien)) {
                                        String vienDaoTao = txtVien.getText().trim();
                                        if (vienDaoTao != null && mail.length() > 0) {
                                            GiaoVien gv = new GiaoVien(ten, Integer.parseInt(tuoi), mail, sdt, maGiaoVien, vienDaoTao);
                                            boolean status = ServiceGV.themGiaoVien(gv);
                                            if (status) {
                                                JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Thêm giáo viên thành công!");
                                                hienThiDanhSachGiaoVien();
                                                setVisible(false);
//                                                new quanlygvUI();
                                            }
                                        } else if (vienDaoTao != null && vienDaoTao.equals("")) {
                                            JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Viện đào tạo không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                                        }
                                    } else if (maGiaoVien != null && maGiaoVien.equals("")) {
                                        JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Mã giáo viên không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Giáo viên đã tồn tại");
                                }
                            } else if (sdt != null && sdt.equals("")) {
                                JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Số điện thoại không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        } else if (mail != null && mail.equals("")) {
                            JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Mail không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } else if (tuoi != null && tuoi.equals("")) {
                        JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Tuổi không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (ten != null && ten.equals("")) {
                    JOptionPane.showMessageDialog(ThemGiaoVienUI.this, "Tên không được để trống", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private boolean kiemTraGiaoVienTonTai(String maGiaoVien) {
        docDulieu();
        for (GiaoVien gv : dsGiaoVien) {
            if (gv.getMaGiaoVien().equalsIgnoreCase(maGiaoVien)) {
                return true;
            }
        }
        return false;
    }

    public void docDulieu() {
        dsGiaoVien = ServiceGV.layDanhSachGiaoVien();
    }

    public void hienThiDanhSachGiaoVien() {
        docDulieu();
        quanlygvUI.dtmTableDanhSach.setRowCount(0);
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
            quanlygvUI.dtmTableDanhSach.addRow(vec);
        }
    }

    private void addControls() {
        //Thông tin giảng viên
        JPanel pnThongTin = new JPanel();
        pnThongTin.setPreferredSize(new Dimension(0, 80));
        pnThongTin.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbThongTin = new JLabel("Thông tin giảng viên");
        lbThongTin.setFont(new Font("Bahnschrift", Font.BOLD, 40));
        lbThongTin.setVerticalTextPosition(JLabel.CENTER);
        pnThongTin.add(lbThongTin);
        pnThongTin.setBackground(new Color(143, 184, 255));

        //Nhập thông tin giảng viên
        JPanel pnNhapThongTin = new JPanel();
        pnNhapThongTin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel[] pnBang = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            pnBang[i] = new JPanel();
            pnBang[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            pnBang[i].setBackground(new Color(0, 0, 0, 0));
        }
        //Tên
        lblHoTen = new JLabel("Họ và tên: ");
        customLabel(lblHoTen);
        txtHoTen = new JTextField(25);
        customTextField(txtHoTen);
        pnBang[0].add(lblHoTen);
        pnBang[0].add(txtHoTen);
        //Tuổi
        lblTuoi = new JLabel("Tuổi: ");
        customLabel(lblTuoi);
        txtTuoi = new JTextField(25);
        customTextField(txtTuoi);
        pnBang[1].add(lblTuoi);
        pnBang[1].add(txtTuoi);
        //Mail
        lblMail = new JLabel("Mail: ");
        customLabel(lblMail);
        txtMail = new JTextField(25);
        customTextField(txtMail);
        pnBang[2].add(lblMail);
        pnBang[2].add(txtMail);
        //SĐT
        lblSdt = new JLabel("Điện thoại: ");
        customLabel(lblSdt);
        txtSdt = new JTextField(25);
        customTextField(txtSdt);
        pnBang[3].add(lblSdt);
        pnBang[3].add(txtSdt);
        //Mã giảng viên
        lblMa = new JLabel("Mã: ");
        customLabel(lblMa);
        txtMa = new JTextField(25);
        customTextField(txtMa);
        pnBang[4].add(lblMa);
        pnBang[4].add(txtMa);
        //Viện đào tạo
        lblVien = new JLabel("Viện: ");
        customLabel(lblVien);
        txtVien = new JTextField(25);
        customTextField(txtVien);
        pnBang[5].add(lblVien);
        pnBang[5].add(txtVien);

        pnNhapThongTin.setLayout(new BoxLayout(pnNhapThongTin, BoxLayout.Y_AXIS));
        pnNhapThongTin.add(Box.createVerticalGlue());

        for (int i = 0; i < 6; i++) {
            pnNhapThongTin.add(pnBang[i]);
        }

        //pnNhapThongTin.add(Box.createVerticalGlue());
        pnNhapThongTin.setBackground(new Color(252, 228, 192));

        //Ảnh trang trí
        JLabel lbAnh = new JLabel();
        lbAnh.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/ThemGiaoVienUI/student.svg"))));

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

        //Container
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

    /**
     * Phương thức dùng để chỉnh sửa đồng bộ các nút có cùng kích thước
     */
    public void customButton(JButton btn) {
        btn.setFocusable(false);
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(130, 50));
//        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void customLabel(JLabel lbl) {
        lbl.setPreferredSize(new Dimension(100, 25));
        lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    }

    /**
     * Phương thức dùng để chỉnh sửa đồng bộ các textfield có cùng kích thước
     */
    public void customTextField(JTextField txt) {
        txt.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        txt.setPreferredSize(new Dimension(0, 30));
        txt.setAlignmentY(CENTER_ALIGNMENT);
    }


    public static void main(String[] args) {
        new ThemGiaoVienUI();
    }
}
