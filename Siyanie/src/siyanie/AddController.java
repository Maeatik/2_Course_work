package siyanie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productIdField;

    @FXML
    private TextField productCountField;

    @FXML
    private TextField productSumField;
    
    @FXML
    private TextField pathnameField;
    
    @FXML
    private TextArea descriptionArea;
    
    public String imageName;
    public String imagePath;
    
    private Stage dialogStage;
    private  Product product;
    private boolean okClicked = false;

     public void setDialogStage (Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    public void setProduct (Product product)
    {
        this.product = product;

        productNameField.setText(product.getProductName());
        productIdField.setText(product.getProductGenre());
        productCountField.setText(product.getProductAuthor());
        productSumField.setText(product.getProductCost());
    }

    public boolean isOkClicked ()
    {
        return okClicked;
    }

    private boolean isInputValid ()
    {
        String errorMessage = "";
        if ((productNameField.getText() == null) || (productNameField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Не указано название\n";
        }
        if ((productIdField.getText() == null) || (productIdField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Не указан жанр\n";
        }
        if ((productCountField.getText() == null) || (productCountField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Не указан автор\n";
        }
        if ((productSumField.getText() == null) || (productSumField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Не указана стоимость\n";
        }
        if ((pathnameField.getText() == null) || (pathnameField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Не указан путь кизображения\n";
        }
        if (errorMessage.length() == 0)
        {
            return true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleOk () throws FileNotFoundException
    {
        if (isInputValid())
        {
            product.setProductName(productNameField.getText());
            product.setProductGenre(productIdField.getText());
            product.setProductAuthor(productCountField.getText());
            product.setProductCost(productSumField.getText());
            product.printProduct("Vinyl.txt");
            
            imageName = productNameField.getText();
            imagePath = pathnameField.getText();
            File fileP = new File("Images.txt");
            PrintStream filePrintStreamP = new PrintStream(new FileOutputStream(fileP, true));
            filePrintStreamP.println(imageName);
            filePrintStreamP.println("images\\"+imagePath);
            filePrintStreamP.close();

            File fileD = new File("Description.txt");
            PrintStream filePrintStreamD = new PrintStream(new FileOutputStream(fileD, true));
            filePrintStreamD.println(imageName);
            filePrintStreamD.println(descriptionArea.getText());
            filePrintStreamD.println("#");
            filePrintStreamD.close();
            okClicked = true;
            dialogStage.close();
        }

    }
   

    @FXML
    private void handleCancel ()
    {
        dialogStage.close();
    }
}