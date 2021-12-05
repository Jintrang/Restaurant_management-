import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class billManagement {
    public static int newBillId = 0;

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
            for (int j : fff) {
                food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(j));
                nb.selectedFoods.add(x);
                nb.totalMoney += x.price;
                SQL.addBilldetails(x, bill.billId);
            }
            //cập nhật tableManagement
            int xxx = ++newBillId;
            tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).setFoods(nb.selectedFoods);
            tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).setBillId(xxx);
            //cập nhật table SQL
            SQL.payBillTableSQL(tableId, xxx);
            SQL.addNewBill(new bill(xxx, nb.selectedFoods, nb.foodNotes
                    , nb.totalMoney, nb.timeIn, nb.paymentTime, nb.employeeID, tableId));
        }
    }

    //thanh toán hóa đơn cũ
    public static void payBill(int tableId){
        if(tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId == 0) System.out.println("Bàn này hiện tại không có hóa đơn");
        else{
            SQL.payBillSQL(tableManagement.tables.get(tableManagement.getIdxTableById(tableId)).billId);
            SQL.payBillTableSQL(tableId, 0);
            tableManagement.clearTable(tableId);
        }
    }

    //In thông tin hóa đơn theo mã hóa đơn
    public void printInfoBill(int billId) throws SQLException {
        bill b = SQL.getInfoBill(billId);
        b.printInfoBill();
    }

    //In thông tin hóa đơn theo ngày
    public static void printInfoBillByDate(LocalDate date) throws SQLException {
        List <bill>  bs = SQL.getInfoBillByDate(date);
        if(bs.size() == 0) System.out.print("Không có hóa đơn nào!");
        else {
            for (bill b : bs) {
                b.printInfoBill();
            }
        }
    }

    //thêm món ăn vào hóa đơn cũ + cập nhật lại giá bằng
    public static void addNewFoodToBill(int tableId) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Xin chọn món: ");
        String foods = scan.nextLine();
        String[] ff = foods.split(" ");
        int[] fff = new int[ff.length];
        for (int i = 0; i < ff.length; i++) {
            fff[i] = Integer.parseInt(ff[i]);
        }
        for (int j : fff) {
            food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(j));
            tableManagement.tables.get(tableId - 1).foods.add(x);
            SQL.addBilldetails(x, tableManagement.tables.get(tableId-1).getBillId());
        }

        int sum = 0;
        for(food f : tableManagement.tables.get(tableId - 1).foods) {
            sum += f.price;
        }

        SQL.updateBill(tableManagement.tables.get(tableId-1).getBillId(), sum);
    }

}
