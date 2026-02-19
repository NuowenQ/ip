package speed;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import speed.ui.MainWindow;

/**
 * Entry point for the Cq chatbot application.
 */
public class Main extends Application {

    private Speed bot = new Speed();

    @Override
    public void start(Stage stage) {
        try {
            ChatbotApp app = new ChatbotApp(bot);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            stage.setTitle("Speed");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/Icon.jpg")));
            fxmlLoader.<MainWindow>getController().setApp(app);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
