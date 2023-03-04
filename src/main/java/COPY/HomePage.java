package COPY;

import GUI.Account;
import GUI.IDandPasswords;
import GUI.LoginPage;
import GUI.WelcomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class HomePageCopy extends JFrame implements ActionListener {

    JFrame homeFrame = new JFrame();
    JTextField peselField = new JTextField();
    JLabel peselLabel = new JLabel("Wpisz pesel: ");
    JButton buttonCreate = new JButton();
    JButton buttonTransfer = new JButton();
    JButton buttonChangePassword = new JButton();

    JButton buttonLogout = new JButton();
    JButton buttonSearch = new JButton();
    JLabel messageLabel = new JLabel();

    HomePageCopy() {


        buttonCreate = new JButton("Stwórz konto.");
        buttonCreate.setBounds(0, 0, 100, 100);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  Account acc = new Account();
                homeFrame.dispose();
            }
        });

        buttonTransfer = new JButton("GUI.TransferWithoutAccount.");
        buttonTransfer.setBounds(0, 100, 100, 100);
        buttonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Account acc = new Account();
                homeFrame.dispose();
            }
        });

        buttonChangePassword = new JButton("Zmień hasło.");
        buttonChangePassword.setBounds(0, 200, 100, 100);
        buttonChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonLogout = new JButton("Wyloguj.");
        buttonLogout.setBounds(0, 300, 100, 100);
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    IDandPasswords idAndPass = new IDandPasswords();
              //  LoginPage loginPage = new LoginPage(idAndPass.getLoginInfo());
                homeFrame.dispose();
            }
        });

        homeFrame.add(buttonCreate);
        homeFrame.add(buttonChangePassword);
        homeFrame.add(buttonLogout);
        homeFrame.add(buttonTransfer);

        peselLabel.setBounds(125, 10, 200, 25);
        homeFrame.add(peselLabel);

        peselField.setBounds(225, 10, 200, 25);
        homeFrame.add(peselField);

        buttonSearch = new JButton("Szukaj.");
        buttonSearch.setBounds(435, 10, 100, 25);
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
int pesel=0;

                try {
                    pesel = Integer.parseInt(peselField.getText());
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
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
                   // String sql = "INSERT INTO klient (Imie, Nazwisko, Pesel, Miasto, Ulica, Numer_domu, Numer_mieszkania, Seria_dowodu, Numer_dowodu, Numer_konta)" +
                     //       " VALUES ('name')";

//                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO klient (Imie, Nazwisko, Pesel, Miasto, Ulica, Numer_domu, Numer_mieszkania, Seria_dowodu, Numer_dowodu, Numer_konta)" +
                      //      " VALUES (?,?,?,?,?,?,?,?,?,?)");

                    //pstmt.setString(1, name);
                    //pstmt.setString(2, lastName);
                    //pstmt.setLong(3, pesel);
                    //pstmt.setString(4, city);
                    //pstmt.setString(5, street);

                    //pstmt.setInt(6, houseNumber);
                    //pstmt.setInt(7, doorNumber);
                    //pstmt.setString(8, idSeries);
                    //pstmt.setInt(9, idNumber);
                    //pstmt.setLong(10, accountNumber);

                    //pstmt.executeUpdate();

                 //   PreparedStatement pstmt = con.prepareStatement("SELECT pesel FROM klient where pesel; ");

                   // String sqlSelect2 = "SELECT pesel FROM klient where pesel value == int pesel; ";
                    String sqlSelect = "SELECT * FROM klient WHERE Pesel " + Integer.toString(pesel);
                    resultSet = statement.executeQuery(sqlSelect);

                    while (resultSet.next()) {
                        int pesels = resultSet.getInt("pesel");
                      //  String nameS = resultSet.getString("Imie");

                        System.out.println("Pesel: " + pesels );

                        if (pesel == pesels)
                        {
                  //          WelcomePage welcomePage = new WelcomePage();
                            homeFrame.dispose();
                        } else {

                            messageLabel.setBounds(150, 75, 350, 35);
                            messageLabel.setFont(new Font(null, Font.ITALIC, 15));
                            messageLabel.setText("Błąd. Nie znaleziono klienta z tym peselem.");
                            homeFrame.add(messageLabel);
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

               // homeFrame.dispose();
            }
        });
        homeFrame.add(buttonSearch);

        homeFrame.setLayout(null);
        homeFrame.setSize(600, 450);
        homeFrame.setVisible(true);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}