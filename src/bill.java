import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bill {
    static int billId;
    static List<food> selectedFoods;
    static List<String> foodNotes;
    static int totalMoney;
    static LocalDateTime timeIn;
    static LocalDateTime paymentTime;
    static int employeeID;
    int tableId;

    public bill() {
        selectedFoods = new ArrayList<>();
        foodNotes = new ArrayList<>();
        totalMoney = 0;
        this.timeIn = LocalDateTime.now();
        this.paymentTime = null;
        employeeID = mainAccount.getUserID();
    }
    public bill(int bID, List<food> selectedFoods, List<String> foodNotes
            , int totalMoney, LocalDateTime timeIn, LocalDateTime paymentTime, int employeeID, int tableId) {
        this.billId = bID;
        this.selectedFoods = selectedFoods;
        this.foodNotes = foodNotes;
        this.totalMoney = totalMoney;
        this.timeIn = timeIn;
        this.paymentTime = paymentTime;
        this.employeeID = employeeID;
        this.tableId = tableId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<food> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<food> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }

    public List<String> getFoodNotes() {
        return foodNotes;
    }

    public void setFoodNotes(List<String> foodNotes) {
        this.foodNotes = foodNotes;
    }

    public static int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public static int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public static void getFoodToList(int foodId) {
        selectedFoods.add(foodManagement.allFood.get(foodManagement.getFoodIdxById(foodId)));
    }

    //In ra thông tin hóa đơn
    public static void printInfoBill() {
        System.out.println("Nhân viên quầy: " + getEmployeeID() + " \n Thời gian vào: " + timeIn
                + " \n Thời gian thanh toán: " + paymentTime + " \n Tổng tiền " + getTotalMoney() );
        for(food f : selectedFoods) {
            System.out.println(f.getInfor());
        }
    }
}
