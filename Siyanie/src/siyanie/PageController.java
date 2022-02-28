package siyanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PageController  implements Initializable{

    @FXML
    private Label choosenName;

    @FXML
    private Label choosenAuthor;

    @FXML
    private Label choosenGenre;

    @FXML
    private Label choosenCost;
        
    @FXML
    private Button addBasket;

    @FXML
    private Label choosenDescription;

    @FXML
    private Button bascketBtn;

    @FXML
    private Button AccountBtn;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button addBasket1;
    
    @FXML
    private Button BackMainBtn;
 
    @FXML
    private ImageView choosenImage;

    @FXML
    void AccountHandler(ActionEvent event) throws IOException {
        System.out.println("Я хочу посмотреть свой аккаунт");
        Stage stage = new Stage();
        stage.setTitle("Личный кабинет");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Stage stage1 = (Stage) AccountBtn.getScene().getWindow();
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
    void addBascketHandler(ActionEvent event) throws IOException 
    {
        String
               Name = choosenName.getText(),
               Author = choosenAuthor.getText(), 
               Genre = choosenGenre.getText(),
               Cost = choosenCost.getText();
        
            File userData = new File("nextUser.txt");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);
            String nextName = reader.readLine();
            /**File checkVinyl = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Siyanie\\accounts\\"+nextName+".txt");
            FileReader fileReaderVinyl = new FileReader(checkVinyl);
            BufferedReader readerVinyl = new BufferedReader(fileReaderVinyl);
                String VinylName;
                Integer VinylCount;
                String tmp = readerVinyl.readLine();
                tmp = readerVinyl.readLine();
                tmp = readerVinyl.readLine();
                tmp = readerVinyl.readLine();
                tmp = readerVinyl.readLine();
                VinylName = readerVinyl.readLine();
                System.out.println(Viny);**/
                File file = new File(System.getProperty("user.dir")+"\\accounts\\"+nextName+".txt");
                FileWriter fr = null;
                       try {
                           fr = new FileWriter(file,true);
                           fr.write(Name);
                           fr.append('\n');
                           fr.write(Cost);
                           fr.append('\n');
                           fr.write("1");
                           fr.append('\n');
                           fr.write("#");
                           fr.append('\n');
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       fr.close();
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
    private void showProductDetails (Product product)
    {
        if (product != null)
        {
            choosenName.setText(product.getProductName().toString());
            choosenGenre.setText(product.getProductGenre().toString());
            choosenAuthor.setText(product.getProductAuthor().toString());
            choosenCost.setText(product.getProductCost().toString());
        }
        else
        {
            choosenName.setText("");
            choosenGenre.setText("");
            choosenAuthor.setText("");
            choosenCost.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        File itemData = new File("item.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(itemData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
  
        try 
        {
            choosenName.setText(reader.readLine());
            choosenAuthor.setText(reader.readLine());
            choosenGenre.setText(reader.readLine());
            choosenCost.setText(reader.readLine());
        }
        catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }      
        try {
            fileReader.close();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String thisName = choosenName.getText();
        
        File chooseDescription = new File("Description.txt");
        FileReader fileReaderDesc = null;
        try {
            fileReaderDesc = new FileReader(chooseDescription);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader readerDesc = new BufferedReader(fileReaderDesc);
        try {
            Boolean find = false;
            String DescName = readerDesc.readLine();
            while ((DescName != null) && !(find))
            {
                if (DescName.equals(thisName))
                {
                    String Description = readerDesc.readLine();
                    String stringDesc = "   ";
                    while (!(Description.equals("#")))
                    {
                        stringDesc = stringDesc + Description + '\n';
                        Description = readerDesc.readLine();
                    }
                    choosenDescription.setText(stringDesc);
                    find = true;
                }
                else
                {
                String tmp = readerDesc.readLine();
                while (!(tmp.equals("#")))
                    {
                        tmp = readerDesc.readLine();
                    }
                }
                DescName = readerDesc.readLine();
            }
            if (find == false)
            {
                choosenDescription.setText("Описание пластинки отсутствует");
            }
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            fileReaderDesc.close();
            readerDesc.close();
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        File imageData = new File("Images.txt");
        FileReader imageReader = null;
        try {
            imageReader = new FileReader(imageData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader iReader = new BufferedReader(imageReader);
       
        try {
            Boolean find = false;
            String ImageName = iReader.readLine();
            while ((ImageName != null) && !(find))
            {
                
                if (ImageName.equals(thisName))
                {
                    String ImageV = iReader.readLine();
                    
                    FileInputStream inputstream = new FileInputStream(ImageV);
                    
                    Image image = new Image(inputstream); 
                    choosenImage.setImage(image);
                    find = true;
                }
                else
                {
                   iReader.readLine();
                   ImageName = iReader.readLine();
                }
            }
            if (find == false)
            {
                String ImageV = System.getProperty("user.dir")+"\\images\\false.png";
                FileInputStream inputstream = new FileInputStream(ImageV); 
                Image image = new Image(inputstream); 
                choosenImage.setImage(image);
            }
        
          } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            imageReader.close();
            iReader.close();
        } catch (IOException ex) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
