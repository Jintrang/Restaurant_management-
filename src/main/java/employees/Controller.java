package employees;

import Bill.bill;
import Food.food;
import Alert.*;
import SQL.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Bill.billManagement.billList;

public class Controller implements Initializable {

    @FXML
    private TableView<employee> employeeTableView;

    @FXML
    private TableColumn<employee, TextField> eId_Column;

    @FXML
    private TableColumn<employee, Button> eDelete_Column;

    @FXML
    private TableColumn<employee, Button> eFix_Column;

    @FXML
    private TableColumn<employee, TextField> eBirthday_Column;

    @FXML
    private TableColumn<employee, TextField> ePhone_Column;

    @FXML
    private TableColumn<employee, TextField> eLastName_Column;

    @FXML
    private TableColumn<employee, TextField> eFirstName_Column;

    @FXML
    private TableColumn<employee, TextField> eTitle_Column;

    @FXML
    private TableColumn<employee, TextField> eJoinDate_Column;

    @FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    @FXML
    private Button back_btn;


    @FXML
    private AnchorPane employeeContainer;

    public List<employee> employeesList= new ArrayList<>();
    public List<Button> fixButtonList = new ArrayList<>();
    public List<Button> deleteButtonList = new ArrayList<>();
    public int tmp = 0;

    // thêm food vào foodList
    // foodList là List chứa food + textField + Button

    private void buttonAction(ActionEvent event) {

        for (int i = 0; i < fixButtonList.size(); i++) {
            if (event.getSource() == fixButtonList.get(i)) {
                employeesList.get(i).getTextLastName().setEditable(true);
                employeesList.get(i).getTextFirstName().setEditable(true);
                employeesList.get(i).getTextBirthday().setEditable(true);
                employeesList.get(i).getTextJobTitle().setEditable(true);
                employeesList.get(i).getTextPhone().setEditable(true);
                tmp = i;
            }

            if (event.getSource() == deleteButtonList.get(i)) {
                int id = Integer.parseInt(employeesList.get(i).getTextId().getText());
                SQL.deleteEmployees(id);
                Alerts.showAlertWarning("Xóa Nhân Viên", "Xóa Thành Công");
            }
        }
    }

    @FXML
    void saveButton(ActionEvent event) {
        
        int id = 0;
        String lastName = "";
        String firstName = "";
        String jobTitle = "";
        String phone = "";
        LocalDate birthDay = null;
        LocalDate joinDate = null;

        id = Integer.parseInt(eId_Column.getCellObservableValue(tmp).getValue().getText());
        lastName = eLastName_Column.getCellObservableValue(tmp).getValue().getText();
        firstName = eFirstName_Column.getCellObservableValue(tmp).getValue().getText();
        birthDay = LocalDate.parse(eBirthday_Column.getCellObservableValue(tmp).getValue().getText());
        jobTitle = eTitle_Column.getCellObservableValue(tmp).getValue().getText();
        phone = ePhone_Column.getCellObservableValue(tmp).getValue().getText();

        SQL.fixInfoEmployee(id, lastName, firstName, birthDay, jobTitle, phone);
        for(employee employee : employeeManagement.allEmployee) {
            if (employee.getEmployeeId() == id) {
                employee.setLastName(lastName);
                employee.setFirstName(firstName);
                employee.setBirthday(birthDay);
                employee.setJobTitle(jobTitle);
                employee.setPhoneNumber(phone);
                System.out.println("haha");
            }
        }

        Alerts.showAlertInfo("Sửa Thông Tin", "Cập nhật thành công");
    }

    @FXML
    void addButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/addEmployee.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) addButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void back_btn (ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/start.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void addEmployee() throws SQLException {
        int dem = 0;

        SQL sql = new SQL();
        sql.getAllEmployees(sql.allEmployees());

        for (employee e : employeeManagement.allEmployee) {
            Button fixButton = new Button("Fix");
            Button deleteButton = new Button("Delete");

            employee employee = new employee();
            employee.getTextId().setText(String.valueOf(e.getEmployeeId()));
            employee.getTextLastName().setText(e.getLastName());
            employee.getTextFirstName().setText(e.getFirstName());
            employee.getTextBirthday().setText(String.valueOf(e.getBirthday()));
            employee.getTextJobTitle().setText(e.getJobTitle());
            employee.getTextJoinDate().setText(String.valueOf(e.getJoinDate()));
            employee.getTextPhone().setText(e.getPhoneNumber());
            employee.setFixButton(fixButton);
            employee.setDeleteButton(deleteButton);

            employee.getTextId().setEditable(false);
            employee.getTextLastName().setEditable(false);
            employee.getTextFirstName().setEditable(false);
            employee.getTextBirthday().setEditable(false);
            employee.getTextJobTitle().setEditable(false);
            employee.getTextJoinDate().setEditable(false);
            employee.getTextPhone().setEditable(false);

            employee.getTextId().setStyle("-fx-background-color: transparent;");
            employee.getTextLastName().setStyle("-fx-background-color: transparent;");
            employee.getTextFirstName().setStyle("-fx-background-color: transparent;");
            employee.getTextBirthday().setStyle("-fx-background-color: transparent;");
            employee.getTextJobTitle().setStyle("-fx-background-color: transparent;");
            employee.getTextJoinDate().setStyle("-fx-background-color: transparent;");
            employee.getTextPhone().setStyle("-fx-background-color: transparent;");


            // Kiểm tra xem food đã có trong list chưa
            // Nếu chưa có thì add
            for (employee employee1 : employeesList) {
                if (Integer.parseInt(employee1.getTextId().getText()) == employee.getEmployeeId()){
                    dem++;
                }
            }
            if (dem == 0) {
                employeesList.add(employee);
            }
            fixButtonList.add(employee.getFixButton());
            deleteButtonList.add(employee.getDeleteButton());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        eId_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textId"));
        eLastName_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textLastName"));
        eFirstName_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textFirstName"));
        eBirthday_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textBirthday"));
        eTitle_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textJobTitle"));
        ePhone_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textPhone"));
        eJoinDate_Column.setCellValueFactory(new PropertyValueFactory<employee, TextField>("textJoinDate"));
        eFix_Column.setCellValueFactory(new PropertyValueFactory<employee, Button>("fixButton"));
        eDelete_Column.setCellValueFactory(new PropertyValueFactory<employee, Button>("deleteButton"));


        for (employee e : employeesList) {
            employeeTableView.getItems().add(e);
        }

        for(int i = 0; i < fixButtonList.toArray().length; i++) {
            fixButtonList.get(i).setOnAction(this::buttonAction);
        }

        for(int i = 0; i < deleteButtonList.toArray().length; i++) {
            deleteButtonList.get(i).setOnAction(this::buttonAction);
        }
    }
//
//    public static void main(String[] args) throws SQLException {
//        SQL sql = new SQL();
//        sql.getAllEmployees(sql.allEmployees());
//        for(employee employee : employeeManagement.allEmployee) {
//            System.out.println(employee.getEmployeeId() + "\n");
//        }
//    }
}
