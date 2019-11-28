import java.util.*;
import java.sql.*;

class myinsert {
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
            System.out.println("Enter Student ID, name and branch");
            int id = sc.nextInt();
            String name = sc.next();
            String branch = sc.next();
            PreparedStatement pst = con.prepareStatement("insert into student values (?, ? ,?)");
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, branch);
            int x = pst.executeUpdate();
            if (x != 0) {
                System.out.println("data inserted succesfully");
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}