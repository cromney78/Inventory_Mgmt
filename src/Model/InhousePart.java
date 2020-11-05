/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Chad Romney
 */
public class InhousePart extends Part {
    
    private final IntegerProperty machineID;
    
    public InhousePart() {
        super();
        machineID = new SimpleIntegerProperty();
    }
    
    public IntegerProperty getMachineIDProperty() {
        return machineID;
    }
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return machineID.get();
    }
          
}

