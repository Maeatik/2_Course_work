package siyanie;

import javafx.scene.control.Alert;

import java.io.*;

public class Product
{
    String
            productName,
            productGenre,
            productAuthor,
            productCost;

    public Product (String productName, String productGenre, String productAuthor, String productCost)
    {
        this.productName = productName;
        this.productGenre = productGenre;
        this.productAuthor = productAuthor;
        this.productCost = productCost;
    }

    public Product() {
        this.productName = "";
        this.productGenre = "";
        this.productAuthor = "";
        this.productCost = "";
    }

    public String getProductName ()
    {
        return productName;
    }

    public void setProductName (String productName)
    {
        this.productName = productName;
    }

    public String getProductGenre ()
    {
        return productGenre;
    }

    public void setProductGenre (String productGenre)
    {
        this.productGenre = productGenre;
    }

    public String getProductAuthor  ()
    {
        return productAuthor;
    }

    public void setProductAuthor (String productAuthor)
    {
        this.productAuthor = productAuthor;
    }

    public void setProductCost (String productCost)
    {
        this.productCost = productCost;
    }
     public String getProductCost ()
    {
       
        return productCost;
    }

    public void printProduct (String pathWithName)
    {
        File file = new File(pathWithName);
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file,true);
            fr.write('\n');
            fr.write(this.productName);
            fr.append('\n');
            fr.write(this.productAuthor);
            fr.append('\n');
            fr.write(this.productGenre);
            fr.append('\n');
            fr.write("#");
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
     public void rePrintProduct (String pathWithName)
    {
        File file = new File(pathWithName);
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file,true);
            fr.write('\n');
            fr.write(this.productName);
            fr.append('\n');
            fr.write(this.productAuthor);
            fr.append('\n');
            fr.write(this.productGenre);
            fr.append('\n');
            fr.write(this.productCost);
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

   
}
