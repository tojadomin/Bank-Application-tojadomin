package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;


public class LoginPage implements ActionListener {

    private JFrame frame = new JFrame();
    JButton loginButton = new JButton("Zaloguj.");
    JButton resetButton = new JButton("Resetuj.");
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel userLoginLabel = new JLabel("Login:");
    JLabel userPasswordLabel = new JLabel("Hasło:");
    JLabel messageLabel = new JLabel();
    JPanel panel = new JPanel();

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    LoginPage(HashMap<String, String> loginInfoMap) {
        loginInfo = loginInfoMap;

        userLoginLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(50, 250, 350, 35);
        messageLabel.setFont(new Font(null, Font.BOLD, 24));

        loginField.setBounds(125, 100, 200, 25);
        passwordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.WHITE);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.WHITE);

        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(450, 450));

        frame.add(resetButton);
        frame.add(loginButton);
        frame.add(loginField);
        frame.add(passwordField);
        frame.add(messageLabel);
        frame.add(userLoginLabel);
        frame.add(userPasswordLabel);
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            loginField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == loginButton) {

            String login = loginField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (loginInfo.containsKey(login)) {
                if (loginInfo.get(login).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Zalogowano!");
                    frame.dispose();
                    HomePage homePage = new HomePage();
                    //      WelcomePage welcomePage = new WelcomePage();

                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("              Błędne hasło.");

                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Nie znaleziono użytkownika.");
            }

        }
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
