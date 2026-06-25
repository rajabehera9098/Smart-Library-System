import javax.swing.*;
import java.awt.*;

public class LoginForm {

    public LoginForm(){

        JFrame frame = new JFrame("Library Login");

        JLabel title = new JLabel("Smart Library Management System");
        title.setFont(new Font("Arial",Font.BOLD,22));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        title.setBounds(40,30,350,40);

        userLabel.setBounds(50,100,100,30);
        userField.setBounds(150,100,180,30);

        passLabel.setBounds(50,150,100,30);
        passField.setBounds(150,150,180,30);

        loginBtn.setBounds(150,220,120,35);

        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);

        frame.setSize(400,350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginBtn.addActionListener(e -> {

            frame.dispose();
            new MainDashboard();

        });
    }

    public static void main(String[] args){

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginForm();

    }
}