/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
public class ModPartController implements Initializable {

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
    
             private Part part;
             private int partID;
             private String name = "";
             private String inventory = "";
             private String price = "";
             private String max = "";
             private String min = "";
             private String machineCompany = "";
             private boolean inhouse;
             private int index;
    
    
    public void setPart(int index, Part part){
        this.index = index;
        this.part = part;
        
        System.out.println("setPart inhouse = " + inhouse);
        
        partID = part.getPartID();
        name = part.getName();
        inventory = Integer.toString(part.getInStock());
        price = Double.toString(part.getPrice());
        max = Integer.toString(part.getMax());
        min = Integer.toString(part.getMin()); 
        
        if(part instanceof InhousePart) {
            machineCompany = Integer.toString(((InhousePart)part).getMachineID());
            radioInHouse.setSelected(true);
            radioOutsourced.setSelected(false);
            inhouse = true;
           
            System.out.println("setPart inhouse");
        }   
        else if (part instanceof OutsourcedPart) {
            machineCompany = ((OutsourcedPart)part).getCompanyName();
            radioOutsourced.setSelected(true);
            radioInHouse.setSelected(false);
            inhouse = false;
            
            System.out.println("setPart outsourced"); 
        }
            txtFieldID.setText(Integer.toString(partID));
            txtFieldName.setText(name);
            txtFieldInv.setText(inventory);
            txtFieldPrice.setText(price);
            txtFieldMax.setText(max);
            txtFieldMin.setText(min);
            txtFieldMachineID.setText(machineCompany);
    }
    
    @FXML
    void btnAddPartSave(ActionEvent event) throws Exception {
        
        //index needed to update part rather than add new
        //index = Inventory.getPartsList().indexOf(part); 
        Inventory inven = new Inventory();
        String err = "";
        
        if (name.equals("")){err = ("Name cannot be blank\n");}
        if (Integer.parseInt(inventory) < 0){err = err + ("Inventory must be greater than 0.\n");}
        if (Double.parseDouble(price) < 0){err = err + ("Price must be greater than 0.\n");}
        if (Integer.parseInt(max) < Integer.parseInt(min)){err = err + ("Maximum must be more than minimum.\n");}
        if (Integer.parseInt(inventory) < Integer.parseInt(min)){err = err + ("Inventory must be greater than Minimum.\n");}
        if (Integer.parseInt(inventory) > Integer.parseInt(max)){err = err + ("Inventory must be less than Maximum.\n");}
        if (machineCompany.equals("")){err = err + ("Machine/Company field cannot be blank.");}

        if(err.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error adding part");
            alert.setHeaderText("Cannot add part");
            alert.setContentText(err); //display the error mssg
            alert.showAndWait();
        }    
        else {
            if(part instanceof InhousePart){
                InhousePart iPart = new InhousePart();
                System.out.println("modpart SavePart inhouse");
                iPart.setPartID(partID);
                iPart.setName(txtFieldName.getText());
                iPart.setInStock(Integer.parseInt(txtFieldInv.getText()));
                iPart.setPrice(Double.parseDouble(txtFieldPrice.getText()));
                iPart.setMax(Integer.parseInt(txtFieldMax.getText()));
                iPart.setMin(Integer.parseInt(txtFieldMin.getText()));
                iPart.setMachineID(Integer.parseInt(txtFieldMachineID.getText()));
                inven.updatePart(index, iPart);
                }
                else {
                    OutsourcedPart oPart = new OutsourcedPart();
                    System.out.println("modpart SavePart = outsourced");
                    oPart.setPartID(partID);
                    oPart.setName(txtFieldName.getText());
                    oPart.setInStock(Integer.parseInt(txtFieldInv.getText()));
                    oPart.setPrice(Double.parseDouble(txtFieldPrice.getText()));
                    oPart.setMax(Integer.parseInt(txtFieldMax.getText()));
                    oPart.setMin(Integer.parseInt(txtFieldMin.getText()));
                    oPart.setCompanyName(txtFieldMachineID.getText());
                    inven.updatePart(index, oPart);
                }
            returnToMain(event);
        }
    }

    @FXML
    void btnCancelPushed(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            returnToMain(event);
        }
    }

    @FXML
    void toggleInHousePart(ActionEvent event) {
        lblMachineID.setText("Machine ID");
        inhouse = true;
    }

    @FXML
    void toggleOutsourcedPart(ActionEvent event) {
        lblMachineID.setText("Company Name");
        inhouse = false;
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
        // TODO
    }    
    
}
