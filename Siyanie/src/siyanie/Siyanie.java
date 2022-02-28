package siyanie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Siyanie extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Вход в магазин");
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
class User
{
    String login;
    String password;

    User (String login, String password)
    {
        this.login = login;
        this.password = password;
    }
}