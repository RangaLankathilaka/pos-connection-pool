/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.business.custom.impl.CustomerBOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.main.AppInitializer;
import lk.ijse.pos.view.util.tblmodel.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ranjith-suranga
 */

public class ManageCustomerFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerAddress;
    
    @FXML
    private TableView<CustomerTM> tblCustomers;

    private CustomerBO customerBO;

    public ManageCustomerFormController(){
        customerBO = AppInitializer.ctx.getBean(CustomerBO.class);
    }
    
    private void loadAllCustomers(){
        try {

            System.out.println("TEsst : " + customerBO);
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            
            ObservableList<CustomerTM> olCustomers = tblCustomers.getItems();
            olCustomers.removeAll(olCustomers);
            
            for (CustomerDTO customer : allCustomers) {
                CustomerTM customerTM = new CustomerTM(customer.getId(), customer.getName(), customer.getAddress());
                olCustomers.add(customerTM);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCustomers.getColumns().get(0).setStyle("-fx-alignment:center");
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        
        loadAllCustomers();
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        
        String customerID = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        
        CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, customerAddress);
        
        try {
            boolean result = customerBO.saveCustomer(customerDTO);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer has been saved successfully", ButtonType.OK).show();
                txtCustomerId.setText("");
                txtCustomerName.setText("");
                txtCustomerAddress.setText("");
                loadAllCustomers();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        
        String customerID = tblCustomers.getSelectionModel().getSelectedItem().getId();
        
        try {
            boolean result = customerBO.deleteCustomer(customerID);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer has been deleted successfully", ButtonType.OK).show();
                loadAllCustomers();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete the customer", ButtonType.OK).show();
            }            
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
