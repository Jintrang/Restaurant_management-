import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class bill {
    int bID;
    List<food> selectedFoods;
    List<String> foodNotes;
    int totalMoney;
    LocalDateTime timeIn;
    LocalDateTime paymentTime;
    int eID;

    public bill() {
        selectedFoods = new ArrayList<>();
        foodNotes = new ArrayList<>();
        totalMoney = 0;
        this.timeIn = LocalDateTime.now();
        this.paymentTime = null;
        eID = mainAccount.getUserID();
    }
    public bill(int bID, List<food> selectedFoods, List<String> foodNotes
            , int totalMoney, LocalDateTime timeIn, LocalDateTime paymentTime, int eID) {
        this.bID = bID;
        this.selectedFoods = selectedFoods;
        this.foodNotes = foodNotes;
        this.totalMoney = totalMoney;
        this.timeIn = timeIn;
        this.paymentTime = paymentTime;
        this.eID = eID;
    }

    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
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

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }
}
