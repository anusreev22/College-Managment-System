package hafi.dbms.collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DataBase {

    private Connection connection = null;
    public static String currentDep = "";
    public static String userid = "";
    public static DataBase db = new DataBase();

    public DataBase() {
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/collegemanagment",
                    "root", "Hafi@1234");
        } catch (Exception e) {
        }
    }

    Boolean SignIn(String name, String pass) {
        String query = "select * from student_login where user_name='" + name + "' and password='" + pass + "';";
        System.out.print(query);
        try {
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                userid = name;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    Boolean SignInadmin(String name, String pass) {
        String query = "select * from admin_login where user_name='" + name + "' and password='" + pass + "';";
        System.out.print(query);
        try {
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                userid = name;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    Boolean SignUp(String uname, String password) {
        String query = "insert into student_login values('" + uname + "','" + password + "')";
        try {
            connection.createStatement().executeUpdate(query);
            userid = uname;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ArrayList getDetails() {
        String query = "select * from student join department on student.dept_id=department.Dept_id where id='" + userid + "';";
        ArrayList list = new ArrayList();
        try {
            ResultSet res = connection.createStatement().executeQuery(query);
            if (res.next()) {

                list.add(res.getString(2));
                list.add(res.getString(3));
                list.add(res.getString(4));
                list.add(res.getString(5));
                list.add(res.getString(6));
                list.add(res.getString(7));
                list.add(res.getString(10));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    Boolean addDetails(String fname, String lname, String age, String dob, String pnum, String addr, String deptid) {
        String query = "insert into student values('" + userid + "','" + fname + "','" + lname + "','" + age + "','" + dob + "','" + pnum + "','" + deptid + "','" + addr + "')";
        try {
            System.out.print(query);
            connection.createStatement().executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Boolean addDetailsteacher(String id, String fname, String lname, String age, String dob, String pnum, String addr, String deptid) {
        String query = "insert into teacher values('" + id + "','" + fname + "','" + lname + "','" + age + "','" + dob + "','" + pnum + "','" + dob + "','" + deptid + "')";
        try {
            System.out.print(query);
            connection.createStatement().executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ArrayList getCourses() {
        String query = "select * from cources where dept_id='" + currentDep + "'";
        ArrayList list = new ArrayList();
        try {
            ResultSet res = connection.createStatement().executeQuery(query);
            while (res.next()) {
                Object obj[] = {
                    res.getString(1),
                    res.getString(2)};
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    ArrayList getFaculty() {
        String query = "select * from teacher where dept_id='" + currentDep + "'";
        ArrayList list = new ArrayList();
        try {
            ResultSet res = connection.createStatement().executeQuery(query);
            while (res.next()) {
                Object obj[] = {
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7)};
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    ArrayList getStudent() {
        String query = "select * from student where dept_id='" + currentDep + "'";
        ArrayList list = new ArrayList();
        try {
            ResultSet res = connection.createStatement().executeQuery(query);
            while (res.next()) {
                Object obj[] = {
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7)};
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
