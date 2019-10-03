package com.trilogyed.customerservice.controller;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.exception.NotFoundException;
import com.trilogyed.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class CustomerServiceController {

  @Autowired
  private CustomerDao customerDao;

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Customer createCustomer(@RequestBody Customer customer) {

    Customer newCustomer = customerDao.createCustomer(customer);
    return newCustomer;
  }

  @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Customer getCustomer(@PathVariable(value = "id") int customerId) {

    Customer customer = customerDao.getCustomer(customerId);
    if (customer == null) {
      throw new NotFoundException("No Customer with given Id was found in the system. ID: "+customerId);
    }

    return customer;
  }

  @RequestMapping(value = "/customers/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Customer> getAllCustomers() {

    List<Customer> customers = customerDao.getAllCustomers();
    if (customers != null && customers.size() == 0) {
      throw new NotFoundException("No customers were found in the System.");
    }

    return customers;
  }

  @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateCustomer(@PathVariable(value = "id") int customerId,
                             @RequestBody Customer customer) {

    if (!(customerId == customer.getCustomerId())) {
      throw new IllegalArgumentException("Id on path must be the same as the Id of the Customer to be updated");
    }

    customerDao.updateCustomer(customer);
  }

  @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable(value = "id") int customerId) {

    customerDao.deleteCustomer(customerId);
  }
}
