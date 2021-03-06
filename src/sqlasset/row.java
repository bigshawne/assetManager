/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlasset;

//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import expressionTree.Tree;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class row {
    private assetDAO model = new assetDAO();
    private final SimpleStringProperty rID;
    private final SimpleStringProperty rItem;
    private final SimpleStringProperty rDes;
    private final SimpleStringProperty rForm;
    private final SimpleStringProperty rAmount;
    
    public row(String rID, String rItem, String rDes, String r_form, String rAmount){
        this.rID = new SimpleStringProperty(rID);
        this.rItem = new SimpleStringProperty(rItem);
        this.rDes = new SimpleStringProperty(rDes);
        this.rForm = new SimpleStringProperty(r_form);
        if(r_form==null || r_form.equals("")){
            this.rAmount = new SimpleStringProperty(rAmount);
        }else{
            Tree t = new Tree(r_form);
            double no = t.evaulate();
            this.rAmount = new SimpleStringProperty(String.valueOf(no));
            model.updateAmount(String.valueOf(no), Integer.parseInt(this.rID.getValue()));
        }

    }
    
    public String getId(){return this.rID.get();}
        public StringProperty rIdProperty(){return this.rID;}
    
    public String getItem(){return this.rItem.get();}
    public StringProperty rItemProperty(){return this.rItem;}

    public String getDes(){return this.rDes.get();}
    public StringProperty rDesProperty(){return this.rDes;}
    
    public String getForm(){return this.rForm.get();}
    public StringProperty rFormProperty(){return this.rForm;}
    
    public String getAmount(){return this.rAmount.get();}
    public StringProperty rAmountProperty(){return this.rAmount;}
    
    public void setId(String id){
        this.rID.set(id);
    }
    
    public void setItem(String item){
        this.rItem.set(item);
        int id = Integer.parseInt(rID.getValue());
        model.updateItem(item, id);
    }

    public void setDes(String des){
        this.rDes.set(des);
        model.updateDes(des, Integer.parseInt(rID.getValue()));
    }
    
    public void setForm(String form){
        this.rForm.set(form);
        model.updateForm(form, Integer.parseInt(rID.getValue()));
    }
    
    public void setAmount(String amount){
        this.rAmount.set(amount);
        model.updateAmount(amount, Integer.parseInt(rID.getValue()));
    }

    public boolean emptyRow(){
        return (rItem.getValue() == null) && (rDes.getValue() == null) && (rForm.getValue() == null) && (rAmount.getValue() == "");

    }

    public void resetAmount(){
        this.rAmount.setValue("");
    }

}
