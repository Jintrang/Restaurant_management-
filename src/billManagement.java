import java.util.List;
import java.util.Scanner;

public class billManagement {
    public void newBill(){
        bill nBill = new bill();
        Scanner scan = new Scanner(System.in);
        System.out.println("Xin chọn món: ");
            String foods = scan.nextLine();
        System.out.println("Mời nhập mã hóa đơn mới: ");
            nBill.bID = scan.nextInt();
        String[] ff = foods.split(" ");
        int[] fff = new int[ff.length];
        for(int i = 0; i < ff.length; i++) {
            fff[i] =Integer.parseInt(ff[i]);
        }

        for(int i = 0; i<fff.length; i++) {
            food x = foodManagement.allFood.get(foodManagement.getFoodIdxById(fff[i]));
            nBill.selectedFoods.add(x);
            nBill.totalMoney += x.fPrice;
            SQL.addBilldetails(x,nBill.bID);
        }

        SQL.addBill(nBill);

    }
}
