/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chad Romney
 */
public class OutsourcedPart extends Part {
    
    private final StringProperty companyName;
    
    public OutsourcedPart() {
        super();
        companyName = new SimpleStringProperty();
    }
    
//    public OutsourcedPart(Integer partID, String name, Double price, Integer inStock, Integer min, Integer max, String companyName) {
//        this.partID = new SimpleIntegerProperty(partID);
//        this.name = new SimpleStringProperty(name);
//        this.price = new SimpleDoubleProperty(price);
//        this.inStock = new SimpleIntegerProperty(inStock);
//        this.min = new SimpleIntegerProperty(min);
//        this.max = new SimpleIntegerProperty(max);
//        this.companyName = new SimpleStringProperty(companyName);
//    }
    
    public StringProperty setCompanyNameProperty() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public String getCompanyName() {
        return companyName.get();
    }  
}

