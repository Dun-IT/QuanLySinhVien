package code;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SplashScreen {
    JFrame frame;
    JLabel image = new JLabel(new ImageIcon(Objects.requireNonNull(SplashScreen.class.getResource("/image/SplashScreen/Loading800x510.gif"))));
    JProgressBar progressBar = new JProgressBar();

    SplashScreen() {
        createGUI();
        addImage();
        addProgressBar();
        runningPBar();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(800, 540);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(8, 49, 66));
        frame.setVisible(true);

    }

    public void addImage() {
        image.setSize(800, 510);
        frame.add(image);
    }

    public void addProgressBar() {
        progressBar.setBounds(0, 520, 800, 20);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(15, 84, 102));
        progressBar.setValue(0);
        frame.add(progressBar);
    }

    public void runningPBar() {
        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(10);//Pausing execution for 10 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                i++;
                if (i == 100)
                    frame.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new LoginUI("Login");
    }

    //Test
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new SplashScreen();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
