import java.util.*;
import java.sql.*;

class myupdate {
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
            System.out.println("Enter Student ID which data you want to update");
            int id = sc.nextInt();
            System.out.println("Enter name and branch for updation");
            String name = sc.next();
            String branch = sc.next();

            System.out.println("you have entered name = " + name + " and branch = " + branch);

            PreparedStatement pst = con.prepareStatement("update student set s_name = ? ,s_branch = ? where sid = ?");
            pst.setString(1, name);
            pst.setString(2, branch);
            pst.setInt(3, id);
            int x = pst.executeUpdate();
            if (x != 0) {
                System.out.println("data updated succesfully");
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}