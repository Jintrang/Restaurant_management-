import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class billManagement {
    public static int newBillId = 0;
    public static List<bill>bills = new ArrayList<>();

    public static void addBillToList(bill b) {
        bills.add(b);
    }

    public int getNewBillId() {
        return newBillId;
    }

    public void setNewBillId(int newBillId) {
        billManagement.newBillId = newBillId;
    }

    //tạo một hóa đơn mới
    public static void createNewBill() {
        Scanner scan = new Scanner(System.in);
        List<Integer> emptyTable = tableManagement.getEmptyTable();
        if (emptyTable.size() == 0) System.out.print("Đã hết bàn");
        else {
            //in ra các bàn còn trống
            for (int i : emptyTable)
                System.out.print("Bàn " + i + " - ");
            //chọn bàn
            System.out.print("Xin chọn bàn: ");
            int tableId = scan.nextInt();
            scan.nextLine();
            System.out.println( tableId);
            //chọn món: các mã món ăn sẽ nối thành một chuỗi cách nhau bởi dấu " " vd: "1 2 3 4 5"
            System.out.println("Xin chọn món: ");
            String foods = scan.nextLine();
            String[] ff = foods.split(" ");
            int[] fff = new int[ff.length];
            for (int i = 0; i < ff.length; i++) {
                fff[i] = Integer.parseInt(ff[i]);
            }

            bill nb = new bill();
            //cập nhật tableManagement
            int xxx = ++newBillId;
            for (int j : fff) {
                food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(j));
                nb.selectedFoods.add(x);
                nb.totalMoney += x.price;
                SQL.addBilldetails(x, xxx);
            }
            tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).setFoods(nb.selectedFoods);
            tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).setBillId(xxx);
            //cập nhật table SQL
            SQL.updateBillOfTableSQL(tableId, xxx);
            bill newBill = new bill(xxx, nb.selectedFoods, nb.foodNotes
                    , nb.totalMoney, nb.timeIn, nb.paymentTime, nb.employeeID, tableId);
            bills.add(newBill);
            SQL.addNewBill(newBill);
        }
    }

    //thanh toán hóa đơn cũ
    public static void payBill(int tableId){
        if(tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId == 0) System.out.println("Bàn này hiện tại không có hóa đơn");
        else{
            LocalDateTime dt = LocalDateTime.now();
            int bId = tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId;
            SQL.payBillSQL(bId, dt);
            SQL.updateBillOfTableSQL(tableId, 0);
            bills.get(getIdxBillById(bId)).setPaymentTime(dt);
            tableManagement.clearTable(tableId);
        }
    }

    public static int getIdxBillById(int billId) {
        for(int i = 0; i< bills.size(); i++) {
            if(bills.get(i).getBillId() == billId) return i;
        }
        return -1;
    }

    //In thông tin hóa đơn theo mã hóa đơn
    public void printInfoBill(int billId) throws SQLException {
        bill b = SQL.getInfoBill(billId);
        assert b != null;
        b.printInfoBill();
    }

    //In thông tin hóa đơn theo ngày
    public static void printInfoBillByDate(LocalDate date) throws SQLException {
        for (bill b : bills) {
            //System.out.println(b.timeIn.toLocalDate());
            if (Objects.equals(b.timeIn.toLocalDate(), date)) {
                b.printInfoBill();
                System.out.println("------------------\n-----------------------");
            }
            //}
        }
    }

    //thêm món ăn vào hóa đơn cũ + cập nhật lại giá bằng
    public static void addNewFoodToBill(int tableId) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Xin chọn món: ");
        String foods = scan.nextLine();
        String[] ff = foods.split(" ");
        int[] fff = new int[ff.length];
        int bId = tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).getBillId();
        for (int i = 0; i < ff.length; i++) {
            fff[i] = Integer.parseInt(ff[i]);
        }
        for (int j : fff) {
            food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(j));
            tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).foods.add(x);
            bills.get(getIdxBillById(bId)).selectedFoods.add(x);
            SQL.addBilldetails(x, tableManagement.tables.get(tableId-1).getBillId());
        }

        int sum = 0;
        for(food f : tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).foods) {
            sum += f.price;
        }
        bills.get(getIdxBillById(bId)).setTotalMoney(sum);
        SQL.updateBill(bId, sum);
    }

}
