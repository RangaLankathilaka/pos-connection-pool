/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.business.custom.impl.ItemBOImpl;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.main.AppInitializer;
import lk.ijse.pos.view.util.tblmodel.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ranjith-suranga
 */
public class ManageItemFormController implements Initializable{

    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<ItemTM> tblItems;

    private ItemBO itemBO;
    
    public ManageItemFormController(){
        itemBO = AppInitializer.ctx.getBean(ItemBO.class);
    }
    
    private void loadAllItems(){
        
        try {
            
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            
            ObservableList<ItemTM> olItems = tblItems.getItems();
            olItems.removeAll(olItems);
            
            for (ItemDTO item : allItems) {
                ItemTM itemTM = new ItemTM(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
                olItems.add(itemTM);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tblItems.getColumns().get(0).setStyle("-fx-alignment: center");
        tblItems.getColumns().get(2).setStyle("-fx-alignment: center-right");
        tblItems.getColumns().get(3).setStyle("-fx-alignment: center-right");
        
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        
        loadAllItems();
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        AppInitializer.navigateToHome(root, (Stage) root.getScene().getWindow());
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        
        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQty.getText());
        
        ItemDTO itemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);
        
        try {
            boolean result = itemBO.saveItem(itemDTO);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Item has been saved successfully", ButtonType.OK).show();
                txtItemCode.setText("");
                txtDescription.setText("");
                txtQty.setText("");
                txtUnitPrice.setText("");
                loadAllItems();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer", ButtonType.OK).show();    
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        
        String code = tblItems.getSelectionModel().getSelectedItem().getCode();
        
        try {
            boolean result = itemBO.deleteItem(code);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Item has been deleted successfully", ButtonType.OK).show();
                loadAllItems();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete the customer", ButtonType.OK).show();    
            }            
        } catch (Exception ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
