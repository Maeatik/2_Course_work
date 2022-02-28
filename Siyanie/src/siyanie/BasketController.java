package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BasketController implements Initializable {

    
    @FXML
    private TextField searchField;

    @FXML
    private Button searchBtn;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button BackMainBtn;

    @FXML
    private Button AccountBtn;

    @FXML
    private Button PayBtn;
    
    @FXML
    private final ObservableList <Product> backetData = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> backetTable;

    @FXML
    private TableColumn<Product, String> backetName;

    @FXML
    private TableColumn<Product, String> backetCount;
    
    @FXML
    private TableColumn<Product, String> backetCost;
    @FXML
    private TableColumn<Product, String> tmp;
    @FXML
    private Button deleteBtn;

    @FXML
    private Label finalCost;
    
    @FXML
    private TextArea checkVinyl;
    
    

    @FXML
    void AccountHandler(ActionEvent event) throws IOException 
    {
        System.out.println("Я хочу посмотреть свой аккаунт");
        Stage stage = new Stage();
        stage.setTitle("Личный кабинет");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Stage stage1 = (Stage) HomeBtn.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void BackMainHandler(ActionEvent event) throws IOException
    {
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
    void PayHandler(ActionEvent event) throws IOException 
    {
                Stage stage = new Stage();
                stage.setTitle("Оплата");
                Parent root = FXMLLoader.load(getClass().getResource("Pay.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) HomeBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }
    
    @FXML
    void deleteHandler(ActionEvent event) throws FileNotFoundException, IOException 
    {
        int selectedIndex = backetTable.getSelectionModel().getFocusedIndex();
        if (selectedIndex >= 0)
        {
            backetTable.getItems().remove(selectedIndex);
            
            File logData = new File("nextUser.txt");
            FileReader fileLogReader = new FileReader(logData);
            BufferedReader logReader = new BufferedReader(fileLogReader);
            String logName = logReader.readLine();
            
            File userData = new File("accounts\\"+logName+".txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            
            String nextName = reader.readLine();
            String nextPassword = reader.readLine();
            String nextFIO = reader.readLine();
            String nextAddress = reader.readLine();
            
            System.out.println(nextPassword+ "a" + nextAddress);
            File file = new File("accounts\\"+nextName+".txt");
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
            ObservableList <Product> Products = backetTable.getItems();
            for (int index = 0; index < Products.size(); index = index + 1)
            {
                Products.get(index).printProduct("accounts\\"+nextName+".txt");
            }
            try
            {
                fr = new FileWriter(file, true);
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
            
            String outputText = "";
            Double sumCost = 0.0;
            for (int i = 0; i<backetTable.getItems().size(); i++)

            {
                outputText = outputText + backetData.get(i).productName+ "....." +backetData.get(i).productAuthor + " pуб. "+'\n' ;
                sumCost = sumCost + Double.parseDouble(backetData.get(i).productAuthor);

            }       

            checkVinyl.setText(outputText);
            finalCost.setText("Итого к оплате: " + String.valueOf(sumCost) + "руб.");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(null);
                alert.setTitle("Не выделено");
                alert.setHeaderText("Не выбран товар");
                alert.setContentText("Выберите товар в таблице");
                alert.showAndWait();
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            File userData = new File("nextUser.txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            String nextName = reader.readLine();
        

            File chooseVinyl = new File("accounts\\"+nextName+".txt");
            FileReader fileReaderVinyl = null;
            try {
                fileReaderVinyl = new FileReader(chooseVinyl);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader readerVinyl = new BufferedReader(fileReaderVinyl);
            String
                   VinylName = null,
                   VinylCostStr = null,
                   VinylCountStr = null;
            Integer
                    VinylCount = 0;
            Double
                    VinylCost = 0.0;
            try {
                readerVinyl.readLine();
                readerVinyl.readLine();
                readerVinyl.readLine();
                readerVinyl.readLine();
                readerVinyl.readLine();
                VinylName = readerVinyl.readLine();
                
               
                    System.out.println(VinylName);
                    while (VinylName != null)
                    {
                        System.out.println("true");
                        VinylCostStr = readerVinyl.readLine();
                        VinylCountStr = readerVinyl.readLine();
                        System.out.println(VinylCostStr +" fsdf "+VinylCountStr);
                        VinylCost = Double.parseDouble(VinylCostStr);
                        VinylCount = Integer.parseInt(VinylCountStr);                       
                        backetData.add(new Product(VinylName, VinylCountStr, VinylCostStr, null));
                        readerVinyl.readLine();
                        VinylName = readerVinyl.readLine();
   
                     }
                
                backetName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                backetCount.setCellValueFactory(new PropertyValueFactory<>("productGenre"));
                backetCost.setCellValueFactory(new PropertyValueFactory<>("productAuthor"));
                tmp.setCellValueFactory(new PropertyValueFactory<>("productCost"));
                backetTable.setItems(backetData);
            } catch (IOException ex) {
                ex.printStackTrace();        
            } 
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        String outputText = "";
        Double sumCost = 0.0;
        for (int i = 0; i<backetTable.getItems().size(); i++)
            
        {
            outputText = outputText + backetData.get(i).productName+ "....." +backetData.get(i).productAuthor + " pуб. "+'\n' ;
            sumCost = sumCost + Double.parseDouble(backetData.get(i).productAuthor);
            
        }       

        checkVinyl.setText(outputText);
        finalCost.setText("Итого к оплате: " + String.valueOf(sumCost) + "руб.");
        
      
    }
    
    
}
