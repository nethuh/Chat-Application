package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class LoginFormController {

    public static String name;
    public Button btnLogin;
    public TextField txtUserName;

    public void enterOnAction(ActionEvent actionEvent) throws IOException {
        btnLogin.fire();
    }

    public void clickOnAction(ActionEvent actionEvent) throws IOException {


        name = txtUserName.getText();

        Parent root = FXMLLoader.load(getClass().getResource("/View/ClientForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat with Play Tech");
        stage.getIcons().add(new Image("Assets/Images/AppIcon.png"));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();


        txtUserName.clear();

    }
}
