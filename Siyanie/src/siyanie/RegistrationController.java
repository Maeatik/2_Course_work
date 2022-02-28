package siyanie;


import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class RegistrationController{

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField checkField;

    @FXML
    private Button registrBtn;
    
    @FXML
    private Button backBtn;

    @FXML
    void backHandler(ActionEvent event) throws IOException
    {
                Stage stage = new Stage();
                stage.setTitle("Вход");
                Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                TextInputDialog dialog = new TextInputDialog("");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) registrBtn.getScene().getWindow();
                stage1.close();
    }
    @FXML
    void RegistrationHandler(ActionEvent event) throws IOException 
    {
         if ((passwordField.getText().equals(checkField.getText())) && (emailField.getText().indexOf("@") != -1))
                 
        {
            boolean alreadyExists = false;
            User user;
            
            String login = emailField.getText();
            String password = checkField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            
            String fileLogin = "";
            String filePassword = "";
            user = new User(login, password);
            File userData = new File("DataUser.txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            String tmp = reader.readLine();
            while (((fileLogin = reader.readLine()) != null) && ((filePassword = reader.readLine()) != null))
            {
                if (user.login.equals(fileLogin))
                {
                    alreadyExists = true;
                }
            }
            fileReader.close();
            reader.close();
            if(alreadyExists)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Внимание");
                alert.setHeaderText("Пользователь с таким логином уже существует");
                alert.showAndWait();
            }
            else
            {
                File file = new File("DataUser.txt");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file,true);
                    fr.write(user.login);
                    fr.append('\n');
                    fr.write(user.password);
                    fr.append('\n');

                } catch (IOException e) {
                    e.printStackTrace();
                }
                fr.close();
                PrintWriter writer = new PrintWriter(System.getProperty("user.dir")+"\\accounts\\"+user.login+".txt", "UTF-8");
                writer.println(user.login);
                writer.println(user.password);
                writer.println(name);
                writer.println(address);
                writer.println();
                writer.close();
                
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Успешно");
                alert.setHeaderText("Пользователь создан");
                alert.showAndWait();


                Stage stage = new Stage();
                stage.setTitle("Вход");
                Parent root = FXMLLoader.load(getClass ().getResource("LogIn.fxml"));
                TextInputDialog dialog = new TextInputDialog("");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) registrBtn.getScene().getWindow();
                stage1.close();
            }
        }
        else
         {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте пароль или логин");
                alert.showAndWait();
         }
    }

}
