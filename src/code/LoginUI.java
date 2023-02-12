package code;

import Model.Account;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Vector;

public class LoginUI extends JFrame {
    JButton btnExit;
    JButton btnMinimize;
    JButton btnLogin;
    JTextField txtEmail;
    JPasswordField txtPassword;
    JCheckBox cbShowPass;
    JLabel lblFb;
    JLabel lblYt;
    JLabel lblMail;
    JLabel lblGithub;
    JLabel lblLinkedin;
    Vector<Account> dsTaiKhoan;

    public LoginUI(String title) {
        super(title);
        addControls();
        addEvent();
        showWindow();
    }

    private void addControls() {
        Container conMain = getContentPane();
        conMain.setLayout(new BorderLayout());
        //Setting panel main
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setPreferredSize(new Dimension(800, 600));
        conMain.add(pnMain);

        //Setting panel Center and East
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setPreferredSize(new Dimension(400, 600));
        pnCenter.setBackground(new Color(40, 65, 9));

        JPanel pnEast = new JPanel();
        pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
        pnEast.setPreferredSize(new Dimension(400, 600));

        //Add panel Center and East -> panel main
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnEast, BorderLayout.EAST);

        //Add cac panel vao panel Center
        //Panel app FB, Web,..
        JPanel pnApp = new JPanel();
        pnApp.setPreferredSize(new Dimension(400, 200));
        pnApp.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnApp.setBackground(new Color(165, 213, 255));
        pnCenter.add(pnApp);

        lblFb = new JLabel();
        lblFb.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/facebook_logo.svg"))));
        lblYt = new JLabel();
        lblYt.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/youtube_logo.svg"))));
        lblMail = new JLabel();
        lblMail.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/gmail_logo.svg"))));
        lblGithub = new JLabel();
        lblGithub.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/github_logo.svg"))));
        lblLinkedin = new JLabel();
        lblLinkedin.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/linkedin_logo.svg"))));
        pnApp.add(lblFb);
        pnApp.add(lblGithub);
        pnApp.add(lblLinkedin);
        pnApp.add(lblYt);
        pnApp.add(lblMail);

        //Setting Panel Welcome
        JPanel pnWel = new JPanel();
        pnWel.setPreferredSize(new Dimension(400, 100));
        pnWel.setBackground(new Color(165, 213, 255));
        pnCenter.add(pnWel);

        JLabel lblWel = new JLabel();
        lblWel.setText("WELCOME!");
        lblWel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        lblWel.setForeground(new Color(34, 68, 97));

        JLabel lblLoWel = new JLabel();
        lblLoWel.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/education.svg"))));
        pnWel.add(lblLoWel);
        pnWel.add(lblWel);

        //Setting Panel StudentManegement
        JPanel pnStu = new JPanel();
        pnStu.setPreferredSize(new Dimension(400, 100));
        pnStu.setBackground(new Color(165, 213, 255));
        pnCenter.add(pnStu);

        JLabel lblStu = new JLabel();
        lblStu.setText("STUDENT MANEGEMENT");
        lblStu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        lblStu.setForeground(new Color(34, 68, 97));
        pnStu.add(lblStu);

        //Setting Panel Null Center
        JPanel pnNullCenter = new JPanel();
        pnNullCenter.setPreferredSize(new Dimension(400, 200));
        pnNullCenter.setBackground(new Color(165, 213, 255));
        pnCenter.add(pnNullCenter);

        //Add panel -> panel East
        //Setting panel Exit and Minimize
        JPanel pnExitMinimize = new JPanel();
        pnExitMinimize.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnExitMinimize.setPreferredSize(new Dimension(400, 50));
        pnExitMinimize.setBackground(new Color(40, 82, 120));
        pnEast.add(pnExitMinimize);

        btnExit = new JButton();
        btnExit.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/exit_login.svg"))));
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setBorder(null);

        btnMinimize = new JButton();
        btnMinimize.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/cancel.svg"))));
        btnMinimize.setOpaque(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setBorder(null);

        pnExitMinimize.add(btnMinimize);
        pnExitMinimize.add(btnExit);

        //Setting panel logo
        JPanel pnLogo = new JPanel();
        pnLogo.setPreferredSize(new Dimension(400, 150));
        pnLogo.setBackground(new Color(40, 82, 120));
        pnEast.add(pnLogo);

        //Add logo to panel logo
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/manager_logo.svg"))));
        pnLogo.add(lblLogo);

        //Setting panel email
        JPanel pnEmail = new JPanel();
        pnEmail.setPreferredSize(new Dimension(400, 20));
        pnEmail.setBackground(new Color(40, 82, 120));
        pnEast.add(pnEmail);

        JLabel lblEmail = new JLabel();
        lblEmail.setText("Email");
        lblEmail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lblEmail.setForeground(Color.WHITE);
        pnEmail.add(lblEmail);

        //Setting add logo and email textfield
        JPanel pnEmailLogo = new JPanel();
        pnEmailLogo.setPreferredSize(new Dimension(400, 60));
        pnEmailLogo.setBackground(new Color(40, 82, 120));
        pnEmailLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnEast.add(pnEmailLogo);

        //Add logo and email textfield
        JLabel lblEmailLogo = new JLabel();
        lblEmailLogo.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/user.svg"))));
        pnEmailLogo.add(lblEmailLogo);

        txtEmail = new JTextField(18);
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setPreferredSize(new Dimension(20, 32));
        txtEmail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        txtEmail.setForeground(new Color(0, 0, 0));
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnEmailLogo.add(txtEmail);

        //Null panel email
        JPanel pnNullEmail = new JPanel();
        pnNullEmail.setPreferredSize(new Dimension(400, 20));
        pnNullEmail.setBackground(new Color(40, 82, 120));
        pnEast.add(pnNullEmail);

        //Setting panel password
        JPanel pnPassword = new JPanel();
        pnPassword.setPreferredSize(new Dimension(400, 20));
        pnPassword.setBackground(new Color(40, 82, 120));
        pnEast.add(pnPassword);

        JLabel lblPassword = new JLabel();
        lblPassword.setText("Password");
        lblPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lblPassword.setForeground(Color.WHITE);
        pnPassword.add(lblPassword);

        //Setting add logo and password textfield
        JPanel pnPasswordLogo = new JPanel();
        pnPasswordLogo.setPreferredSize(new Dimension(400, 50));
        pnPasswordLogo.setBackground(new Color(40, 82, 120));
        pnPasswordLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnEast.add(pnPasswordLogo);

        //Add logo and password textfield
        JLabel lblPassLogo = new JLabel();
        lblPassLogo.setIcon(new FlatSVGIcon(Objects.requireNonNull(LoginUI.class.getResource("/image/LoginUI/key_logo.svg"))));
        pnPasswordLogo.add(lblPassLogo);

        txtPassword = new JPasswordField(18);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setPreferredSize(new Dimension(20, 32));
        txtPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        txtPassword.setForeground(new Color(0, 0, 0));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnPasswordLogo.add(txtPassword);

        //Null panel password
        JPanel pnNullPass = new JPanel();
        pnNullPass.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnNullPass.setPreferredSize(new Dimension(400, 60));
        pnNullPass.setBackground(new Color(40, 82, 120));
        pnEast.add(pnNullPass);

        //Add check box show password
        cbShowPass = new JCheckBox();
        cbShowPass.setText("Show password");
        cbShowPass.setBackground(new Color(40, 82, 120));
        cbShowPass.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        cbShowPass.setForeground(Color.WHITE);
        cbShowPass.setOpaque(false);
        cbShowPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbShowPass.setBorder(null);
        cbShowPass.setFocusPainted(false);
        pnNullPass.add(cbShowPass);

        //Setting panel login
        JPanel pnLogin = new JPanel();
        pnLogin.setPreferredSize(new Dimension(400, 100));
        pnLogin.setBackground(new Color(40, 82, 120));
        pnEast.add(pnLogin);

        //Add button login to panel login
        btnLogin = new JButton();
        btnLogin.setText("   Login   ");
        btnLogin.setPreferredSize(new Dimension(150, 32));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnLogin.setForeground(new Color(40, 82, 120));
        btnLogin.setBorder(null);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        pnLogin.add(btnLogin);

        //Setting panel Null East
        JPanel pnNullEast = new JPanel();
        pnNullEast.setPreferredSize(new Dimension(400, 70));
        pnNullEast.setBackground(new Color(40, 82, 120));
        pnEast.add(pnNullEast);
    }

    private void addEvent() {
        //btn exit
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.addActionListener(e -> System.exit(0));

        //btn minimize
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMinimize.addActionListener(e -> setState(ICONIFIED));

        //check box show password
        cbShowPass.addActionListener(e -> {
            if (cbShowPass.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }
        });

        //btnLogin
        btnLogin.addActionListener(e -> {
            if (kiemTra(txtEmail.getText(), txtPassword.getText())) {
                setVisible(false);
                new mainUI();
            } else {
                JOptionPane.showMessageDialog(null, "email hoặc mật khẩu không đúng!", "Error!", JOptionPane.WARNING_MESSAGE);
            }
        });

        //Label URL Facebook
        lblFb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblFb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/dhbkhanoi"));
                } catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lblFb.setText("FACEBOOK");
                lblFb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lblFb.setText("");
            }
        });

        //Label URL linkedin
        lblLinkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLinkedin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/school/dhbkhn/"));
                } catch (URISyntaxException | IOException e2) {
                    e2.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lblLinkedin.setText("LINKEDIN");
                lblLinkedin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lblLinkedin.setText("");
            }
        });

        //Label URL Github
        lblGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/nhp1807/ProjectJavaFinal"));
                } catch (URISyntaxException | IOException e3) {
                    e3.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lblGithub.setText("GITHUB");
                lblGithub.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lblGithub.setText("");
            }
        });

        //Label URL Youtube
        lblYt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblYt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UCl17km6Ou3av5LClGz_FWIw"));
                } catch (URISyntaxException | IOException e4) {
                    e4.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lblYt.setText("YOUTUBE");
                lblYt.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lblYt.setText("");
            }
        });

        //Label URL mail
        lblMail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Desktop.getDesktop().browse(new URI("https://www.hust.edu.vn/phong-ban-chuc-nang-trung-tam"));
                } catch (URISyntaxException | IOException e5) {
                    e5.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lblMail.setText("MAIL");
                lblMail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lblMail.setText("");
            }
        });
    }

    public void showWindow() {
        setIconImages(FlatSVGUtils.createWindowIconImages(this.getClass().getResource("/image/LoginUI/icon.svg")));
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);
    }

    public boolean kiemTra(String user, String pass) {
        docDuLieu();
        for (Account account : dsTaiKhoan) {
            if (account.getUsername().equals(user) && account.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public void docDuLieu() {
        dsTaiKhoan = ServiceLogin.layDanhSachTaiKhoan();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new LoginUI("Login");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
