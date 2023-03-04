package DataBase;

import javax.xml.transform.Result;
import java.sql.*;


public class JDBC_select {

    public static void main(String[] args) {

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/udemy", "root", "");
            if (con == null) {
                System.out.println("Brak polączenia z bazą danych.");
            } else {
                System.out.println("Jest połączenie z bazą danych.");
            }

            statement = con.createStatement();
            String sql = "SELECT * FROM employees; ";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String adress = resultSet.getString("adress");
                int salary = resultSet.getInt("salary");
                System.out.println("id: "+id+" name: "+name+" salary: "+salary);

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
}