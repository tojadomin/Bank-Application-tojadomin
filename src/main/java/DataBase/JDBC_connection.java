package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_connection {

    public static void main(String[] args) {

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
            if (con == null) {
                System.out.println("Brak polączenia z bazą danych.");
            } else {
                System.out.println("Jest połączenie z bazą danych.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                con.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}