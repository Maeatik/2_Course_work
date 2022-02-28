package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainMenuController implements Initializable{

    @FXML
    private TextField searchField;

    @FXML
    private Button searchBtn;

    @FXML
    private Button bascketBtn;

    @FXML
    private Button AccountBtn;
    
    @FXML
    private Button OpenBtn;
    
    @FXML
    private Button superSearchBtn;
    
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
    private Label nameLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label costLabel;


    @FXML
    private void showProductDetails (Product product)
    {
        if (product != null)
        {
            nameLabel.setText(product.getProductName().toString());
            genreLabel.setText(product.getProductGenre().toString());
            authorLabel.setText(product.getProductAuthor().toString());
            costLabel.setText(product.getProductCost().toString());
        }
        else
        {
            nameLabel.setText("");
            genreLabel.setText("");
            authorLabel.setText("");
            costLabel.setText("");
        }
    }
    public void printCatalog()
    {
        File file = new File("Vinyl.txt");
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file);
            fr.write("Список пластинок");
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
        ObservableList <Product> Products = ProductTable.getItems();
        for (int index = 0; index < Products.size(); index = index + 1)
        {
            Products.get(index).printProduct("g.txt");
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
        Stage stage1 = (Stage) searchBtn.getScene().getWindow();
        stage1.close();
        stage.show();
    }
    @FXML
    void openHandler(ActionEvent event) throws IOException 
    {
        String Name = null,
               Author = null, 
               Genre = null,
               Cost = null;
        Product selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct != null)
        {
        Name =  NameColumn.getCellData(selectedProduct);
        Author = AuthorColumn.getCellData(selectedProduct);
        Genre = genreColumn.getCellData(selectedProduct);
        Cost = CostColumn.getCellData(selectedProduct);
        
        PrintWriter writer = new PrintWriter("item.txt", "UTF-8");
        writer.println(Name);
        writer.println(Author);
        writer.println(Genre);
        writer.println(Cost);
        writer.close();
        Stage stage = new Stage();
        stage.setTitle(Name);
        Parent root = FXMLLoader.load(getClass ().getResource("Page.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) nameLabel.getScene().getWindow();
        stage1.close();
        System.out.println(NameColumn.getCellData(selectedProduct));
     
        writer.print("");
        writer.close();
        }
        else
        {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Пластинка не найдена");
                alert.setContentText("Выберите пластинку, которую хотите посмотреть");
                alert.showAndWait();   
                
        }
    }
    
    @FXML
    void SearchPlastHandler(ActionEvent event) 
    {
        String name = searchField.getText();
        
        if (!(name.equals("")))
        {
            for ( int i = 0; i<ProductTable.getItems().size(); i++)
            {
                ProductTable.getItems().clear();
            }       

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File userDataNew = new File("Vinyl.txt");
        FileReader fileReaderNew = null;
        try {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader readerNew = new BufferedReader(fileReader);
        
       
        
        try {
            String tmp = readerNew.readLine();     
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean find = false;
        for(int i = 0; i<count; i++) 
        {
                try {
                    tmpProductName = readerNew.readLine();
                    if ((tmpProductName.length() > 0) && (tmpProductName.equals(name)))
                    {
                        tmpProductGenre = readerNew.readLine();
                        tmpProductAuthor = readerNew.readLine();
                        tmpProductCost = readerNew.readLine();
                        productData.add(new Product(tmpProductName, tmpProductGenre, tmpProductAuthor, tmpProductCost));
                        find = true;    
                    }

                }
                catch (IOException e)
                {
                    break;
                }

            NameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("productGenre"));
            AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("productAuthor"));
            CostColumn.setCellValueFactory(new PropertyValueFactory<>("productCost"));
            ProductTable.setItems(productData);

            showProductDetails(null);

            ProductTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
            superSearchBtn.visibleProperty().setValue(false);
            OpenBtn.visibleProperty().setValue(true);
            if (i == (count-2))
            {   
                
                System.out.println("dsad");
                if (!(find))
                {
                   
                    OpenBtn.visibleProperty().setValue(false);
                    superSearchBtn.visibleProperty().setValue(true);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Сообщение об ошибке");
                    alert.setContentText("Пластинка не найдена");
                    alert.showAndWait();
                    
                }
            }
        }   
        }
        else
        {
        for ( int i = 0; i<ProductTable.getItems().size(); i++)
        {
        ProductTable.getItems().clear();
        } 
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
                System.out.println(tmpProductName);
                if (tmpProductName.length() > 0)
                {
                    tmpProductGenre = readerNew.readLine();
                    tmpProductAuthor = readerNew.readLine();
                    System.out.println(tmpProductGenre + tmpProductAuthor);
                    tmpProductCost = readerNew.readLine();
                    productData.add(new Product(tmpProductName, tmpProductGenre, tmpProductAuthor, tmpProductCost));
                    
                }
                else
                {
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

        showProductDetails(null);
        ProductTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
    }
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Введите название пластинки");
            alert.showAndWait();
        }
    }
    
    
    @FXML
    void superSearchHandler(ActionEvent event) throws IOException 
    {
        
                Stage stage = new Stage();
                stage.setTitle("Корзина");
                Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) searchBtn.getScene().getWindow();
                stage1.close();
                stage.show();
    }
    
    
    @FXML
    void checkBascketHandler(ActionEvent event) throws IOException 
    {
                Stage stage = new Stage();
                stage.setTitle("Корзина");
                Parent root = FXMLLoader.load(getClass().getResource("Basket.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage stage1 = (Stage) searchBtn.getScene().getWindow();
                stage1.close();
                stage.show();
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

        showProductDetails(null);
        ProductTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
    }
    }
    }
