/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import lk.ijse.pos.dao.CrudDAOImpl;
import lk.ijse.pos.dao.custom.ItemDetailDAO;
import lk.ijse.pos.entity.ItemDetail;
import lk.ijse.pos.entity.ItemDetail_PK;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 *
 * @author ranjith-suranga
 */
@Component
public class ItemDetailDAOImpl extends CrudDAOImpl<ItemDetail, ItemDetail_PK> implements ItemDetailDAO{

}
