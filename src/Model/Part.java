/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chad Romney
 */
public abstract class Part {
    
    protected IntegerProperty partID;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty inStock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    public Part() {
        partID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    public StringProperty getNameProperty() {
        return name;
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }

    public DoubleProperty getPriceProperty() {
        return price;
    }

    public double getPrice() {
        return this.price.get();
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public IntegerProperty getInStockProperty() {
        return inStock;
    }

    public int getInStock() {
        return this.inStock.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public IntegerProperty getMinProperty() {
        return min;
    }

    public int getMin() {
        return this.min.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty getMaxProperty() {
        return max;
    }

    public int getMax() {
        return this.max.get();
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }

    public IntegerProperty getPartIDProperty() {
        return partID;
    }
    
    public int getPartID() {
        return this.partID.get();
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }
}

