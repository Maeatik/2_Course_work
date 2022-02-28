package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchController implements Initializable{

   @FXML
    private final ObservableList <Product> productData = FXCollections.observableArrayList();
       
    @FXML
    private TableView<Product> ProductTable;
        
    @FXML
    private TableColumn<Product, String> NameColumn;

    @FXML
    private TableColumn<Product, String> GenreColumn;

    @FXML
    private TableColumn<Product, String> AuthorColumn;

    @FXML
    private TableColumn<Product, String> CostColumn;

    @FXML
    private TextField minCostField;

    @FXML
    private TextField maxCostField;

    @FXML
    private TextField authorField;

    @FXML
    private ComboBox<String> genreField;

    @FXML
    private Button superSearchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button BackMainBtn;

    @FXML
    private Button AccountBtn;
    
    @FXML
    private Button bascketBtn1;

 

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
                Stage stage1 = (Stage)HomeBtn.getScene().getWindow();
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
                Stage stage1 = (Stage) HomeBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }

    @FXML
    void superSearchHandler(ActionEvent event) 
    {
        double selectedMin = 0.0;
        double selectedMax = 9999999.9;
    
      String selectedAuthor = authorField.getText();  
      String selectedMinString = minCostField.getText();
      {
      if (selectedMinString.equals("") ==false)
      {
      selectedMin = Double.parseDouble(selectedMinString);
      }
      String selectedMaxString = maxCostField.getText();
       if (selectedMaxString.equals("") ==false)
      {
      selectedMax = Double.parseDouble(selectedMaxString);
      }
      String selectedGenre = (String) genreField.getValue();
      
      System.out.println(selectedGenre + " "+selectedMin+ " "+ selectedMax);
      
      for ( int i = 0; i<ProductTable.getItems().size(); i++)
        {
        ProductTable.getItems().clear();
        }  
      
        if((selectedAuthor.equals("") == true) && (selectedGenre == null) && 
        (selectedMinString.equals("") == true) && (selectedMaxString.equals("") == true))
        {
                    superSearchBtn.visibleProperty().setValue(true);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Не задали параметры поиска");
                    alert.setContentText("Введите в хотя бы одно поле параметр поиска");
                    alert.showAndWait();
        }
        else
        {
        Boolean superFind = false;
        File userData = new File("Vinyl.txt");
        FileReader fileReader = null;
        try 
        {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        int count = 0;
        String
                tmpProductName = "",
                tmpProductGenre = "",
                tmpProductAuthor = "",
                tmpProductCost = "";
        try {
            String tmp = reader.readLine();
       
            while(tmp != null)
        {
            
            tmp = reader.readLine();
            count++;
            
        }
        System.out.println(count/4);
                  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        File userDataNew = new File("Vinyl.txt");
        FileReader fileReaderNew = null;
        try {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader readerNew = new BufferedReader(fileReader);
        
        
        Boolean find = false;
         try {
            String tmp = readerNew.readLine();     
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        for(int i = 0; i<count; i++) 
        {
            try {
                tmpProductName = readerNew.readLine();
               
                System.out.println(tmpProductName);
                if ((tmpProductName.length() > 0))
                {
                    
                    tmpProductGenre = readerNew.readLine();
                    tmpProductAuthor = readerNew.readLine();
                    tmpProductCost = readerNew.readLine();
                    double productCost = Double.parseDouble(tmpProductCost);
                    if ((selectedMin  == 0.0)&&(selectedMax==9999999.9))
                    {
                         if ((tmpProductGenre.equals(selectedGenre))||(tmpProductAuthor.equals(selectedAuthor)))
                         {
                             System.out.println("bez chisel");
                              productData.add(new Product(tmpProductName, tmpProductGenre, tmpProductAuthor, tmpProductCost));
                              find = true;
                         }
                    }
                    else
                    {
                    if ((tmpProductGenre.equals(selectedGenre))||(tmpProductAuthor.equals(selectedAuthor)) ||((productCost>selectedMin) && (productCost < selectedMax)))
                    {
                    productData.add(new Product(tmpProductName, tmpProductGenre, tmpProductAuthor, tmpProductCost));
                    System.out.println("s chisel");
                     find = true;
                    }
                }
                }
               
            }
            catch (IOException e)
            {
                break;
            }

    
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (find = false) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Пластинка не найдена");
                    alert.setContentText("Введите другие параметры поиска");
                    alert.showAndWait();
        }
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        GenreColumn.visibleProperty().setValue(true);
        AuthorColumn.visibleProperty().setValue(true);
        CostColumn.visibleProperty().setValue(true);
        GenreColumn.setCellValueFactory(new PropertyValueFactory<>("productGenre"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("productAuthor"));
        CostColumn.setCellValueFactory(new PropertyValueFactory<>("productCost"));
        ProductTable.setItems(productData);
            }
    
        }      
      }
      
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        
        File userData = new File("Vinyl.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        int count = 0;
        String
                tmpProductName = "",
                tmpProductGenre = "",
                tmpProductAuthor = "",
                tmpProductCost = "";
        try {
            String tmp = reader.readLine();
       
            while(tmp != null)
        {
            
            tmp = reader.readLine();
            count++;
            
        }
        System.out.println(count/4);
                  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        File userDataNew = new File("Vinyl.txt");
        FileReader fileReaderNew = null;
        try {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader readerNew = new BufferedReader(fileReader);
        
        
        Boolean find = true;
         try {
            String tmp = readerNew.readLine();     
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> genreList = new ArrayList<String>();
        for(int i = 0; i<count; i++) 
        {
            try {
                tmpProductName = readerNew.readLine();
                System.out.println(tmpProductName);
                if (tmpProductName.length() > 0)
                {
                    tmpProductGenre = readerNew.readLine();
                    tmpProductAuthor = readerNew.readLine();
                    System.out.println(tmpProductGenre + tmpProductAuthor);
                    tmpProductCost = readerNew.readLine();
                }
            }
            catch (IOException e)
            {
                break;
            }

    
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (genreList.contains(tmpProductGenre) == false)
        {
        genreList.add(tmpProductGenre);
        }            
       }
        genreField.getItems().addAll(genreList); 
    }

    
 

}