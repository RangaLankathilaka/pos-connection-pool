/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ranjith-suranga
 */
public class OrderDTO {
    
    private String id;
    private Date date;
    private String customerId;
    
    private ArrayList<ItemDetailDTO> itemDetails;

    public OrderDTO() {
    }

    public OrderDTO(String id, Date date, String customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public OrderDTO(String id, Date date, String customerId, ArrayList<ItemDetailDTO> itemDetails) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.itemDetails = itemDetails;
    }    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the itemDetails
     */
    public ArrayList<ItemDetailDTO> getItemDetails() {
        return itemDetails;
    }

    /**
     * @param itemDetails the itemDetails to set
     */
    public void setItemDetails(ArrayList<ItemDetailDTO> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "id=" + id + ", date=" + date + ", customerId=" + customerId + ", itemDetails=" + itemDetails + '}';
    }

    
}
