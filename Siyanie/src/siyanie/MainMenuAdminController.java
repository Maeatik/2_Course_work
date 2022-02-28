package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuAdminController implements Initializable {

    @FXML
    private Button logOutBtn;
    
    @FXML
    private final ObservableList <Product> productData = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> ProductTable;

    @FXML
    private TableColumn<Product, String> NameColumn;

    @FXML
    private TableColumn<Product, String> genreColumn;

    @FXML
    private TableColumn<Product, String> AuthorColumn;

    @FXML
    private TableColumn<Product, String> CostColumn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button DeleteBtn;
    
     public boolean showProductEditDialog (Product product)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddController.class.getResource("Add.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить пластинку");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            scene.getStylesheets().add("descriptionArea.css");
            dialogStage.setScene(scene);

            AddController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    @FXML
    void AddHandler(ActionEvent event) throws FileNotFoundException
    {
        System.out.println("Проверка -1");
        Product tempProduct = new Product();
        System.out.println("Проверка 0");
        boolean okClicked = this.showProductEditDialog(tempProduct);
        System.out.println("Проверка 1");
        if (okClicked)
        {
            System.out.println("Проверка 2");
            productData.add(tempProduct);
            
            System.out.println("Проверка 3");
        }
        printCatalog();
        
    }


    @FXML
    void LogOutHandler(ActionEvent event) throws IOException 
    {
                Stage stage = new Stage();
                stage.setTitle("Вход в магазин");
                Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) logOutBtn.getScene().getWindow();
                stage1.close();
    }
    
    
    public void printCatalog ()
    {
        File file = new File("Vinyl.txt");
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file);
            fr.write("Список пластинок");
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
        ObservableList <Product> Products = ProductTable.getItems();
        for (int index = 0; index < Products.size(); index = index + 1)
        {
            Products.get(index).rePrintProduct("Vinyl.txt");
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
    }
    @Override
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
         
        for(int i = 0; i<count; i++) 
        {
            try {
                tmpProductName = readerNew.readLine();
                if (tmpProductName.length() > 0)
                {
                    tmpProductGenre = readerNew.readLine();
                    tmpProductAuthor = readerNew.readLine();
                    tmpProductCost = readerNew.readLine();
                    System.out.println(tmpProductName + " " + tmpProductCost);
                    productData.add(new Product(tmpProductName, tmpProductGenre, tmpProductAuthor, tmpProductCost));
                    i++;
                    
                }
                else
                {
                    System.out.println(i);
                    find = false;
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
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("productGenre"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("productAuthor"));
        CostColumn.setCellValueFactory(new PropertyValueFactory<>("productCost"));
        ProductTable.setItems(productData);
        }
    }

}
