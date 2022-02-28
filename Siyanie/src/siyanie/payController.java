package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class payController implements Initializable{

    @FXML
    private Button payBtn;
    @FXML
    private ImageView bankCard;
    
    @FXML
    private TextField numField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField cvcField;
    
    @FXML
    void payHandler(ActionEvent event) throws FileNotFoundException, IOException
    {
            String num = numField.getText();
            String month = monthField.getText();
            //String month = "a";
            String year = yearField.getText();
            String cvc = cvcField.getText();
            
            if (!(num.equals("")) && !(month.equals("")) && !(year.equals("")) && !(cvc.equals("")))
            { 
                if ((num.length() == 16) && (month.length() < 3) && (year.length() < 3) && (cvc.length() == 3))  
                {    
            File logData = new File("nextUser.txt");
            FileReader fileLogReader = new FileReader(logData);
            BufferedReader logReader = new BufferedReader(fileLogReader);
            String logName = logReader.readLine();
           
            
            File userData = new File(System.getProperty("user.dir")+"\\accounts\\"+logName+".txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            String nextName = reader.readLine();
            String nextPassword = reader.readLine();
            String nextFIO = reader.readLine();
            String nextAddress = reader.readLine();
            System.out.println(nextPassword+ "a" + nextAddress);
            
            File file = new File(System.getProperty("user.dir")+"\\accounts\\"+logName+".txt");
            FileWriter fr = null;
            try
            {
                fr = new FileWriter(file);
                fr.write(nextName);
                fr.append('\n');
                fr.write(nextPassword);
                fr.append('\n');
                fr.write(nextFIO);
                fr.append('\n');
                fr.write(nextAddress);
                fr.append('\n');
                fr.append('\n');
                
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
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Успешно");
             alert.setHeaderText("Поздравляем с покупкой" + '\n'+ "Приятного наслаждения музыкой");
             alert.showAndWait();
             
            Stage stage = new Stage();
            stage.setTitle("Личный кабинет");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Main1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Stage stage1 = (Stage) payBtn.getScene().getWindow();
            stage1.close();
            stage.show();
            }
             else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте платежные данные");
                alert.showAndWait();
            }
                 }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите все платежные данные");
                alert.showAndWait();
             }
           
    }

                
    public void initialize(URL url, ResourceBundle rb)          
    {
       FileInputStream inputstream; 
        try {
            inputstream = new FileInputStream(System.getProperty("user.dir")+"\\images\\bankCard.jpg");
            Image image = new Image(inputstream); 
            bankCard.setImage(image);
         } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        }
        
    }
}