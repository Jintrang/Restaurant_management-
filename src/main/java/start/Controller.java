package start;

import Alert.Alerts;
import employees.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {

    @FXML
    private AnchorPane container;

    @FXML
    private Button thucDon_btn;

    @FXML
    private Button nhanVien_btn;

    @FXML
    private Button hoaDon_btn;

    @FXML
    private ImageView thanhToan_img;

    @FXML
    private ImageView nhanVien_img;

    @FXML
    private Button thanhToan_btn;

    @FXML
    private Button backStart;

    @FXML
    private ImageView thucDon_img;

    @FXML
    private ImageView hoaDon_img;

    @FXML
    void thanhToan_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/Table.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) thanhToan_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void hoaDon_btn(ActionEvent event) throws IOException {
        for(employee employee : employeeManagement.allEmployee) {
            if (employee.getEmployeeId() == login.Controller.id) {
                if (employee.getJobTitle().equals("Quản lí")) {
                    URL url = new File("src/main/resources/com/example/demo1/bill.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
                    root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                    Stage window = (Stage) nhanVien_btn.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
                else {
                    Alerts.showAlertWarning("Quản Lý Hóa Đơn", "Bạn không đủ thẩm quyền");
                }
            }
        }
    }

    @FXML
    void nhanVien_btn(ActionEvent event) throws IOException {
        for(employee employee : employeeManagement.allEmployee) {
            if (employee.getEmployeeId() == login.Controller.id) {
                if (employee.getJobTitle().equals("Quản lí")) {
                    URL url = new File("src/main/resources/com/example/demo1/employees.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
                    root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                    Stage window = (Stage) nhanVien_btn.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
                else {
                    Alerts.showAlertWarning("Quản Lý Nhân Viên", "Bạn không đủ thẩm quyền");
                }
            }
        }
    }

    @FXML
    void thucDon_btn(ActionEvent event) throws IOException {
        for(employee employee : employeeManagement.allEmployee) {
            if (employee.getEmployeeId() == login.Controller.id) {
                if (employee.getJobTitle().equals("Quản lí")) {
                    URL url = new File("src/main/resources/com/example/demo1/food.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
                    root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                    Stage window = (Stage) nhanVien_btn.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
                else {
                    Alerts.showAlertWarning("Quản Lý Thực Đơn", "Bạn không đủ thẩm quyền");
                }
            }
        }
    }

    @FXML
    void backStart(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) backStart.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
