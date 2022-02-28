package siyanie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    @FXML
    private Button log_inId;

    @FXML
    private PasswordField passwordFiledId;

    @FXML
    private TextField loginFieldId;

    @FXML
    private Hyperlink registrationId;

    @FXML
    void LoginHandler(ActionEvent event) throws FileNotFoundException, IOException
    {
        User user;
        String login = loginFieldId.getText();
        String password = passwordFiledId.getText();
        String fileLogin = "";
        String filePassword = "";
        user = new User(login, password);
        File userData = new File("DataUser.txt");
        FileReader fileReader = new FileReader(userData);
        BufferedReader reader = new BufferedReader(fileReader);
        boolean found = false;
        String tmp = reader.readLine();
        while (((fileLogin = reader.readLine()) != null) && ((filePassword = reader.readLine()) != null) && (!found))
        {
            if ((user.login.equals(fileLogin)) && (user.password.equals(filePassword)))
            {
              
                PrintWriter writer = new PrintWriter("nextUser.txt", "UTF-8");
                writer.println(fileLogin);
                writer.close();
                
                Stage stage = new Stage();
                stage.setTitle("Каталог");
                Parent root = FXMLLoader.load(getClass().getResource("Main1.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) registrationId.getScene().getWindow();
                stage1.close();
                found = true;
            }
            if ((user.login.equals("admin")) && (user.password.equals("admin")))
            {
                Stage stage = new Stage();
                stage.setTitle("Admin System");
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) registrationId.getScene().getWindow();
                stage1.close();
                found = true;
            }
        }
        if (!found)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Проверьте введенные логин и пароль");
            alert.showAndWait();
        }
    }
    @FXML
    void RegistrationHandler(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Parent root = FXMLLoader.load(getClass ().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) registrationId.getScene().getWindow();
        stage1.close();
    }
}