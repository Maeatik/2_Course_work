package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountController implements Initializable{

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;
    
    @FXML
    private TextField searchField;

    @FXML
    private Button searchBtn;

    @FXML
    private Button bascketBtn;

    @FXML
    private Button signOutBtn;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button BackMainBtn;
    
    @FXML
    private Button changeBtn;

    

    /**@FXML
    void ChangeDataHandler(ActionEvent event) throws IOException 
    {
                Stage stage = new Stage();
                stage.setTitle("Изменение данных");
                Parent root = FXMLLoader.load(getClass().getResource("Change.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) changeBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }
**/
    @FXML
    void BackMainHandler(ActionEvent event) throws IOException {
        Stage stage = new Stage();
                stage.setTitle("Каталог");
                Parent root = FXMLLoader.load(getClass().getResource("Main1.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) HomeBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }


    @FXML
    void checkBascketHandler(ActionEvent event) throws IOException 
    {
                System.out.println("Я хочу посмотреть корзину");
                Stage stage = new Stage();
                stage.setTitle("Корзина");
                Parent root = FXMLLoader.load(getClass().getResource("Basket.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) bascketBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }

    @FXML
    void signOutHandler(ActionEvent event) throws IOException 
    {
                Stage stage = new Stage();
                stage.setTitle("Вход в магазин");
                Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) signOutBtn.getScene().getWindow();
                stage1.close();
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        FileReader fileLogReader = null;
        try {
            File logData = new File("nextUser.txt");
            fileLogReader = new FileReader(logData);
            BufferedReader logReader = new BufferedReader(fileLogReader);
            String logName = logReader.readLine();
            File userData = new File(System.getProperty("user.dir")+"\\accounts\\"+logName+".txt");
            //File userData = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Siyanie\\accounts\\"+logName+".txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            String nextName = reader.readLine();
            String nextPassword = reader.readLine();
            String nextFIO = reader.readLine();
            String nextAddress = reader.readLine();
            
            nameLabel.setText(nextFIO);
            addressLabel.setText(nextAddress);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        try {
            fileLogReader.close();
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }
}
