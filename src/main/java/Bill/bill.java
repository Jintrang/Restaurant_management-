package Bill;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Food.*;
import login.mainAccount;

public class bill {
    private int billId;
    private List<food> selectedFoods = new ArrayList<>();
    private List<String> foodNotes;
    public int totalMoney;
    private LocalDateTime timeIn;
    private LocalDateTime paymentTime;
    private int employeeId;
    private int discount;

    public int newBillId = 0;


    public bill() {
        employeeId = mainAccount.getUserID();
    }

    public bill(int id, int totalMoney, int discount, LocalDateTime timeIn, LocalDateTime paymentTime, int employeeId) {
        this.billId = id;
        this.totalMoney = totalMoney;
        this.discount = discount;
        this.timeIn = timeIn;
        this.paymentTime = paymentTime;
        this.employeeId =employeeId;
    }

    public bill(int bID, List<food> selectedFoods, List<String> foodNotes
            , int totalMoney, LocalDateTime timeIn, LocalDateTime paymentTime, int employeeId) {
        this.billId = bID;
        this.selectedFoods = selectedFoods;
        this.foodNotes = foodNotes;
        this.totalMoney = totalMoney;
        this.timeIn = timeIn;
        this.paymentTime = paymentTime;
        this.employeeId = billId;
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

    public int getTotalMoney() {
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    // thêm 1 sản phẩm mới vào bill
    public void addNewFood(food food) {
        this.selectedFoods.add(food);
    }

    public void deleteFood(food food) {
        this.selectedFoods.remove(food);
    }

    public void getFoodToList(int foodId) {
        for(food food : foodManagement.allFood) {
            if (food.getFoodId() == foodId) {
                this.selectedFoods.add(food);
            }
        }
    }
}