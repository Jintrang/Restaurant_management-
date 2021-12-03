import java.sql.SQLException;
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
        System.out.println("Xin nhập mật khẩu: ");
            String pc = scan.nextLine();
        if(SQL.checkID(id, pc)) setUserID(id);
        else System.out.println("Sai tài khoản hoặc sai mật khẩu.");
    }
}
