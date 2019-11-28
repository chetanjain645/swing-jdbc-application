import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class testing extends JFrame implements ActionListener {
  JTextField idfield, namefield, addressfield, mobilefield, cityfield;
  JComboBox idcombo;
  JLabel L;

  testing() {
    idcombo = new JComboBox();
    idcombo.setBounds(430, 150, 100, 25);
    JLabel L = new JLabel("Data Testing Window");
    L.setFont(new Font("Comic Sans MS", Font.BOLD, 46));
    L.setBounds(175, 40, 500, 70);
    L.setForeground(Color.red);
    JLabel userid = new JLabel("User ID");
    userid.setBounds(175, 150, 50, 25);
    JLabel name = new JLabel("Name");
    name.setBounds(175, 190, 50, 25);
    JLabel address = new JLabel("Address");
    address.setBounds(175, 230, 50, 25);
    JLabel city = new JLabel("City");
    city.setBounds(175, 270, 50, 25);
    JLabel mobile = new JLabel("Mobile");
    mobile.setBounds(175, 310, 50, 25);

    idfield = new JTextField();
    idfield.setBounds(300, 150, 100, 25);
    namefield = new JTextField();
    namefield.setBounds(300, 190, 250, 25);
    addressfield = new JTextField();
    addressfield.setBounds(300, 230, 250, 25);
    cityfield = new JTextField();
    cityfield.setBounds(300, 270, 250, 25);
    mobilefield = new JTextField();
    mobilefield.setBounds(300, 310, 250, 25);

    JButton insert = new JButton("Insert");
    insert.setBounds(175, 375, 75, 25);
    JButton delete = new JButton("Delete");
    delete.setBounds(275, 375, 75, 25);
    JButton view = new JButton("View");
    view.setBounds(375, 375, 75, 25);
    JButton update = new JButton("Update");
    update.setBounds(475, 375, 75, 25);
    JButton exit = new JButton("Exit");
    exit.setBounds(575, 375, 75, 25);
    JButton clear = new JButton("Clear");
    clear.setBounds(350, 425, 125, 35);

    add(L);
    add(userid);
    add(name);
    add(address);
    add(mobile);
    add(city);
    add(idfield);
    add(namefield);
    add(addressfield);
    add(mobilefield);
    add(cityfield);
    add(insert);
    add(delete);
    add(view);
    add(update);
    add(exit);
    add(clear);
    add(idcombo);

    idcombo.addActionListener(this);
    insert.addActionListener(this);
    delete.addActionListener(this);
    view.addActionListener(this);
    update.addActionListener(this);
    exit.addActionListener(this);
    clear.addActionListener(this);

    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded successfully");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample?user=testuser&password=securepwd");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select user_id from datatesting");
      idcombo.addItem("-Select-");
      while (rs.next()) {
        idcombo.addItem(rs.getString(1));
      }
    } catch (Exception e) {
    }
    idfield.setText("");

    setSize(800, 600);
    setLayout(null);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == idcombo) {
      idfield.setText(idcombo.getSelectedItem().toString());
    }

    else if (evt.getActionCommand() == "Exit") {
      System.exit(1);
    }

    else if (evt.getActionCommand() == "Clear") {
      /*
       * idfield.setEditable(true); namefield.setEditable(true);
       * addressfield.setEditable(true); cityfield.setEditable(true);
       * mobilefield.setEditable(true);
       */
      idfield.setText("");
      namefield.setText("");
      addressfield.setText("");
      cityfield.setText("");
      mobilefield.setText("");
      this.dispose();
      new testing();
    }

    else if (evt.getActionCommand() == "Insert") {

      try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample?user=testuser&password=securepwd");
        PreparedStatement pstmt = con.prepareStatement("insert into datatesting values(?,?,?,?,?)");

        String s1, s2, s3, s4, s5;
        s1 = idfield.getText();
        s2 = namefield.getText();
        s3 = addressfield.getText();
        s4 = cityfield.getText();
        s5 = mobilefield.getText();

        if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
          JOptionPane.showMessageDialog(this, "Fill all the field first.");
        } else {
          pstmt.setInt(1, Integer.parseInt(s1));
          pstmt.setString(2, s2);
          pstmt.setString(3, s3);
          pstmt.setString(4, s4);
          pstmt.setInt(5, Integer.parseInt(s5));
          pstmt.executeUpdate();

          JOptionPane.showMessageDialog(this, "Record inserted successfully...");
          idfield.setText("");
          namefield.setText("");
          addressfield.setText("");
          cityfield.setText("");
          mobilefield.setText("");
          con.close();
          this.dispose();
          new testing();
        }
      }

      catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Record on this User ID allready exists.");
      }

      catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Record can not inserted " + e.getMessage());
      }

    }

    else if (evt.getActionCommand() == "Delete") {

      try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample?user=testuser&password=securepwd");
        PreparedStatement pstmt = con.prepareStatement("delete from datatesting where user_id =?");
        String s = idfield.getText();
        if (s.equals("")) {
          JOptionPane.showMessageDialog(this, "Enter user Id first.");
        } else {
          JOptionPane.showMessageDialog(this, "Are you sure to delete the record");

          pstmt.setInt(1, Integer.parseInt(s));
          pstmt.executeUpdate();

          con.close();
          JOptionPane.showMessageDialog(this, "Record deleted successfully...");
          idfield.setText("");
          namefield.setText("");
          addressfield.setText("");
          cityfield.setText("");
          mobilefield.setText("");

          idfield.setEditable(true);
          namefield.setEditable(true);
          addressfield.setEditable(true);
          cityfield.setEditable(true);
          mobilefield.setEditable(true);
        }
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Record does not exists.");
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Record can not Deleted:" + e.getMessage());
      }
      this.dispose();
      new testing();
    }

    else if (evt.getActionCommand() == "View") {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample?user=testuser&password=securepwd");
        PreparedStatement pstmt = con.prepareStatement("select * from datatesting where user_id=?");
        String s = idfield.getText();

        if (s.equals("")) {
          JOptionPane.showMessageDialog(this, "Enter the User_Id first.");
        }

        else {
          pstmt.setInt(1, Integer.parseInt(s));
          ResultSet rs = pstmt.executeQuery();

          if (rs.next()) {
            idfield.setText(rs.getString(1));
            namefield.setText(rs.getString(2));
            addressfield.setText(rs.getString(3));
            cityfield.setText(rs.getString(4));
            mobilefield.setText(rs.getString(5));
            rs.close();

            /*
             * idfield.setEditable(false); namefield.setEditable(false);
             * addressfield.setEditable(false); cityfield.setEditable(false);
             * mobilefield.setEditable(false);
             */
          } else {
            JOptionPane.showMessageDialog(this, "Record doesnot exists");
          }
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error Found" + e.getMessage());
      }

    } else if (evt.getActionCommand() == "Update") {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded successfully");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sample?user=testuser&password=securepwd");
        PreparedStatement pstmt = con.prepareStatement(
            "update datatesting set user_name=?,user_address=?,user_city=?,user_contect=? where user_id=?");
        pstmt.setString(1, namefield.getText());
        pstmt.setString(2, addressfield.getText());
        pstmt.setString(3, cityfield.getText());
        pstmt.setInt(4, Integer.parseInt(mobilefield.getText()));
        pstmt.setInt(5, Integer.parseInt(idfield.getText()));
        pstmt.executeUpdate();
        con.close();
        JOptionPane.showMessageDialog(this, "Record updated successfully...");
        idfield.setText("");
        namefield.setText("");
        addressfield.setText("");
        cityfield.setText("");
        mobilefield.setText("");
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Record can not update" + e.getMessage());
      }

    }

  }

  public static void main(String[] s) {
    new testing();
  }

}