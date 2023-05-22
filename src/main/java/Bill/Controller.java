package Bill;

import Food.food;
import SQL.SQL;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<bill> billTableView;

    @FXML
    private TableColumn<bill, LocalDateTime> timePayment_Column;

    @FXML
    private TableColumn<bill, Integer> bID_Column;

    @FXML
    private AnchorPane billContainer;

    @FXML
    private TableColumn<bill, Integer> totalMoney_Column;

    @FXML
    private TableColumn<bill, LocalDateTime> timeIn_Column;

    @FXML
    private TableColumn<bill, Integer> emploteeId_Column;

    @FXML
    private Button billBack;

    @FXML
    private Button searchBill_btn;

    @FXML
    private TextField search;

    public void addBIll() throws SQLException {
        SQL sql = new SQL();
        sql.getAllBillsSQL(sql.allBills());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addBIll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bID_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("billId"));
        totalMoney_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("totalMoney"));
        timeIn_Column.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("timeIn"));
        timePayment_Column.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("paymentTime"));
        emploteeId_Column.setCellValueFactory(new PropertyValueFactory<bill, Integer>("employeeId"));


        for (bill bill : billManagement.allBill) {
            billTableView.getItems().add(bill);
        }
    }

    @FXML
    void billBack(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/start.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) billBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void searchBill_btn(ActionEvent event) throws IOException {
        searchBill.Controller.time = search.getText();
        URL url = new File("src/main/resources/com/example/demo1/searchBill.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) searchBill_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
