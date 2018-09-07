/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.pos.dao.CrudDAOImpl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 *
 * @author ranjith-suranga
 */
@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {


}
