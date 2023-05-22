package Food;

import Alert.Alerts;
import Bill.bill;
import SQL.SQL;
import Table.table;
import employees.employee;
import employees.employeeManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Bill.billManagement.billList;
import static Table.checkTable.tableList;

public class Controller implements Initializable {

    @FXML
    private TableColumn<food, TextField> fPrice_Column;

    @FXML
    private TableColumn<food, TextField> fName_Column;

    @FXML
    private TableView<food> menuTableView;

    @FXML
    private TableColumn<food, Button> deleteButton_Column;

    @FXML
    private AnchorPane foodContainer;

    @FXML
    private TableColumn<food, Button> fixButton_Column;

    @FXML
    private TableColumn<food, Integer> fID_Column;

    @FXML
    private Button saveMenu;

    @FXML
    private Button addBtn;

    @FXML
    private Button foodBack;

    public List<food> menuList = new ArrayList<>();
    public List<Button> fixButtonList = new ArrayList<>();
    public List<Button> deleteButtonList = new ArrayList<>();
    public int tmp = 0;


    public Controller() throws SQLException {
    }

    private void buttonAction(ActionEvent event) {

        for (int i = 0; i < fixButtonList.size(); i++) {
            if (event.getSource() == fixButtonList.get(i)) {
                menuList.get(i).getTextName().setEditable(true);
                menuList.get(i).getTextPrice().setEditable(true);
                tmp = i;
            }

            if (event.getSource() == deleteButtonList.get(i)) {
                int id = menuList.get(i).getFoodId();
                SQL.deleteFood(id);
                Alerts.showAlertWarning("Xóa Món", "Xóa Thành Công");
            }
        }
    }

    @FXML
    void saveMenu(ActionEvent event) {

        int id = 0;
        String name = "";
        int price = 0;

        id = fID_Column.getCellObservableValue(tmp).getValue();
        name = fName_Column.getCellObservableValue(tmp).getValue().getText();
        price = Integer.parseInt(fPrice_Column.getCellObservableValue(tmp).getValue().getText());

        SQL.fixFoodSQL(id, name, price);
        for(food food : foodManagement.allFood) {
            if (food.getFoodId() == id) {
                food.setName(name);
                food.setPrice(price);
                System.out.println("haha");
            }
        }

        Alerts.showAlertInfo("Sửa Thông Tin", "Cập nhật thành công");
    }


    @FXML
    void foodBack(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/start.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) foodBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void addBtn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/addFood.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) addBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void addFood() throws SQLException {
        int dem = 0;

        SQL sql = new SQL();
        sql.getAllFoodsSQL(sql.allFoods());

        for (food f : foodManagement.allFood) {
            Button fixButton = new Button("Fix");
            Button deleteButton = new Button("Delete");

            food food = new food();
            food.setFoodId(f.getFoodId());
            food.getTextName().setText(f.getName());
            food.getTextPrice().setText(String.valueOf(f.getPrice()));
            food.setFixButton(fixButton);
            food.setDeleteButton(deleteButton);

            food.getTextName().setEditable(false);
            food.getTextPrice().setEditable(false);


            food.getTextName().setStyle("-fx-background-color: transparent;");
            food.getTextPrice().setStyle("-fx-background-color: transparent;");

            // Kiểm tra xem food đã có trong list chưa
            // Nếu chưa có thì add
            for (food food1 : menuList) {
                if (food1.getFoodId() == food.getFoodId()){
                    dem++;
                }
            }
            if (dem == 0) {
                menuList.add(food);
            }
            fixButtonList.add(food.getFixButton());
            deleteButtonList.add(food.getDeleteButton());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addFood();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fID_Column.setCellValueFactory(new PropertyValueFactory<food, Integer>("foodId"));
        fName_Column.setCellValueFactory(new PropertyValueFactory<food, TextField>("textName"));
        fPrice_Column.setCellValueFactory(new PropertyValueFactory<food, TextField>("textPrice"));
        fixButton_Column.setCellValueFactory(new PropertyValueFactory<food, Button>("fixButton"));
        deleteButton_Column.setCellValueFactory(new PropertyValueFactory<food, Button>("deleteButton"));

        for (food f : menuList) {
            menuTableView.getItems().add(f);
        }

        for(int i = 0; i < fixButtonList.toArray().length; i++) {
            fixButtonList.get(i).setOnAction(this::buttonAction);
        }

        for(int i = 0; i < deleteButtonList.toArray().length; i++) {
            deleteButtonList.get(i).setOnAction(this::buttonAction);
        }
    }
}
