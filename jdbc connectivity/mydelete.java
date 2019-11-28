import java.util.*;
import java.sql.*;

class mydelete {
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
            System.out.println("Enter Student ID which data you want to delete");
            int id = sc.nextInt();
            PreparedStatement pst = con.prepareStatement("delete from student where sid = ?");
            pst.setInt(1, id);
            int x = pst.executeUpdate();
            if (x != 0) {
                System.out.println("data deleted succesfully");
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}