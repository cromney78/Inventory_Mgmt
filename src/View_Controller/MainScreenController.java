/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chad Romney
 */
public class MainScreenController implements Initializable {

    @FXML    private Button btnPartSearch;
    @FXML    private TextField partSearchTxt;
    @FXML    private Button btnAddPart;
    @FXML    private Button btnModifyPart;
    @FXML    private Button btnDeletePart;
    @FXML    private TableView<Part> partTable;
    @FXML    private TableColumn<Part, Integer> colPartID;
    @FXML    private TableColumn<Part, String> colPartName;
    @FXML    private TableColumn<Part, Integer> colPartInventory;
    @FXML    private TableColumn<Part, Double> colPartPrice;
    @FXML    private Button btnProdSearch;
    @FXML    private TextField prodSearchTxt;
    @FXML    private Button btnAddProd;
    @FXML    private Button btnModifyProd;
    @FXML    private Button btnDeleteProd;
    @FXML    private TableView<Product> prodTable;
    @FXML    private TableColumn<Product, Integer> colProdID;
    @FXML    private TableColumn<Product, String> colProdName;
    @FXML    private TableColumn<Product, Integer> colProdInventory;
    @FXML    private TableColumn<Product, Double> colProdPrice;
    @FXML    private Button btnExit;
    
    private Inventory inven;
    
    @FXML
    void btnAddPartPushed(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        Scene addPartScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(addPartScene);
        stage.show();
    }

    @FXML
    void btnAddProductPushed(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        Scene addPartScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(addPartScene);
        stage.show();
    }

    @FXML
    void btnDeletePartPressed(ActionEvent event) throws Exception {
        Part p = partTable.getSelectionModel().getSelectedItem();
        
        //show alert to make sure they want to delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Part?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            int partID = p.getPartID();
            inven.deletePart(partID);
        }
    }

    @FXML
    void btnDeleteProdPressed(ActionEvent event) throws Exception {
        Product p = prodTable.getSelectionModel().getSelectedItem();
        
        //show alert to make sure they want to delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            int prodID = p.getProductID();
            inven.removeProduct(prodID);
        }
    }

    @FXML
    void btnExitPressed(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Program?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    
    @FXML
    void btnModifyPartPressed(ActionEvent event) throws Exception{
        
        Part mPart = partTable.getSelectionModel().getSelectedItem();
        int index = partTable.getSelectionModel().getSelectedIndex();
        
        //check the index
        System.out.println("main index = " + index);
        
        try {
            if (mPart == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("No Part");
                alert.setHeaderText("Part not selected");
                alert.setContentText("Select a part to continue.");
                alert.showAndWait();
            }
            else {
                System.out.println("Part = " + mPart.getName());
                
                Stage stage; 
                Parent root;       
                stage=(Stage) btnModifyPart.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModPart.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("Modify Part");
                stage.setScene(scene);
                stage.show();
                ModPartController controller = loader.getController();
                controller.setPart(index, mPart);
                
                //System.out.println("Part = " + part);
            }
        } catch (Exception e) {System.out.println(e);}
    }

    @FXML
    void btnModifyProdPressed(ActionEvent event) throws Exception {
        
        Product mProd = prodTable.getSelectionModel().getSelectedItem();
        int index = prodTable.getSelectionModel().getSelectedIndex();
        
        try {
            if (mProd == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("No Product");
                alert.setHeaderText("Product not selected");
                alert.setContentText("Select a product to continue.");
                alert.showAndWait();
            }
            else {
                Stage stage; 
                Parent root;       
                stage=(Stage) btnModifyProd.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("Modify Product");
                stage.setScene(scene);
                stage.show();
                ModifyProductController controller = loader.getController();
                controller.setProduct(index, mProd);
            }
        } catch (Exception e) {System.out.println(e);}
    }

    @FXML
    void btnPartSearchPressed(ActionEvent event) throws Exception {
        String searchTerm = partSearchTxt.getText();
        boolean found = false;
        
        if(searchTerm.equals("")) { //if search is blank
            partTable.setItems(inven.getPartsList());
        }
        else {
            int id = Integer.parseInt(searchTerm);
            Part p = inven.lookupPart(id);
            if (p != null) {
                found = true;
            }
            if (found == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part Not Found");
                alert.setContentText("Please search by Part ID");
                alert.showAndWait();
                
                //not found return all parts
                partTable.setItems(inven.getPartsList());
            }
            if (found == true) {
                ObservableList<Part> partSearchList = FXCollections.observableArrayList();
                partSearchList.add(p);
                partTable.setItems(partSearchList);
            }
        }
    }

    @FXML
    void btnProdSearchPressed(ActionEvent event) throws Exception {
        String searchTerm = prodSearchTxt.getText();
        boolean found = false;
        
        if(searchTerm.equals("")) { //if search is blank
            prodTable.setItems(inven.getProductsList());
        }
        else {
            int id = Integer.parseInt(searchTerm);
            Product p = inven.lookupProduct(id);
            if (p != null) {
                found = true;
            }
            if (found == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Product Not Found");
                alert.setContentText("Please search by Product ID");
                alert.showAndWait();
                
                //not found return all parts
                prodTable.setItems(inven.getProductsList());
            }
            if (found == true) {
                ObservableList<Product> prodSearchList = FXCollections.observableArrayList();
                prodSearchList.add(p);
                prodTable.setItems(prodSearchList);
            }
        }
    }
    
    @FXML
    public void updateTableViews() {
        partTable.setItems(inven.getPartsList());
        prodTable.setItems(inven.getProductsList());
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProdID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colProdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProdInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        colProdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateTableViews();
    }    
    

}
