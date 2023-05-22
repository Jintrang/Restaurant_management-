package addEmployee;

import Alert.Alerts;
import SQL.SQL;
import employees.employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField birthDay;

    @FXML
    private Button add_btn;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button quayLai_btn;

    @FXML
    private TextField jobTitle;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item1;

    @FXML
    private TextField passCode;

    @FXML
    private AnchorPane addEmployee;


    @FXML
    void add_btn(ActionEvent event) {
        String lName = lastName.getText();
        String fName = firstName.getText();
        String birth = birthDay.getText();
        String title = jobTitle.getText();
        String phone = phoneNumber.getText();
        String pass = passCode.getText();
        LocalDate join = LocalDate.now();
        employee e = new employee();
        e.setEmployeeId(SQL.getNewEmployeeIdSQL() + 1);
        e.setLastName(lName);
        e.setFirstName(fName);
        e.setPhoneNumber(phone);
        e.setBirthday(LocalDate.parse(birth));
        e.setPhoneNumber(phone);
        e.setJoinDate(join);
        e.setPassCode(pass);
        e.setJobTitle(title);
        SQL.addEmployeeSQL(e);

        Alerts.showAlertInfo("Thêm Nhân Viên", "Thêm thành công");
    }

    @FXML
    void quayLai_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/employees.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) quayLai_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jobTitle.setEditable(false);
    }

    @FXML
    void item1(ActionEvent event) {
        jobTitle.setText(item1.getText());
    }

    @FXML
    void item2(ActionEvent event) {
        jobTitle.setText(item2.getText());
    }
}
