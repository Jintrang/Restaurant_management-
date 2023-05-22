package Order;

import Bill.*;
import Food.*;
import SQL.SQL;
import Alert.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Table.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Bill.billManagement.billList;
import static Table.checkTable.tableList;
import static Table.tableManagement.clearTable;

//import static Table.checkTable.table_list;

public class Controller implements Initializable {

    @FXML
    private TableColumn<food, Number> fID_Column;

    @FXML
    private TableColumn<food, String> fName_Column;

    @FXML
    private TableColumn<food, Button> fButton_Column;

    @FXML
    private TableColumn<food, TextField> fQuantity_Column;

    @FXML
    private TableColumn<food, Number> fPrice_Column;

    @FXML
    private TableColumn<food, Button> fDelete_Column;

    @FXML
    private TableView<food> foodTableView;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label billPrice;

    @FXML
    private Button quayLai_btn;

    @FXML
    private Button checkBill_btn;


    private ObservableList<food> tableView_list;

    public static List<food> foodList = new ArrayList<>();
    public static SQL SQL;
    public static int code;
    public static List<Button> foodButtonList = new ArrayList<>(100);
    public static List<Button> deleteButtonList = new ArrayList<>(100);
    public static List<Integer> quantityList = new ArrayList<>(100);

    public List<Integer> listSum = new ArrayList<>();
    public static int codeBill;

    static {
        try {
            SQL = new SQL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Controller() throws SQLException {
    }

    private void buttonAction(ActionEvent event) {
        int sum = 0, tmp = 0;
        List<food> newList = new ArrayList<>(100);
        List<String> delListView = new ArrayList<>(100);
        for (food food : foodList) {
            quantityList.add(0);
        }

        for (int i = 0; i < foodButtonList.size(); i++) {
            if (event.getSource() == foodButtonList.get(i)) {
                int id = Integer.parseInt(foodTableView.getColumns().get(0).getCellObservableValue(i).getValue().toString());
                String name = foodTableView.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                int price = Integer.parseInt(foodTableView.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                String quantityString = fQuantity_Column.getCellObservableValue(i).getValue().getText();
                quantityList.set(i, Integer.valueOf(quantityString));
                int quantity = quantityList.get(i);

                foodList.get(i).getQuantity().setText(quantityString);

                //chuyển đổi food sang string
                String foodInfor = foodList.get(i).toString();

                // thêm vào listView
                listView.getItems().add(foodInfor);
                sum = price * quantity;
                System.out.println(name + " " + price + " " + quantity);


                // đặt đồ (kéo xuống dưói để biết thêm chi tiết)
                Order(Integer.parseInt(foodTableView.getColumns().get(0).getCellObservableValue(i).getValue().toString()), name, price, quantity, listSum);

                food food = new food(id, name, price);

                // thêm chi tiết
//                SQL.addBillDetails(food, codeBill);

                for (bill bill : billList) {
                    if (bill.getBillId() == codeBill) {
                        bill.addNewFood(food);
                        bill.setTotalMoney(sum);
                    }
                }
            }

            int del;
            if (event.getSource() == deleteButtonList.get(i)) {
                del = i + 1;
                for (table table : tableList) {
                    if (table.getTableId() == code) {
                        for (food food : table.getFoods()) {
                            if (food.getFoodId() == Integer.parseInt(foodTableView.getColumns().get(0).getCellObservableValue(i).getValue().toString())) {
                                newList.add(food);
                                table.setCurPrice(table.getCurPrice() - Integer.parseInt(food.getQuantity().getText()) * food.getPrice());
                                for (bill bill : billList) {
                                    if (bill.getBillId() == codeBill) {
                                        bill.deleteFood(food);
                                    }
                                }
                            }
                        }
                    }
                }
                String delString = "";
                for (food food : foodList) {
                    if (food.getFoodId() == Integer.parseInt(foodTableView.getColumns().get(0).getCellObservableValue(i).getValue().toString())) {
                        System.out.println("haha");
                        delString = food.toString();
                    }
                }

                for (Object o : listView.getItems()) {
                    if (o != null && o.equals(delString)) {
                        delListView.add(delString);
                    }
                }

                listView.getItems().removeAll(delListView);

                for (table table : tableList) {
                    if (table.getTableId() == code) {
                        table.getFoods().removeAll(newList);
                    }
                }

            }
        }
//        System.out.println(newList.size());

        //update 12/12
        for (table table : tableList) {
            if (table.getTableId() == code) {
                tmp = table.getCurPrice();
            }
        }

        for (table table : tableList) {
            if (table.getTableId() == code) {
                tmp = table.getCurPrice();
            }
        }

        billPrice.setText(String.valueOf(tmp));
    }

    // thêm food vào foodList
    // foodList là List chứa food + textField + Button
    public void addFood() throws SQLException {
        SQL.getAllFoodsSQL(SQL.allFoods());
        List<food> delFood = new ArrayList<>();
        List<Button> delButtonList = new ArrayList<>();

        for (food food : foodManagement.allFood) {
            int dem = 0;
            Button btn = new Button("ĐẶT ĐỒ");
            Button delButton = new Button("XÓA MÓN");
            TextField textField = new TextField();
            food f = new food(food.getFoodId(), food.getName(), food.getPrice(), textField, btn, delButton);

            // Kiểm tra xem food đã có trong list chưa
            // Nếu chưa có thì add
            for (food food1 : foodList) {
                if (food1.getFoodId() == f.getFoodId()) {
                    dem++;
                    if (food1.getPrice() != f.getPrice() || !food1.getName().equals(f.getName())) {
                        food1.setPrice(f.getPrice());
                        food1.setName(f.getName());
                    }
                }
                System.out.println(food.getFoodId() + " " + food1.getFoodId() + " " + dem);
            }
            if (dem == 0) {
                foodList.add(f);
            }

            foodButtonList.add(f.getBtn());
            deleteButtonList.add(f.getOrderDeleteButton());
        }

        for (food food : foodList) {
            int dem = 0;

            for(food f : foodManagement.allFood) {
                if (food.getFoodId() == f.getFoodId()) {
                    dem++;
                }
            }
            if (dem == 0) {
                delFood.add(food);
                delButtonList.add(food.getBtn());
            }
        }

        foodList.removeAll(delFood);
        foodButtonList.removeAll(delButtonList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addFood();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fID_Column.setCellValueFactory(new PropertyValueFactory<food, Number>("foodId"));
        fName_Column.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        fPrice_Column.setCellValueFactory(new PropertyValueFactory<food, Number>("price"));
        fQuantity_Column.setCellValueFactory(new PropertyValueFactory<food, TextField>("quantity"));
        fButton_Column.setCellValueFactory(new PropertyValueFactory<food, Button>("btn"));
        fDelete_Column.setCellValueFactory(new PropertyValueFactory<food, Button>("orderDeleteButton"));
        fQuantity_Column.setText(" ");

        foodTableView.getColumns().get(2).setText("");

        for (food f : foodList) {
            foodTableView.getItems().add(f);
        }

        for (int i = 0; i < foodButtonList.toArray().length; i++) {
            foodButtonList.get(i).setOnAction(this::buttonAction);
        }

        for (int i = 0; i < deleteButtonList.toArray().length; i++) {
            deleteButtonList.get(i).setOnAction(this::buttonAction);
        }
    }

    @FXML
    public void Order(int id, String name, int price, int quantity, List<Integer> list) {
        int billSum = 0;

        TextField textField = new TextField(String.valueOf(quantity));
        Integer sum = price * quantity;
        food sp = new food(id, name, price, textField);

        for (table table : tableList) {
            if (table.getTableId() == code) {
                table.getFoods().add(sp);
            }
        }

        // update 12/12
        for (table table : tableList) {
            if (table.getTableId() == code) {
                table.setCurPrice(table.getCurPrice() + sum);
//                table.setCurPrice(billSum);
                System.out.println(table.getCurPrice());
            }
        }
    }


    @FXML
    void quayLai_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/Table.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) quayLai_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void checkBill_btn(ActionEvent event) {
        int sum = 0;
        for (table table : tableList) {
            if (table.getTableId() == code && table.getCurPrice() != 0) {
                sum = table.getCurPrice();
                for (food food : table.getFoods()) {
                    String tmp = food.toString();
                    listView.getItems().add(tmp);
                }
            }
        }

        billPrice.setText(String.valueOf(sum));
        checkBill_btn.setVisible(false);
    }

    @FXML
    void thanhToan_btn(ActionEvent event) {
        int billID = 0;

        for (table table : tableList) {
            if (table.getTableId() == code) {
                for (food food : table.getFoods()) {
                    SQL.addBillDetails(food, codeBill);
                }
            }
        }

        for (table table : tableList) {
            if (table.getTableId() == code) {
                for (bill bill : billList) {
                    if (bill.getBillId() == table.getBillId()) {
                        bill.setPaymentTime(LocalDateTime.now());
                        bill.setTotalMoney(table.getCurPrice());
                        billID = bill.getBillId();
                        bill.setEmployeeId(login.Controller.id);
                        SQL.addBill(bill);
                        System.out.println("correct");
                    }
                }
            }
        }

        for (bill bill : billList) {
            System.out.println(bill.getBillId() + " ");
        }

        for (table table : tableList) {
            if (table.getTableId() == code) {
                table.setBillId(0);
                table.setCurPrice(0);
                table.setFoods(new ArrayList<>());
            }
        }
        SQL.payBill(billID, LocalDateTime.now());
        Alerts.showAlertInfo("Thanh Toán", "Thanh Toán thành công");
        listView.setVisible(false);
        billPrice.setText("");
    }
}