package code;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.JPanel;

public class mainUI extends JFrame {
    //Attribute
//    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//    private int width = (int) screen.getWidth();
//    private int height = (int) screen.getHeight();
    private JButton btnQuanLySV, btnDanhSach, btnThongTin, btnThoat, btnPhanHoi, btnQuanLyGV;

    //Constructor
    public mainUI(){
        addControls();
        addEvents();
        showWindows();
    }

    //Add control
    public void addControls(){
        //Tao khu vuc hien thi thong tin project
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnThongTin.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        pnThongTin.setPreferredSize(new Dimension(0, 200));
        pnThongTin.setBackground(new Color(93, 167, 232));
        JLabel lbThongTin = new JLabel("Quản lý trường học");
        lbThongTin.setHorizontalAlignment(JLabel.CENTER);
        lbThongTin.setVerticalAlignment(JLabel.CENTER);
        lbThongTin.setFont(new Font("Bahnschrift", Font.BOLD, 70));
        JLabel lbCreatedBy = new JLabel("Version 1.0");
        lbCreatedBy.setHorizontalAlignment(JLabel.CENTER);
        lbCreatedBy.setVerticalAlignment(JLabel.CENTER);
        lbCreatedBy.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));

        JLabel lbLeft = new JLabel();
        lbLeft.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/school.svg"))));
        lbLeft.setVerticalAlignment(JLabel.CENTER);
        lbLeft.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbRight = new JLabel();
        lbRight.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/class.svg"))));
        lbRight.setVerticalAlignment(JLabel.CENTER);
        lbRight.setHorizontalAlignment(JLabel.CENTER);

        pnThongTin.add(lbThongTin, BorderLayout.CENTER);
        pnThongTin.add(lbCreatedBy, BorderLayout.SOUTH);
        pnThongTin.add(lbLeft, BorderLayout.WEST);
        pnThongTin.add(lbRight, BorderLayout.EAST);

        //Tao khu vuc hien thi cac nut chuc nang
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));
        JPanel pn1 = new JPanel();
        pn1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        pn1.setBackground(new Color(165, 213, 255));
        JPanel pn2 = new JPanel();
        pn2.setBackground(new Color(165, 213, 255));
        pn2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        JPanel pn3 = new JPanel();
        pn3.setLayout(new FlowLayout(FlowLayout.CENTER));
        pn3.setBackground(new Color(165, 213, 255));

        btnQuanLySV = new JButton("Quản lý sinh viên");
        btnQuanLySV.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/studentMain.svg"))));
        btnDanhSach = new JButton("Danh sách");
        btnDanhSach.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/list.svg"))));
        btnThongTin = new JButton("Thông tin");
        btnThongTin.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/info.svg"))));
        btnThoat = new JButton("Thoát");
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/exit.svg"))));
        btnQuanLyGV = new JButton("Quản lý giáo viên");
        btnQuanLyGV.setIcon(new FlatSVGIcon(Objects.requireNonNull(mainUI.class.getResource("/image/MainUI/teacherMain.svg"))));
        btnPhanHoi = new JButton("Phản hồi");
        btnPhanHoi.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/MainUI/setting.svg"))));
        customButton(btnQuanLySV);
        customButton(btnQuanLyGV);
        customButton(btnDanhSach);
        customButton(btnThongTin);
        customButton(btnThoat);
        customButton(btnPhanHoi);
        JLabel lb = new JLabel("Menu");
        lb.setFont(new Font("Trebuchet MS", Font.BOLD, 25));

        pn1.add(btnQuanLySV);
        pn1.add(btnQuanLyGV);
        pn1.add(btnDanhSach);
        pn2.add(btnThongTin);
        pn2.add(btnPhanHoi);
        pn2.add(btnThoat);
        pn3.add(lb);
        pnChucNang.add(Box.createVerticalGlue());
        pnChucNang.add(pn3);
        pnChucNang.add(pn1);
        pnChucNang.add(pn2);
//        pnChucNang.add(Box.createVerticalGlue());
        pnChucNang.setBackground(new Color(165, 213, 255));
        pnChucNang.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        //Container
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pnMain.setBackground(new Color(165, 213, 255));
        pnMain.add(pnThongTin, BorderLayout.NORTH);
        pnMain.add(pnChucNang, BorderLayout.CENTER);
        con.add(pnMain);
    }

    //Function
    public void customButton(JButton btn){
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);                                                //Khi nhấp sẽ không hiện viền trong nút
        btn.setIconTextGap(20);                                                 //Set khoảng cách giữa chữ và icon
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setFont(new Font("Bahnschrift", Font.BOLD, 25));
//        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(300, 220));
//        btn.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    //Add event
    public void addEvents(){
        //Chức năng nút thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //Chức năng nút quản lý sinh viên
        btnQuanLySV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new quanlysvUI();
                setVisible(false);
            }
        });

        btnQuanLyGV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new quanlygvUI();
                setVisible(false);
            }
        });
        //Chức năng nút thông tin
        btnThongTin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new thongtinUI();
            }
        });
        //Chức năng nút danh sách
        btnDanhSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new danhsachUI();
                setVisible(false);
            }
        });
        //Chức năng nút Feedback
        btnPhanHoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://forms.gle/dfTXVMh8iLxTEFJp6"));
                } catch (URISyntaxException | IOException e6) {
                    e6.printStackTrace();
                }
            }
        });
    }

    /**
     * Hiển thị cửa sổ
     */
    public void showWindows(){
        setTitle("Quản lý sinh viên");
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/MainUI/icon.svg"))); //Set icon cho chương trình
        setExtendedState(JFrame.MAXIMIZED_BOTH);   //Tự động mở full màn hình khi chạy chương trình
//        setResizable(false);    //Không cho người dùng thay đổi kích thước cửa sổ
        setLocationRelativeTo(null);    //Cửa sổ xuất hiện giữa màn hình khi chạy
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Test main
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new mainUI();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}