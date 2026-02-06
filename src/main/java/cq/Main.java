package cq;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for the Cq chatbot application.
 */
public class Main extends Application {

    private Cq bot = new Cq();

    @Override
    public void start(Stage stage) {
        try {
            ChatbotApp app = new ChatbotApp(bot);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setApp(app);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
