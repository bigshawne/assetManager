/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlasset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import expressionTree.amount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.xml.transform.Result;

/**
 *
 * @author user
 */
public class assetDAO {
    private Connection connection;
    private Statement stmt;
    
    public assetDAO(){
        
    }
    
    private String desString = "INSERT INTO asset (description) VALUES (?);";
    PreparedStatement des;
    public void insertDes(String d){
        connect();
        try{
            des.setString(1, d);
            des.executeUpdate();
        }catch (SQLException ex) {
            System.out.print("Error in insert description");
            ex.printStackTrace();
        }
    }
    
    public ObservableList<Integer> getIds(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        String sql = "SELECT id FROM asset";
        try{
            ResultSet r = stmt.executeQuery(sql);
            while(r.next())
                ids.add(r.getInt(1));
        }catch (SQLException ex) {
            showMessageDialog("Could not get all id: " + ex.getMessage());
        }
        return ids;
    }


    public amount getAmount(int id){
        amount a = null;
        String sql = "SELECT id, formula, amount FROM asset where id = ";
        String whereclause = sql + id;
        try{
            ResultSet r = stmt.executeQuery(whereclause);
            r.next();
            cell form = new cell(r.getString(2));
            cell amount = new cell(r.getDouble(3));
            if(form.toString() == null || form.toString() == "")
                a = new amount(amount.getNo());
            else
                a = new amount(form.toString());
        }catch(SQLException ex){
            showMessageDialog("Could not get the amount: " + ex.getMessage());
            ex.printStackTrace();
        }
        return a;
    }

    public ObservableList<row> getTable(){
        ObservableList<row> table = FXCollections.observableArrayList();
        ObservableList<Integer> ids = getIds();
        for(Integer i: ids){
            String sql = "SELECT id, item, description, formula, amount FROM asset WHERE id = " + i;
            try{
                ResultSet r = stmt.executeQuery(sql);
                r.next();
                cell id = new cell(r.getInt(1));
                cell item = new cell(r.getString(2));
                cell des = new cell(r.getString(3));
                cell form = new cell(r.getString(4));
                cell amount = new cell(r.getDouble(5));
                row row =  new row(id.toString(), item.toString(), des.toString(), form.toString(),amount.toString());
                table.add(row);
            }catch(SQLException ex){
                showMessageDialog("Could not get all id: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return table;
    }

    private String newDes = "UPDATE asset SET description = (?) WHERE id = (?)";
    PreparedStatement nPDes;
    public void updateDes(String str, int id){
        connect();
        try{
            nPDes.setString(1, str);
            nPDes.setInt(2, id);
            nPDes.executeUpdate();
        }catch (SQLException ex) {
            showMessageDialog("Could update description: " + ex.getMessage());
        }
    }

    private String newItem = "UPDATE asset SET item = (?) WHERE id = (?)";
    PreparedStatement nPItem;
    public void updateItem(String str, int id){
        connect();
        try{
            nPItem.setString(1, str);
            nPItem.setInt(2, id);
            nPItem.executeUpdate();
        }catch (SQLException ex) {
            showMessageDialog("Could update item: " + ex.getMessage());
        }
    }

    private String newForm = "UPDATE asset SET formula = (?) WHERE id = (?)";
    PreparedStatement nPForm;
    public void updateForm(String str, int id){
        connect();
        try{
            nPForm.setString(1, str);
            nPForm.setInt(2, id);
            nPForm.executeUpdate();
        }catch (SQLException ex) {
            showMessageDialog("Could update formula: " + ex.getMessage());
        }
    }

    private String newAmount = "UPDATE asset SET amount = (?) WHERE id = (?)";
    PreparedStatement nPAmount;
    public void updateAmount(String str, int id){
        connect();
        try{
            nPAmount.setDouble(1, Double.parseDouble(str));
            nPAmount.setInt(2, id);
            nPAmount.executeUpdate();
        }catch (SQLException ex) {
            showMessageDialog("Could update amount: " + ex.getMessage());
        }
    }

    public void connect() {
       try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
       } catch (Exception ex) {
           showMessageDialog("Unable to load JDBC driver. Application will exit.");
           System.exit(0);
       }

       // Establish a connection
       try {
           String url = "jdbc:mysql://localhost:3306/asset?user=root&password=cmsc250";
           connection = DriverManager.getConnection(url);
           stmt = connection.createStatement();
           des = connection.prepareStatement(desString);
           nPDes = connection.prepareStatement(newDes);
           nPItem = connection.prepareStatement(newItem);
           nPForm = connection.prepareStatement(newForm);
           nPAmount = connection.prepareStatement(newAmount);
       } catch (SQLException ex) {
           showMessageDialog("Unable to connect to database. Application will exit.");
           System.exit(0);
       }
   }

    private void showMessageDialog(String message) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error");
       alert.showAndWait();
   }
}
