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
import java.util.ArrayList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chad Romney
 */
public class ModifyProductController implements Initializable {

    @FXML    private TextField txtFieldAddProduct;
    @FXML    private TextField txtFieldModifyProdName;
    @FXML    private TextField txtFieldModifyProdInv;
    @FXML    private TextField txtFieldModifyProdPrice;
    @FXML    private TextField txtFieldModifyProdMin;
    @FXML    private TextField txtFieldModifyProdMax;
    @FXML    private TableColumn<Part, Integer> tblPartID;
    @FXML    private TableColumn<Part, String> tblPartName;
    @FXML    private TableColumn<Part, Integer> tblInventory;
    @FXML    private TableColumn<Part, Double> tblPPU;
    @FXML    private Button btnAddProd;
    @FXML    private TableColumn<Part, Integer> tblPartIDDelete;
    @FXML    private TableColumn<Part, String> tblPartNameDelete;
    @FXML    private TableColumn<Part, Integer> tblInventoryDelete;
    @FXML    private TableColumn<Part, Double> tblPPUDelete;
    @FXML    private Button btnDeleteProd;
    @FXML    private Button btnSaveProd;
    @FXML    private Button btnCancelProd;
    @FXML    private Button btnSearchProd;
    @FXML    private TableView<Part> tblDeleteProd;
    @FXML    private TableView<Part> tblAddProd;
    @FXML    private TextField searchText;

    private Product prod;
    private Inventory inven;
    protected ArrayList<Part> associatedPartsArray;
    private ObservableList<Part> prodPartsList = FXCollections.observableArrayList();
    //private ObservableList<Part> tempPartsList = FXCollections.observableArrayList();
    private int index;
    
    
            
    public void setProduct(int index, Product prod){
            this.prod = prod;
            this.index = index;
            
            //set to associatedParts to tempList so it can be edited
            prodPartsList = prod.getAssociatedPartsObservable();
          
            txtFieldAddProduct.setText(Integer.toString(prod.getProductID()));
            txtFieldModifyProdName.setText(prod.getName());
            txtFieldModifyProdInv.setText(Integer.toString(prod.getInStock()));
            txtFieldModifyProdPrice.setText(Double.toString(prod.getPrice()));
            txtFieldModifyProdMin.setText(Integer.toString(prod.getMin()));
            txtFieldModifyProdMax.setText(Integer.toString(prod.getMax()));
            tblDeleteProd.setItems(prodPartsList);
    }
    
    
    @FXML
    void btnAddProdPressed(ActionEvent event) {
        Part p = tblAddProd.getSelectionModel().getSelectedItem();
        
        try {
            if (p == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.setContentText("Select a part to continue.");
                alert.showAndWait();
            }
            else {
                prodPartsList.add(p);
                updateBtmTableView();
            }
        } catch (Exception e) {System.out.println(e);}
    }
    
    @FXML
    void btnCancelProdPushed(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            returnToMain(event);
        }
    }

    @FXML
    void btnDeleteProdPressed(ActionEvent event) {
        Part p = tblDeleteProd.getSelectionModel().getSelectedItem();

        //show alert to make sure they want to delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Part?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            prodPartsList.remove(p);
        }
        tblDeleteProd.setItems(prodPartsList);
    }

    @FXML
    void btnSaveProdPressed(ActionEvent event) throws Exception {
        String productID = txtFieldAddProduct.getText();
        String name = txtFieldModifyProdName.getText();
        String inventory = txtFieldModifyProdInv.getText();
        String price = txtFieldModifyProdPrice.getText();
        String min = txtFieldModifyProdMin.getText();
        String max = txtFieldModifyProdMax.getText();
        
        String error = "";
        if (name.equals("")){
            error = error + ("Name cannot be blank\n");}
        if (Integer.parseInt(inventory) < 0){
            error = error + ("Inventory must be greater than 0.\n");}
        if (Double.parseDouble(price) < 0){
            error = error + ("Price must be greater than 0.\n");}
        if (Integer.parseInt(max) < Integer.parseInt(min)){
            error = error + ("Maximum must be more than minimum.\n");}
        if (Integer.parseInt(inventory) < Integer.parseInt(min)){
            error = error + ("Inventory must be greater than Minimum.\n");}
        if (Integer.parseInt(inventory) > Integer.parseInt(max)){
            error = error + ("Inventory must be less than Maximum.\n");}
        
        //Ensure that the price of product cannot be less than the cost of the parts
        double partsTotalPrice = 0;
        ArrayList<Part> nParts = new ArrayList<>();
        nParts.addAll(tblDeleteProd.getItems());
        
        for (Part p : nParts) {
            partsTotalPrice = partsTotalPrice + p.getPrice();
            System.out.println("partsTotalPrice = " + partsTotalPrice);
        }
        
        if (Double.parseDouble(price) < partsTotalPrice){
            error = error + ("Price of product cannot be less than the cost of the parts\n");}
        
        if (error.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot add product");
            alert.setContentText(error); //display the error mssg
            alert.showAndWait();
        }    
        else {
            Product p = new Product();
                p.setProductID(Integer.parseInt(productID));
                p.setName(name);
                p.setInStock(Integer.parseInt(inventory));
                p.setPrice(Double.parseDouble(price));
                p.setMin(Integer.parseInt(min));
                p.setMax(Integer.parseInt(max));
                
                p.setAssociatedPartsArray(nParts);
                inven.updateProduct(index, p);
                
            returnToMain(event);
        }
    }

    @FXML
    void btnSearchProdPressed(ActionEvent event) {
        String searchTerm = searchText.getText();
        boolean found = false;
        
        if(searchTerm.equals("")) { //if search is blank
            tblAddProd.setItems(inven.getPartsList());
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
                tblAddProd.setItems(inven.getPartsList());
            }
            if (found == true) {
                ObservableList<Part> searchList = FXCollections.observableArrayList();
                searchList.add(p);
                tblAddProd.setItems(searchList);
            }
        }
    }
    
    public void returnToMain(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene addPartScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(addPartScene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTopTableView(); 
        updateBtmTableView(); 
    }    
    
    //get all available parts
    public void updateTopTableView(){ 
        tblPartID.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        tblPartName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        tblInventory.setCellValueFactory(cellData -> cellData.getValue().getInStockProperty().asObject());
        tblPPU.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        tblAddProd.setItems(inven.getPartsList());
    }
    
    //get the parts in the product
    public void updateBtmTableView(){
        tblPartIDDelete.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        tblPartNameDelete.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        tblInventoryDelete.setCellValueFactory(cellData -> cellData.getValue().getInStockProperty().asObject());
        tblPPUDelete.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        tblDeleteProd.setItems(prodPartsList);
    }
}
