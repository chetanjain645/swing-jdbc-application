import java.util.*;
import java.sql.*;

class myview {
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
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Student ID which data you want to show");
            int id = sc.nextInt();
            PreparedStatement pst = con.prepareStatement("select * from student where sid  = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(
                        "ID = " + rs.getInt(1) + " Name  = " + rs.getString(2) + " BRANCH = " + rs.getString(3));
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}