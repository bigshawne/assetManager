/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlasset;

//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleIntegerProperty;
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
    
    public row(String rID, String rItem, String rDes, String rForm, String rAmount){
        this.rID = new SimpleStringProperty(rID);
        this.rItem = new SimpleStringProperty(rItem);
        this.rDes = new SimpleStringProperty(rDes);
        this.rForm = new SimpleStringProperty(rForm);

        if(!rForm.isEmpty()){
            //String rForm = deleteWhite(rForm);
        }
        this.rAmount = new SimpleStringProperty(rAmount);
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
