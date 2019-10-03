package com.trilogyed.customerservice.dao;

import com.trilogyed.customerservice.model.Customer;

import java.util.List;

public interface CustomerDao {

  Customer createCustomer(Customer customer);

  Customer getCustomer(int customerId);

  List<Customer> getAllCustomers();

  void updateCustomer(Customer customer);

  void deleteCustomer(int customerId);
}
