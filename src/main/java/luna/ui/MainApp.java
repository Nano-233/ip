package luna.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luna.Luna;

/**
 * Main entry point for the JavaFX GUI.
 */
public class MainApp extends Application {
    private Luna luna = new Luna("data/Luna.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setLuna(luna);

            stage.setTitle("Luna Chatbot");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
