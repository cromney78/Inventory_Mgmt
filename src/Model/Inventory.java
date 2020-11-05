/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chad Romney
 */
public class Inventory {
    
    private static ObservableList<Product> products = FXCollections.observableArrayList(); 
    private static ObservableList<Part> allParts = FXCollections.observableArrayList(); 
    
    //part & prodID need to be static so they will hold numbers.
    private static int prodID = 0;
    private static int partID = 0;
    
    public static ObservableList<Product> getProductsList() {
        return products;
    }
    
    public static ObservableList<Part> getPartsList() {
        return allParts;
    }
    
    public static void addProduct(Product product) { 
        products.add(product);
    }
    
    public static boolean removeProduct(int productID) {
        boolean remove = products.removeIf(part -> part.getProductID() == productID);
        return remove;
    }
   
    public static Product lookupProduct(int productID) {
        for(Product p : products) {
            if(p.getProductID() == productID) {
                return p;
            }
        }   
        return null;
    }
    
    public static void updateProduct(int index, Product product) {
        products.set(index, product);
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
    
    public static boolean deletePart(int partID) {
        //allPartsList.remove(partID);
        boolean delete = allParts.removeIf(part -> part.getPartID() == partID);
        return delete;
    }
    
    public static Part lookupPart(int partID) {
        for(Part part : allParts) {
            if(part.getPartID() == partID) {
                return part;
            }
        }   
        return null; //no part found
    }
    
    public static void updatePart(int index, Part part){
        allParts.set(index, part);
    }
    
    //add and remove prod and part id
    public static int getPartID() {
        partID++;
        return partID;
    }
    
    public static int removePartID() {
        partID--;
        return partID;
    }
    
    public static int getProdID() {
        prodID++;
        return prodID;
    }
    
    public static int removeProdID() {
        prodID--;
        return prodID;
    }
    
}

