package Bill;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Food.*;
import SQL.SQL;
import employees.employee;

public class billManagement {
    //allBill lấy dữ liệu bill từ trong sql
    public static List<bill> allBill = new ArrayList<>();
    // billList chỉ dùng khi chương trình chạy. Khi tắt chương trình các dữ liệu ở trong billList sẽ mất
    public static List<bill> billList = new ArrayList<>();
    public static int newBillId = 0;

    public static void addBill(bill b) {
        int dem = 0;
        for(bill bill : allBill) {
            if (bill.getBillId() == b.getBillId()) {
                dem++;
            }
        }
        if (dem == 0) {
            allBill.add(b);
        }
    }
}