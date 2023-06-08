import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Login extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Chat with Play Tech");

        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}