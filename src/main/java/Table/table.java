package Table;

import Food.food;

import java.util.ArrayList;
import java.util.List;

public class table {
    private int tableId;
    private int billId;
    private List<food> foods = new ArrayList<>();
    private int curPrice;


    public table() {

    }

    public table(int tableId, int billId) {
        this.tableId = tableId;
        this.billId = billId;
    }

    public table(int tableId, int billId, List<food> foods) {
        this.tableId = tableId;
        this.billId = billId;
        this.foods = foods;
    }

    public void addSanPham(food sp) {
        this.foods.add(sp);
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<food> getFoods() {
        return foods;
    }

    public void setFoods(List<food> foods) {
        this.foods = foods;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(int curPrice) {
        this.curPrice = curPrice;
    }
}