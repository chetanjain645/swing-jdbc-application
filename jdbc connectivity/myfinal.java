import java.util.*;
import java.sql.*;

class myfirst {
    public static void main(String S[]) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load MySQL Driver");
        }

        String jdbcUrl = "jdbc:mysql://localhost/sample?user=testuser&password=securepwd";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("connected");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student");
            while (rs.next()) {
                System.out.println(
                        "ID = " + rs.getInt(1) + " Name  = " + rs.getString(2) + " BRANCH = " + rs.getString(3));
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}