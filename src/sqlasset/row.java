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

/**
 *
 * @author user
 */
public class row {
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
        if(r_form==null){
            this.rAmount = new SimpleStringProperty(rAmount);
        }else{
            Tree t = new Tree(r_form);
            double no = t.evaulate();
            this.rAmount = new SimpleStringProperty(String.valueOf(no));
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
    
    public void setId(String id){this.rID.set(id);}
    
    public void setItem(String item){this.rItem.set(item);}
    
    public void setDes(String des){this.rDes.set(des);}
    
    public void setForm(String form){this.rForm.set(form);}
    
    public void setAmount(String amount){this.rAmount.set(amount);}


}
