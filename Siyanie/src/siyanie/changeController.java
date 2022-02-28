/**package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class changeController {

    @FXML
    private Button ApplyBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField changeNameField;

    @FXML
    private TextField changeAddressField;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField checkPassword;

    @FXML
    void ApplyHandler(ActionEvent event) throws FileNotFoundException, IOException 
    {
        
            String changeName = changeNameField.getText();
            String changeAddress = changeAddressField.getText();
            String changeOldPassword = oldPassword.getText();
            String setNewPassword = newPassword.getText();
            String setCheckPassword = checkPassword.getText();
        
            File logData = new File("nextUser.txt");
            FileReader fileLogReader = new FileReader(logData);
            BufferedReader logReader = new BufferedReader(fileLogReader);
            String logName = logReader.readLine();
            
            copyFile(logName);
            
            
            
        String newName = null , newAddress = null , setPassword = null;
        if ((changeName.equals("")) && (changeAddress.equals("")) && (changeOldPassword.equals("")) && (setNewPassword.equals("")) && (setCheckPassword.equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите данные хотя бы в одно поле");
            alert.showAndWait();
        }
        if ((changeOldPassword.equals("")) && !(setNewPassword.equals("")) && !(setCheckPassword.equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите старый пароль");
            alert.showAndWait();
        }
        if (!(changeName.equals("")))
        {
            newName = changeName;
            // переписать имя
        }
        if (!(changeAddress.equals("")))
        {
            newAddress = changeAddress;
        }
        if ( setNewPassword.equals(setCheckPassword) && 
                (!(changeOldPassword.equals("")) && !(setNewPassword.equals("")) && !(setCheckPassword.equals(""))))
        {
            setPassword = setNewPassword;
        }
        
        System.out.println(newName + " " + newAddress+ " "+ setPassword);
    }

    @FXML
    void cancelHandler(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        stage.setTitle("Личный кабинет");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Stage stage1 = (Stage) cancelBtn.getScene().getWindow();
        stage1.close();
        stage.show();
    }
    
     void copyFile(String name) throws FileNotFoundException, IOException
    {
        File userData = new File(System.getProperty("user.dir")+"\\accounts\\"+name+".txt");
        FileReader fileReader = new FileReader(userData);
        BufferedReader reader = new BufferedReader(fileReader);
        
        File file = new File(System.getProperty("user.dir")+"\\accounts\\"+name+".txt");
        FileWriter fr = null;
        try
            {
                fr = new FileWriter(file);
                String nextLine = reader.readLine();
                while (nextLine != null)
                {
                    fr.write(nextLine);
                    fr.append('\n');
                }
                fr.write(nextName);
                fr.append('\n');
                fr.write(nextPassword);
                fr.append('\n');
                fr.write(nextFIO);
                fr.append('\n');
                fr.write(nextAddress);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        reader.readLine();
        String oldPassword = reader.readLine();
        String oldFIO = reader.readLine();
        String oldAddress = reader.readLine();
    }

}
**/