/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.StudentBean;

/**
 *
 * @author Andrew Amen
 */
public class StudentDAO {

    public int createProfile(StudentBean aStudentBean) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO ITKSTU.Students VALUES ('"
                    + aStudentBean.getUserName()
                    + "','" + aStudentBean.getPassword()
                    + "','" + aStudentBean.getFirstName()
                    + "','" + aStudentBean.getLastName()
                    + "','" + aStudentBean.getEmail()
                    + "','" + aStudentBean.getAddress()
                    + "','" + aStudentBean.getCity()
                    + "','" + aStudentBean.getState()
                    + "','" + aStudentBean.getHighSchool()
                    + "','" + aStudentBean.getAct()
                    + "','" + aStudentBean.getGpa()
                    + "','" + aStudentBean.getExtra()
                    + "','" + aStudentBean.getMajor()
                    + "','" + aStudentBean.getStatement()
                    + "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    public ArrayList findAll() {

        String query = "SELECT * FROM ITKSTU.Students";
        ArrayList aStudentBeanCollection = selectProfilesFromDB(query);
        return aStudentBeanCollection;

    }

    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aStudentBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/Project353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String lastName, firstName, userName, password, email, address, city, state, act, gpa, extra, statement, major, highSchool;
            StudentBean aStudentBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                lastName = rs.getString("LastName");
                firstName = rs.getString("FirstName");
                userName = rs.getString("UserName");
                password = rs.getString("Password");
                email = rs.getString("Email");
                address = rs.getString("Address");
                city = rs.getString("City");
                state = rs.getString("State");
                act = rs.getString("ACT");
                gpa = rs.getString("GPA");
                extra = rs.getString("Extra");
                major = rs.getString("Major");
                statement = rs.getString("Statement");
                highSchool = rs.getString("HighSchool");

                // make a ProfileBean object out of the values
                aStudentBean = new StudentBean(lastName, firstName, userName, password, email, address, city, state, highSchool, act, gpa, extra, major, statement);
                // add the newly created object to the collection
                aStudentBeanCollection.add(aStudentBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aStudentBeanCollection;
    }

    public ArrayList findByUserName(String aName) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM ITKSTU.Students ";
        query += "WHERE UserName = '" + aName + "'";

        ArrayList aStudentBeanCollection = selectProfilesFromDB(query);
        return aStudentBeanCollection;
    }

    public int updateProfile(StudentBean pro) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String updateString;
            Statement stmt = DBConn.createStatement();

            // SQL UPDATE Syntax [http://www.w3schools.com]:
            // UPDATE table_name
            // SET column1=value, column2=value2,...
            // WHERE some_column=some_value
            // Note: Notice the WHERE clause in the UPDATE syntax. The WHERE clause specifies which record or records that should be updated. If you omit the WHERE clause, all records will be updated!
            updateString = "UPDATE ITKSTU.Students SET "
                    + "FirstName = '" + pro.getFirstName() + "', "
                    + "LastName = '" + pro.getLastName() + "', "
                    + "Password = '" + pro.getPassword() + "', "
                    + "Email = '" + pro.getEmail() + "', "
                    + "Address = '" + pro.getAddress() + "', "
                    + "City = '" + pro.getCity() + "' "
                    + "State = '" + pro.getState() + "' "
                    + "HighSchool = '" + pro.getHighSchool() + "' "
                    + "ACT = '" + pro.getAct() + "' "
                    + "GPA = '" + pro.getGpa() + "' "
                    + "Extra = '" + pro.getExtra() + "' "
                    + "Major = '" + pro.getMajor() + "' "
                    + "Statement = '" + pro.getStatement() + "' "
                    + "WHERE UserName = '" + pro.getUserName() + "'";
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("updateString =" + updateString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;

    }
}