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
    private int tableLen;
    public assetDAO(){
        
    }

    private String newId = "INSERT INTO asset (id) VALUES (?)";
    PreparedStatement nID;
    public void insertNewLine(int id){
        connect();
        try{
            nID.setInt(1, id);
            nID.executeUpdate();
        }catch (SQLException ex) {
            showMessageDialog("Could not insert new line: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public row getRow(int id){
        String sql = "SELECT item, description, formula, amount FROM asset WHERE id = ";
        String whereclause = sql + id;
        row row = null;
        try{
            ResultSet r = stmt.executeQuery(whereclause);
            r.next();
            cell item = new cell(r.getString(1));
            cell des = new cell(r.getString(2));
            cell form = new cell(r.getString(3));
            cell amount = new cell(r.getDouble(4));
            row = new row(String.valueOf(id), item.toString(), des.toString(), form.toString(), amount.toString());
        }catch(SQLException ex){
            showMessageDialog("Could not fetch the row: " + ex.getMessage());
        }
        return row;
    }

    public void deleteEmpty(int id){
        String sql = "DELETE FROM asset WHERE id = ";
        String whereClause = sql + id;
        try{
            stmt.executeUpdate(whereClause);
        }catch(SQLException ex){
            showMessageDialog("Could not delete the row: " + ex.getMessage());
        }
    }

    
    public ObservableList<Integer> getIds(){
        connect();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        String sql = "SELECT id FROM asset";
        try{
            ResultSet r = stmt.executeQuery(sql);
            while(r.next())
                ids.add(r.getInt(1));
        }catch (SQLException ex) {
            showMessageDialog("Could not get all id: " + ex.getMessage());
        }
        tableLen = ids.size();
        return ids;
    }

    public ObservableList<row> getTable(){
        connect();
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
                cell amount;
                String al = r.getString(5);
                if(al == null){
                    amount = new cell();
                }else{
                    amount = new cell(Double.parseDouble(al));
                }
                row row =  new row(id.toString(), item.toString(), des.toString(), form.toString(),amount.toString());
                table.add(row);
            }catch(SQLException ex){
                showMessageDialog("Could not get all id: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return table;
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

    private String newDes = "UPDATE asset SET description = (?) WHERE id = (?)";
    PreparedStatement nPDes;
    public void updateDes(String str, int id){
        connect();
        try{
            if(str.equals(""))str = null;
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
            if(str.equals(""))str = null;
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
            if(str.equals(""))str = null;
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
            if(str.equals(""))
                nPAmount.setString(1, null);
            else
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
           nPDes = connection.prepareStatement(newDes);
           nPItem = connection.prepareStatement(newItem);
           nPForm = connection.prepareStatement(newForm);
           nPAmount = connection.prepareStatement(newAmount);
           nID = connection.prepareStatement(newId);
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
