package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Account extends JFrame implements ActionListener {

    JButton buttonDo = new JButton();
    JButton buttonBack = new JButton();
    JFrame frameAccount = new JFrame();
    JPanel panel = new JPanel();
    JLabel peselLabel = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel cityLabel = new JLabel();
    JLabel streetLabel = new JLabel();
    JLabel houseNumberLabel = new JLabel();
    JLabel doorNumberLabel = new JLabel();
    JLabel postCodeLabel = new JLabel();
    JLabel idNumberLabel = new JLabel();
    JLabel idSeriesLabel = new JLabel();
    JLabel messageLabel = new JLabel();

    JTextField peselField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField cityField = new JTextField();
    JTextField streetField = new JTextField();
    JTextField houseNumberField = new JTextField();
    JTextField doorNumberField = new JTextField();
    JTextField postCodeField = new JTextField();
    JTextField idNumberField = new JTextField();
    JTextField idSeriesField = new JTextField();
    JTextField balanceField = new JTextField();
    ClientData client = new ClientData();


    //Wyżej ścieżka do pliku ww ktorym przechowywana jest wartość następnego dostępnego numeru konta
    //Plik znajduje sie w głownym katalogu aplikacji
    File file = new File("C:\\Users\\Developer\\Documents\\BankingApplication\\accnum.txt");

        Scanner inputFile = new Scanner(file);

    Account() throws FileNotFoundException {
        nameLabel.setText("Imię: ");
        nameLabel.setBounds(25, 80, 120, 25);
        frameAccount.add(nameLabel);

        nameField.setBounds(125, 80, 120, 25);
        frameAccount.add(nameField);

        lastNameLabel.setText("Nazwisko: ");
        lastNameLabel.setBounds(25, 110, 120, 25);
        frameAccount.add(lastNameLabel);

        lastNameField.setBounds(125, 110, 120, 25);
        frameAccount.add(lastNameField);

        peselLabel.setText("Pesel: ");
        peselLabel.setBounds(25, 140, 120, 25);
        frameAccount.add(peselLabel);

        peselField.setBounds(125, 140, 120, 25);
        frameAccount.add(peselField);

        cityLabel.setText("Miasto: ");
        cityLabel.setBounds(25, 170, 120, 25);
        frameAccount.add(cityLabel);

        cityField.setBounds(125, 170, 120, 25);
        frameAccount.add(cityField);

        streetLabel.setText("Ulica: ");
        streetLabel.setBounds(25, 200, 120, 25);
        frameAccount.add(streetLabel);

        streetField.setBounds(125, 200, 120, 25);
        frameAccount.add(streetField);

        houseNumberLabel.setText("Numer domu: ");
        houseNumberLabel.setBounds(300, 80, 120, 25);
        frameAccount.add(houseNumberLabel);

        houseNumberField.setBounds(425, 80, 120, 25);
        frameAccount.add(houseNumberField);

        doorNumberLabel.setText("Numer mieszkania: ");
        doorNumberLabel.setBounds(300, 110, 120, 25);
        frameAccount.add(doorNumberLabel);

        doorNumberField.setBounds(425, 110, 120, 25);
        frameAccount.add(doorNumberField);

        postCodeLabel.setText("Kod pocztowy: ");
        postCodeLabel.setBounds(300, 140, 120, 25);
        frameAccount.add(postCodeLabel);

        postCodeField.setBounds(425, 140, 120, 25);
        frameAccount.add(postCodeField);

        idSeriesLabel.setText("Seria dowodu: ");
        idSeriesLabel.setBounds(300, 170, 120, 25);
        frameAccount.add(idSeriesLabel);

        idSeriesField.setBounds(425, 170, 120, 25);
        frameAccount.add(idSeriesField);

        idNumberLabel.setText("Numer dowodu: ");
        idNumberLabel.setBounds(300, 200, 120, 25);
        frameAccount.add(idNumberLabel);

        idNumberField.setBounds(425, 200, 120, 25);
        frameAccount.add(idNumberField);

        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        messageLabel.setBounds(200, 280, 200, 50);
        messageLabel.setForeground(Color.BLACK);
        frameAccount.add(messageLabel);

        buttonDo.setBackground(Color.DARK_GRAY);
        buttonDo.setForeground(Color.WHITE);
        buttonDo.setBounds(150, 320, 100, 50);
        buttonDo.setFont(new Font("Arial", Font.BOLD, 14));
        buttonDo.setText("Wykonaj.");
        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                client.setName(nameField.getText());
                client.setLastName(lastNameField.getText());
                client.setCity(cityField.getText());
                client.setIdSeries(idSeriesField.getText());
                client.setStreet(streetField.getText());
                try {
                    client.setPesel(Integer.parseInt(peselField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                try {
                    client.setHouseNumber(Integer.parseInt(houseNumberField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                try {
                    client.setDoorNumber(Integer.parseInt(doorNumberField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                try {
                    client.setPostCode(Integer.parseInt(postCodeField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                try {
                    client.setIdNumber(Integer.parseInt(idNumberField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }

                String accnum = inputFile.nextLine();
                client.setAccountNumber(Long.parseLong(accnum));

                Connection con = null;
                Statement statement = null;


                try {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
                    if (con == null) {
                        System.out.println("Brak polączenia z bazą danych.");
                    } else {
                        System.out.println("Jest połączenie z bazą danych.");
                    }

                    statement = con.createStatement();
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO klient (Imie, Nazwisko, Pesel, Miasto, Kod_pocztowy, Ulica, Numer_domu, Numer_mieszkania, Seria_dowodu, Numer_dowodu, Numer_konta, Saldo)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

                    pstmt.setString(1, client.getName());
                    pstmt.setString(2, client.getLastName());
                    pstmt.setLong(3, client.getPesel());
                    pstmt.setString(4, client.getCity());
                    pstmt.setInt(5, client.getPostCode());
                    pstmt.setString(6, client.getStreet());
                    pstmt.setInt(7, client.getHouseNumber());
                    pstmt.setInt(8, client.getDoorNumber());
                    pstmt.setString(9, client.getIdSeries());
                    pstmt.setInt(10, client.getIdNumber());
                    pstmt.setLong(11, client.getAccountNumber());
                    pstmt.setInt(12, client.getBalance());
                    pstmt.executeUpdate();

                    messageLabel.setText("Dodano użytkownika.");
                    peselField.setText("");
                    nameField.setText("");
                    lastNameField.setText("");
                    cityField.setText("");
                    streetField.setText("");
                    houseNumberField.setText("");
                    doorNumberField.setText("");
                    postCodeField.setText("");
                    idNumberField.setText("");
                    idSeriesField.setText("");
                    balanceField.setText("");

                    long acc = client.getAccountNumber(); //zmienna w ktorej zapisuje numer konta aby nastepnie go inkrementować
                    acc++; //inkirementacja numeru konta aby uzyskac kolejny wolny
                    PrintWriter outputFile = new PrintWriter("C:\\Users\\Developer\\Documents\\BankingApplication\\accnum.txt");
                    outputFile.println(acc); // zapisywanie wolnego numeru konta do pliku
                    outputFile.close();// zamykanie pliku
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
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
        frameAccount.add(buttonDo);
        buttonBack.setBackground(Color.DARK_GRAY);
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBounds(300, 320, 100, 50);
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));
        buttonBack.setText("Wstecz.");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage home = new HomePage();
                frameAccount.dispose();
            }
        });
        frameAccount.add(buttonBack);
        panel.setBackground(Color.gray);
        panel.setPreferredSize(new Dimension(600, 450));
        frameAccount.add(panel);
        frameAccount.setSize(600, 450);
        frameAccount.setVisible(true);
        frameAccount.setLocationRelativeTo(null);
        frameAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}