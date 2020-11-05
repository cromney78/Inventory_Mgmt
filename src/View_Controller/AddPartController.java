/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chad Romney
 */
public class AddPartController implements Initializable {

    @FXML    private Button btnAddPartSave;
    @FXML    private Button btnAddPartCancel;
    @FXML    private TextField txtFieldID;
    @FXML    private TextField txtFieldName;
    @FXML    private TextField txtFieldInv;
    @FXML    private TextField txtFieldPrice;
    @FXML    private TextField txtFieldMax;
    @FXML    private TextField txtFieldMin;
    @FXML    private Label lblMachineID;
    @FXML    private TextField txtFieldMachineID;
    @FXML    private RadioButton radioInHouse;
    @FXML    private RadioButton radioOutsourced;
    
    private String machineID = "";
    private String name = "";
    private int partID;
    private Inventory inven;
    
    
    @FXML
    void btnAddPartSave(ActionEvent event) throws Exception {
        name = txtFieldName.getText();
        String inventory = txtFieldInv.getText();
        String price = txtFieldPrice.getText();
        String max = txtFieldMax.getText();
        String min = txtFieldMin.getText();
        name = txtFieldName.getText();
        machineID = txtFieldMachineID.getText();
        
        String error = "";
        if (name.equals("")){error = error + ("Name cannot be blank\n");}
        if (Integer.parseInt(inventory) < 0){error = error + ("Inventory must be greater than 0.\n");}
        if (Double.parseDouble(price) < 0){error = error + ("Price must be greater than 0.\n");}
        if (Integer.parseInt(max) < Integer.parseInt(min)){error = error + ("Maximum must be more than minimum.\n");}
        if (Integer.parseInt(inventory) < Integer.parseInt(min)){error = error + ("Inventory must be greater than Minimum.\n");}
        if (Integer.parseInt(inventory) > Integer.parseInt(max)){error = error + ("Inventory must be less than Maximum.\n");}
        if (machineID.equals("")){error = error + ("Machine/Company field cannot be blank.");}

        if(error.length() > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error adding part");
            alert.setHeaderText("Cannot add part");
            alert.setContentText(error); //display the error mssg
            alert.showAndWait();
        }    
        else {    
            if(radioInHouse.isSelected()){//inhouse == true){
                InhousePart iPart = new InhousePart();
                    iPart.setPartID(partID);
                    iPart.setName(name);
                    iPart.setInStock(Integer.parseInt(inventory));
                    iPart.setPrice(Double.parseDouble(price));
                    iPart.setMax(Integer.parseInt(max));
                    iPart.setMin(Integer.parseInt(min));
                    iPart.setMachineID(Integer.parseInt(machineID));
                inven.addPart(iPart);
                System.out.println("addPart inhouse part");
                }
                else {
                    OutsourcedPart oPart = new OutsourcedPart();
                        oPart.setPartID(partID);
                        oPart.setName(name);
                        oPart.setInStock(Integer.parseInt(inventory));
                        oPart.setPrice(Double.parseDouble(price));
                        oPart.setMax(Integer.parseInt(max));
                        oPart.setMin(Integer.parseInt(min));
                        oPart.setCompanyName(machineID);
                    inven.addPart(oPart);
                    System.out.println("addPart outsourced part");
                }
            returnToMain(event);
        } 
    }

    @FXML
    void btnCancelPushed(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            inven.removePartID();
            returnToMain(event);
        }
    }

    @FXML
    void toggleInHousePart(ActionEvent event) {
        lblMachineID.setText("Machine ID");
        txtFieldMachineID.setPromptText("Machine ID");
        radioInHouse.setSelected(true);
        radioOutsourced.setSelected(false);
    }
    
    @FXML 
    void toggleOutsourcedPart(ActionEvent event) {
        lblMachineID.setText("Company Name");
        txtFieldMachineID.setPromptText("Company Name");
        radioOutsourced.setSelected(true);
        radioInHouse.setSelected(false);
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
        partID = inven.getPartID();
        txtFieldID.setText("" + partID);
    }
}
