/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chad Romney
 */
public class Product {
    
    protected ArrayList<Part> associatedParts; 
    //protected ObservableList<Part> observAssociatedParts;
    protected IntegerProperty productID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty max;
    protected IntegerProperty min;
    
    public Product(){
        this.productID = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0.0);
        this.inStock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();
   }
    
    public ArrayList<Part> getAssociatedPartsArray() {
        return associatedParts;
    }
    
    public void setAssociatedPartsArray(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    public ObservableList<Part> getAssociatedPartsObservable() {
        ObservableList<Part> p = FXCollections.observableArrayList(this.associatedParts);
        return p;
    }
    
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    
    public String getName() {        
        return name.get();
    }
    
    public void setPrice(double price) {        
        this.price = new SimpleDoubleProperty(price);
    }
    
    public double getPrice() {
        return price.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock = new SimpleIntegerProperty(inStock);
    }
    
    public int getInStock() {
        return inStock.get();
    }
    
    public void setMin(int min) {
        this.min = new SimpleIntegerProperty(min);
    }
    
    public int getMin() {    
        return min.get();
    }
    
    public void setMax(int max) {
        this.max = new SimpleIntegerProperty(max);
    }
    
    public int getMax() {    
        return max.get();
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int partID) {   
        boolean delete = associatedParts.removeIf(part -> part.getPartID() == partID);
        return delete;        
    }
    
    public Part lookupAssociatedPart(int partID) {        
        for(Part p : associatedParts) {
            if(p.getPartID() == partID) {
                return p;
            }
        }   
        return null;         
    }
    
    public void setProductID(int productID) {   
        //this.productID.set(productID);
        this.productID = new SimpleIntegerProperty(productID);
    }
    
    public int getProductID() {     
        return productID.get();
    }  
}


