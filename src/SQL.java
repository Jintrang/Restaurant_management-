import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

public class SQL {
    /**
     * url: dataname
     */
    public String url = "jdbc:mysql://localhost:3307/rtr";
    public String username = "root";
    public String password = "Seokjin0412";
    public static String tableEmployee = "employee";
    public static String tableBill = "bill";
    public static String tableBilldetails = "billdetails";
    public static String tableFood = "food";
    public static String tableDtable = "dtable";
    public static Connection connection;
    public static List<String> wordsList = new ArrayList<>();
    public static int num = 0;

    public SQL() throws SQLException {
        connect();
        mainAccount.logIn();
        getAllEmployees(allEmployees());
        getAllFoodsSQL(allFoods());
        //getWords(getAllWord());
        //wordList();
    }


    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            //num = getNumber();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public ResultSet allEmployees() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableEmployee;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    // thêm tất cả nhân viên vào danh sách
    public void getAllEmployees(ResultSet rs) throws SQLException {
        while (rs.next()) {
            employeeManagement.addEmployee(new employee(rs.getInt(1)
                    , rs.getString(2)
                    , rs.getString(3)
                    , Instant.ofEpochMilli(rs.getDate(4).getTime()).atZone(ZoneId.systemDefault()).toLocalDate()
                    , rs.getString(5)
                    , rs.getString(6)
                    , Instant.ofEpochMilli(rs.getDate(7).getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));
            //String nv = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                    //+ rs.getInt(4) + " " + rs.getDate(5);
            //System.out.println(nv);
        }
    }

    // thêm nhân viên
    public static void addEmployeeSQL(employee e) {
        String sqlCommand = "insert into " + tableEmployee + " value( ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst.setInt(1, e.eID);
            pst.setString(2, e.lastName);
            pst.setString(3, e.firstName);
            pst.setDate(4, java.sql.Date.valueOf(e.birthday));
            pst.setString(5, e.jobTitle);
            pst.setString(6, e.phoneNumber);
            pst.setDate(7, java.sql.Date.valueOf(e.joinDate));
            if (pst.executeUpdate() > 0) {
                employeeManagement.addEmployee(e);
                System.out.println("Thêm nhân viên thành công: " + e.eID);
            } else {
                System.out.println("Chưa thể thêm nhân viên!");
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    //xóa nhân viên
    public boolean deleteEmployees(int maNV) {
        String sqlCommand = "delete from " + tableEmployee + " where eID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, maNV);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã xóa");
                employeeManagement.removeEmployee(maNV);
            } else {
                System.out.println("Không có nhân viên cần xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // In món ăn
    public ResultSet allFoods() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableFood;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    public void getAllFoodsSQL(ResultSet rs) throws SQLException {
        while (rs.next()) {
           foodManagement.addFood(new food(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
    }

    //xóa món ăn
    public void deleteFood(int maMon) {
        String sqlCommand = "delete from " + tableFood + " where fID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, maMon);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã xóa món ăn");
                foodManagement.removeFoodByID(maMon);
            } else {
                System.out.println("Không có món ăn cần xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkID(int id, String pc) throws SQLException {
        ResultSet rs = checkID2(id, pc);
        //if(rs == null) return false;
            while (rs.next()) {
                if(Objects.equals(rs.getString(8), pc)) return true;
            }
        return false;
    }
    public static ResultSet checkID2(int id, String pc) {
        ResultSet rs = null;
        String sqlCommand = "select * from " + tableEmployee + " where eID = " + id;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    public static void addBilldetails(food x, int bID) {
        ResultSet rs = null;
        String sqlCommand = "INSERT INTO billdetails (fID, fPrice, bID) " +
                "VALUES (?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, x.fID);
            pst.setInt(2, x.fPrice);
            //pst.setString(3, "none");
            pst.setInt(3, bID);
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + pst.executeUpdate());
            } else {
                System.out.println("update billdetails error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBill(bill nBi) {
        ResultSet rs = null;
        String sqlCommand = "INSERT INTO bill (bID, totalMoney, discount, timeIn, eID) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, nBi.bID);
            pst.setInt(2, nBi.totalMoney);
            pst.setInt(3, 0);
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(nBi.timeIn));
            pst.setInt(5, nBi.eID);
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + pst.executeUpdate());
            } else {
                System.out.println("update billdetails error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void payBill(int bID, LocalDateTime payTime) {
        ResultSet rs = null;
        String sqlCommand = "UPDATE " + tableBill + " SET timePayment = ?" + " WHERE bID = ? ";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(2, bID);
            pst.setTimestamp(1, java.sql.Timestamp.valueOf(payTime) );
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + pst.executeUpdate());
            } else {
                System.out.println("update bill error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //nhận vào tất cả hóa đơn
    /*
    public ResultSet getAllBill() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + table6;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    // thêm từ vào Dictionary.Words
    public void getBill(ResultSet rs) throws SQLException {
        while (rs.next()) {
            quanliHoadon.hoaDons.add(new hoaDon(rs.getString(6), , , , ));
        }
    }*/




    public static void main(String[] args) throws SQLException {
        SQL myconnect = new SQL();
        billManagement bl = new billManagement();
        bl.newBill();
        myconnect.payBill(90, LocalDateTime.now());
        //myconnect.printAllEmployees(myconnect.allEmployees());
        //myconnect.addEmployee("Trần Thủy", "Nhân viên", 4635);
        //myconnect.printAllFoods(myconnect.allFoods());
        //myconnect.deleteEmployees(4);
        //myconnect.printAllEmployees(myconnect.allEmployees());
    }

}

