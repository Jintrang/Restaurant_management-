package Food;

import employees.employee;

import java.util.ArrayList;
import java.util.List;

public class foodManagement {
    public static List<food> allFood = new ArrayList<>();


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
        int dem = 0;
        for(food food : allFood) {
            if (food.getFoodId() == f.getFoodId()) {
                dem++;
            }
        }
        if (dem == 0) {
            allFood.add(f);
        }
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