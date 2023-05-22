package Table;

import Bill.bill;
import SQL.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static Bill.billManagement.billList;

public class checkTable {

    @FXML
    private Button ban1_btn, ban2_btn, ban3_btn, ban4_btn, ban5_btn, ban6_btn, ban7_btn, ban8_btn ;

    @FXML
    private Button checkTable_btn;

    @FXML
    private Button tableBack;

    @FXML
    private Button logout_btn;

    @FXML
    private ListView<String> test;

    public static ArrayList<table> tableList = new ArrayList<>();

    private ArrayList<Button> buttonList = new ArrayList<>();
    URL url = new File("src/main/resources/com/example/demo1/order.fxml").toURI().toURL();
    File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");

    public checkTable() throws MalformedURLException, SQLException {
    }

    @FXML
    void pickthrows (URL url, File f, Button btn) throws IOException {
        Parent root = FXMLLoader.load(url);
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    void makeBill(Button btn) {
        for(table table : tableList) {
            if(table.getTableId() == Integer.parseInt(btn.getText()) && table.getCurPrice() == 0) {
                bill bill = new bill();
                bill.setBillId(SQL.getNewBillIdSQL() + 1);
                billList.add(bill);
                bill.setTimeIn(LocalDateTime.now());
                Order.Controller.codeBill = bill.getBillId();
                table.setBillId(bill.getBillId());
            }
        }
    }
    @FXML
    void ban1_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban1_btn);
        Order.Controller.code = Integer.parseInt(ban1_btn.getText());

        // tạo ra 1 bill mới
        makeBill(ban1_btn);
    }

    @FXML
    void ban2_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban2_btn);
        Order.Controller.code = Integer.parseInt(ban2_btn.getText());
        makeBill(ban2_btn);

    }

    @FXML
    void ban3_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban3_btn);
        Order.Controller.code = Integer.parseInt(ban3_btn.getText());
        makeBill(ban3_btn);

    }

    @FXML
    void ban4_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban4_btn);
        Order.Controller.code = Integer.parseInt(ban4_btn.getText());
        makeBill(ban4_btn);

    }

    @FXML
    void ban5_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban5_btn);
        Order.Controller.code = Integer.parseInt(ban5_btn.getText());
        makeBill(ban5_btn);

    }

    @FXML
    void ban6_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban6_btn);
        Order.Controller.code = Integer.parseInt(ban6_btn.getText());
        makeBill(ban6_btn);

    }

    @FXML
    void ban7_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban7_btn);
        Order.Controller.code = Integer.parseInt(ban7_btn.getText());
        makeBill(ban7_btn);

    }

    @FXML
    void ban8_btn(ActionEvent event) throws IOException {
        pickthrows(url, f, ban8_btn);
        Order.Controller.code = Integer.parseInt(ban8_btn.getText());
        makeBill(ban8_btn);
    }


    @FXML
    void checkTable_btn(ActionEvent event) {
        add_button();
        add_table();
        for(table table : tableList) {
            table tmp = table;
            if (table.getCurPrice() == 0) {
                for(Button btn : buttonList) {
                    if (Integer.parseInt(btn.getText()) == tmp.getTableId()) {
                        btn.setStyle("-fx-background-color: #00FF99;");
                    }
                }
            }
            else {
                for(Button btn : buttonList) {
                    if (Integer.parseInt(btn.getText()) == tmp.getTableId()) {
                        btn.setStyle("-fx-background-color: red;");
                    }
                }
            }
        }

        for (table table : tableList) {
            String tmp = "Bàn" + table.getTableId() + ": " + table.getCurPrice() + "\n";
            test.getItems().add(tmp);
        };
        checkTable_btn.setVisible(false);
    }

    void add_button() {
        check_button(ban1_btn);
        check_button(ban2_btn);
        check_button(ban3_btn);
        check_button(ban4_btn);
        check_button(ban5_btn);
        check_button(ban6_btn);
        check_button(ban7_btn);
        check_button(ban8_btn);
    }

    void check_button(Button btn) {
        int dem = 0;
        for(Button tmp : buttonList) {
            if(tmp.getText().equals(btn.getText())) {
                dem++;
            }
        }
        if(dem == 0) {
            buttonList.add(btn);
        }
    }

    @FXML
    void tableBack(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/start.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) tableBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void logout_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) logout_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    void check_table(table tmp) {
        int dem = 0;
        for(table table : tableList) {
            if(table.getTableId() == tmp.getTableId()) {
                dem++;
            }
        }
        if(dem == 0) {
            tableList.add(tmp);
        }
    }

    public void add_table() {
        table table1 = new table();
        table1.setTableId(Integer.parseInt(ban1_btn.getText()));
        check_table(table1);

        table table2 = new table();
        table2.setTableId(Integer.parseInt(ban2_btn.getText()));
        check_table(table2);

        table table3 = new table();
        table3.setTableId(Integer.parseInt(ban3_btn.getText()));
        check_table(table3);

        table table4 = new table();
        table4.setTableId(Integer.parseInt(ban4_btn.getText()));
        check_table(table4);

        table table5 = new table();
        table5.setTableId(Integer.parseInt(ban5_btn.getText()));
        check_table(table5);

        table table6 = new table();
        table6.setTableId(Integer.parseInt(ban6_btn.getText()));
        check_table(table6);

        table table7 = new table();
        table7.setTableId(Integer.parseInt(ban7_btn.getText()));
        check_table(table7);

        table table8 = new table();
        table8.setTableId(Integer.parseInt(ban8_btn.getText()));
        check_table(table8);
    }
}