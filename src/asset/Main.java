/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset;

import javafx.scene.control.cell.TextFieldTableCell;
import sqlasset.assetDAO;
import sqlasset.cell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sqlasset.row;

/**
 *
 * @author user
 */
public class Main implements Initializable {
    private assetDAO model;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn<row, String> tc_id;
    
    @FXML
    private TableColumn<row, String> tc_item;
    
    @FXML
    private TableColumn<row, String> tc_des;
    
    @FXML
    private TableColumn<row, String> tc_formula;
    
    @FXML
    private TableColumn<row, String> tc_amount;
    
        
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new assetDAO();
        model.connect();

        //Setup contents for each columns
        tc_id.setCellValueFactory(cellData -> cellData.getValue().rIdProperty());
        tc_item.setCellValueFactory(cellData->cellData.getValue().rItemProperty());
        tc_des.setCellValueFactory(cellData->cellData.getValue().rDesProperty());
        tc_formula.setCellValueFactory(cellData->cellData.getValue().rFormProperty());
        tc_amount.setCellValueFactory(cellData->cellData.getValue().rAmountProperty());

        //Make each cell editable
        table.setEditable(true);
        tc_id.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_item.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_des.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_formula.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_amount.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<row>t = model.getTable();
        table.setItems(t);
    }    
    
}
