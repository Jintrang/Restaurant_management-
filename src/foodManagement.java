import java.util.ArrayList;
import java.util.List;

public class foodManagement {
    static int newFoodId = 0;
    static List<food> allFood = new ArrayList<>();

    //in tất cả các món ăn có trong danh sách
    public void printAllFood() {
        for(food f : allFood) {
            System.out.println(f.getInfor());
        }
    }

    public static int getFoodIdx(food f) {
        return allFood.indexOf(f);
    }

    public static int getFoodIdxById(int id) {
        for(food f : allFood) {
            if(f.getFoodId() == id) return getFoodIdx(f);
        }
        return -1;
    }

    public static void addFood(food f) {
        allFood.add(f);
    }

    public boolean removeFood(food f) {
        int idx = getFoodIdx(f);
        if(idx >= 0){
            allFood.remove(f);
            return true;
        } else return false;
    }

    public static boolean removeFoodByID(int id) {
        int idx = getFoodIdxById(id);
        if(idx >= 0){
            allFood.remove(idx);
            return true;
        } else return false;
    }

    public boolean fixFood(int id, String name, int price) {
        int idx = getFoodIdxById(id);
        if(idx>=0) {
            allFood.get(idx).setName(name);
            allFood.get(idx).setPrice(price);
            return true;
        }
        return false;
    }
}
