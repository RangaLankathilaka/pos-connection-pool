/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ranjith-suranga
 */
@Component
@Transactional
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public ItemBOImpl() {

    }

    public ArrayList<ItemDTO> getAllItems() throws Exception {

        try  {
            List<Item> allItems = itemDAO.getAll();
            ArrayList<ItemDTO> dtos = new ArrayList<>();
            for (Item item : allItems) {
                ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
                dtos.add(itemDTO);
            }
            return dtos;
        } catch (HibernateException exp) {
            return null;
        }

    }

    public boolean saveItem(ItemDTO dto) throws Exception {

        try {
            Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
            itemDAO.save(item);
            return true;
        } catch (HibernateException exp) {
            return false;
        }

    }

    public boolean deleteItem(String itemCode) throws Exception {
        try  {
            itemDAO.delete(itemCode);
            return true;
        } catch (HibernateException exp) {
            return false;
        }
    }

}
