package addFood;

import Alert.Alerts;
import Food.food;
import SQL.SQL;
import employees.employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class Controller {

    @FXML
    private Button add_btn;

    @FXML
    private TextField price;

    @FXML
    private Button quayLai_btn;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane addEmployee;

    @FXML
    void quayLai_btn(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/food.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) quayLai_btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void add_btn(ActionEvent event) {
        String foodName = name.getText();
        int foodPrice = Integer.parseInt(price.getText());

        food food = new food();
        food.setFoodId(SQL.getNewFoodIdSQL() + 1);
        food.setName(foodName);
        food.setPrice(foodPrice);
        SQL.addNewFoodSQL(food);

        Alerts.showAlertInfo("Thêm Món Ăn", "Thêm thành công");

    }

}
