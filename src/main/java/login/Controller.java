package login;

import Alert.Alerts;
import SQL.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Controller {
    @FXML
    private Button nhanVien_btn;

    @FXML
    private Button khachHang_btn;

    @FXML
    private Label dangNhap_title;

    @FXML
    private Button dangNhap_btn;

    @FXML
    public TextField maDangNhap;

    @FXML
    private PasswordField matKhau;

    SQL sqlConnect = new SQL();
    public Controller() throws SQLException {
    }

    public static int id;

    @FXML
    void dangNhap_btn(ActionEvent event) throws IOException, SQLException {
        sqlConnect.connect();
        id = Integer.parseInt(maDangNhap.getText());
        String pass = matKhau.getText();
        if (!SQL.checkID(id,pass)) {
            Alerts.showAlertWarning("Đăng nhập", "Đăng nhập không thành công");
        } else {
            URL url = new File("src/main/resources/com/example/demo1/start.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
            root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            Stage window = (Stage) dangNhap_btn.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

}
