import java.util.ArrayList;
import java.util.List;

public class table {
    int tableId;
    int billId;
    List<food> foods = new ArrayList<>();


    public table(int tableId, int billId, List<food> foods) {
        this.tableId = tableId;
        this.billId = billId;
        this.foods = foods;
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
}
