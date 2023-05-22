package addEmployee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/main/resources/com/example/demo1/addEmployee.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        File f = new File("C:\\Users\\Admin\\IdeaProjects\\demo1\\src\\main\\resources\\Style.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
