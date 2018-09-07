/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ranjith-suranga
 */

@Component
@Transactional
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
    }

    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        try{
            Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress());
            customerDAO.save(customer);
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {

        try  {
            List<Customer> allCustomers = customerDAO.getAll();

            ArrayList<CustomerDTO> dtos = new ArrayList<>();
            for (Customer customer : allCustomers) {
                CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
                dtos.add(customerDTO);
            }
            return dtos;
        }catch (HibernateException exp){
            exp.printStackTrace();
            return null;
        }
    }

    public boolean deleteCustomer(String customerId) throws Exception {
        try  {
            customerDAO.delete(customerId);
            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

}
