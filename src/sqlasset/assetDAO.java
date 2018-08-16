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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import parsing.amount;

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
            a = new amount(r.getInt(1),form.toString(), r.getDouble(3));
        }catch(SQLException ex){
            showMessageDialog("Could not get all id: " + ex.getMessage());
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
    
    public void updateDes(String d, int id){
        connect();
        String des = "UPDATE asset SET description =";
        String whereClause = "WHERE is = ";
        try{
            String sql = des + d + whereClause + id;
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.println("Error when update description.");
            ex.printStackTrace();
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
           //newItStmt = connection.prepareStatement(newItSQL);
           //checkoutStmt = connection.prepareStatement(checkoutSQL);
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
