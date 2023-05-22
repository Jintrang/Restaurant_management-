package searchBill;

import Bill.bill;
import Bill.billManagement;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<bill, Integer> bID_Col;

    @FXML
    private Button billBack;

    @FXML
    private TableView<bill> billTableView;

    @FXML
    private AnchorPane billContainer;

    @FXML
    private TableColumn<bill, Integer> emploteeId_Col;

    @FXML
    private TableColumn<bill, Integer> totalMoney_Col;

    @FXML
    private TableColumn<bill, LocalDateTime> timeIn_Col;

    @FXML
    private TableColumn<bill, LocalDateTime> timePayment_Col;

    public static String time = "";
    @FXML
    void billBack(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/bill.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) billBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate date = LocalDate.parse(time);

        bID_Col.setCellValueFactory(new PropertyValueFactory<bill, Integer>("billId"));
        totalMoney_Col.setCellValueFactory(new PropertyValueFactory<bill, Integer>("totalMoney"));
        timeIn_Col.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("timeIn"));
        timePayment_Col.setCellValueFactory(new PropertyValueFactory<bill, LocalDateTime>("paymentTime"));
        emploteeId_Col.setCellValueFactory(new PropertyValueFactory<bill, Integer>("employeeId"));

        for (bill bill : billManagement.allBill) {
            if(Objects.equals(bill.getTimeIn().toLocalDate(), date)) {
                billTableView.getItems().add(bill);
            }
        }
    }
}
