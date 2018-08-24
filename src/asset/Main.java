/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset;

import javafx.scene.control.cell.TextFieldTableCell;
import sqlasset.assetDAO;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sqlasset.row;

/**
 *
 * @author Shawn S Zhang
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

    private void refresh(){
        ObservableList<row>t = model.getTable();
        int size = t.size();
        row last = t.get(size - 1);
        if(!last.emptyRow()){
            int newId = t.size() + 1;
            t.add(new row(String.valueOf(newId), null, null, null, null));
            model.insertNewLine(newId);
        }else if(last.emptyRow() && t.get(size - 2).emptyRow()){
            t.remove(size - 1);
            model.deleteEmpty(size);
        }
        table.setItems(t);
    }



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


        tc_item.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_item.setOnEditCommit((TableColumn.CellEditEvent<row, String> t) -> {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setItem(String.valueOf(t.getNewValue()));
            refresh();
        });

        tc_des.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_des .setOnEditCommit((TableColumn.CellEditEvent<row, String> t) -> {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDes(t.getNewValue());
            refresh();
        });


        tc_formula.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_formula.setOnEditCommit((TableColumn.CellEditEvent<row, String> t) -> {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setForm(t.getNewValue());
            refresh();
        });

        tc_amount.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_amount.setOnEditCommit((TableColumn.CellEditEvent<row, String> t) -> {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
            refresh();
        }
        );

        ObservableList<row>t = model.getTable();

        table.setItems(t);

    }




    
}
