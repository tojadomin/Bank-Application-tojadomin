package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.*;

public class HomePage extends JFrame implements ActionListener {

     static int peselValueFromField = 0;

    JFrame homeFrame = new JFrame();
    JTextField peselField = new JTextField();
    JLabel peselLabel = new JLabel("Wpisz pesel: ");
    JButton buttonCreate = new JButton("Stwórz konto");
    JButton buttonTransfer = new JButton("Wpłata bez konta");
    JButton buttonLogout = new JButton("Wyloguj");
    JButton buttonSearch = new JButton("Wyszukaj klienta");
    JLabel messageLabel = new JLabel();
    JPanel panel = new JPanel();

    HomePage() {
        peselField.setBounds(325, 10, 200, 25);
        peselLabel.setBounds(225, 10, 200, 25);

        buttonCreate.setBackground(Color.DARK_GRAY);
        buttonCreate.setForeground(Color.WHITE);
        buttonCreate.setBounds(0, 0, 200, 100);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Account acc = new Account();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                homeFrame.dispose();
            }
        });
        buttonTransfer.setBackground(Color.DARK_GRAY);
        buttonTransfer.setForeground(Color.WHITE);
        buttonTransfer.setBounds(0, 100, 200, 100);
        buttonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferWithoutAccount transferWithoutAccount = new TransferWithoutAccount();
                homeFrame.dispose();
            }
        });
        buttonLogout.setBackground(Color.DARK_GRAY);
        buttonLogout.setForeground(Color.WHITE);
        buttonLogout.setBounds(0, 200, 200, 100);
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDandPasswords idAndPass = new IDandPasswords();
                LoginPage loginPage = new LoginPage(idAndPass.getLoginInfo());
                homeFrame.dispose();
            }
        });
        buttonSearch.setBackground(Color.DARK_GRAY);
        buttonSearch.setForeground(Color.WHITE);
        buttonSearch.setBounds(535, 10, 200, 25);
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                try {
                    peselValueFromField = Integer.parseInt(peselField.getText());
                } catch (NumberFormatException e) {
//dopisać
                }
                Connection con = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
                    if (con == null) {
                        System.out.println("Brak polączenia z bazą danych.");
                    } else {
                        System.out.println("Jest połączenie z bazą danych.");
                    }
                    statement = con.createStatement();
                    String sqlSelect = "SELECT * FROM klient";
                    resultSet = statement.executeQuery(sqlSelect);
                    while (resultSet.next()) {
                        int pesel = resultSet.getInt("Pesel");
                        if (peselValueFromField == pesel && peselValueFromField !=0) {
                            WelcomePage welcomePage = new WelcomePage();
                            homeFrame.dispose();
                        } else {
                            messageLabel.setBounds(220, 255, 540, 35);
                            messageLabel.setFont(new Font(null, Font.BOLD, 26));
                            messageLabel.setForeground(Color.RED);
                            messageLabel.setText("Błąd! Nie znaleziono klienta z tym peselem.");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        statement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        panel.setBackground(Color.gray);

        homeFrame.add(messageLabel);
        homeFrame.add(peselLabel);
        homeFrame.add(peselField);
        homeFrame.add(buttonSearch);
        homeFrame.add(buttonCreate);
        homeFrame.add(buttonLogout);
        homeFrame.add(buttonTransfer);
        homeFrame.add(panel);
        homeFrame.setSize(800, 338);
        homeFrame.setVisible(true);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}