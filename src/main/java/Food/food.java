package Food;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class food {
    private int foodId;
    private String name;
    private int price;
    private TextField quantity;
    private Button btn;
    private Button fixButton;
    private Button deleteButton;
    private Button orderDeleteButton;
    private TextField textPrice = new TextField();
    private TextField textName = new TextField();

    public food() {

    }
    public food(int foodId, String name, int price) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
    }

    public food(int foodId, String name, int price, TextField textField) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
        this.quantity = textField;
    }

    public food(int foodId, String name, int price, TextField textField, Button btn) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
        this.quantity = textField;
        this.btn = btn;
    }

    public food(int foodId, TextField textName, TextField textPrice, Button fixButton, Button deleteButton) {
        this.foodId= foodId;
        this.textName = textName;
        this.textPrice = textPrice;
        this.fixButton = fixButton;
        this.deleteButton = deleteButton;
    }

    public food(int foodId, String name, int price, TextField textField, Button btn, Button orderDeleteButton) {
        this.foodId= foodId;
        this.name = name;
        this.price = price;
        this.quantity = textField;
        this.btn = btn;
        this.orderDeleteButton = orderDeleteButton;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public TextField getTextName() {
        return textName;
    }

    public void setTextName(TextField textName) {
        this.textName = textName;
    }

    public TextField getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(TextField textPrice) {
        this.textPrice = textPrice;
    }

    public Button getFixButton() {
        return fixButton;
    }

    public void setFixButton(Button fixButton) {
        this.fixButton = fixButton;
    }

    public Button getOrderDeleteButton() {
        return orderDeleteButton;
    }

    public void setOrderDeleteButton(Button orderDeleteButton) {
        this.orderDeleteButton = orderDeleteButton;
    }

    public String getInfor() {
        return this.foodId + " " + this.name + "  vnd" + this.price;
    }

    public String toString() {
        return  this.name + "\n"
                + "Giá: " + this.price + " VNĐ" + "\n"
                + "Số lượng: " + this.quantity.getText();
    }
}