import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class mainAccount {
    private static int userID;

    public mainAccount(int userID) {
        this.userID = userID;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int ID) {
        userID = ID;
    }

    public static void logIn() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Xin nhập ID: ");
            int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Xin nhập mật khẩu: ");
            String pc = scan.nextLine();
        if(SQL.checkID(id, pc)) setUserID(id);
        else System.out.println("Sai tài khoản hoặc sai mật khẩu.");
    }

    //Kiểm tra có phải là quản lí không
    public static boolean checkJob(){
        return Objects.equals(employeeManagement.allEmployee.get(employeeManagement.getEmployeeIdxById(userID)).jobTitle, "quản lí");
    }

    //đăng xuất
    public static void signOut(){
        setUserID(-1);
    }

    //đổi mật khẩu của tài khoản đang đăng nhập
    public static void changePass(String newPass) {
        if(SQL.changePassSQL(userID, newPass)){
            System.out.println("mật khẩu đã được đổi");
        } else{
            System.out.println("lỗi đổi mật khẩu");
        }
    }
}
