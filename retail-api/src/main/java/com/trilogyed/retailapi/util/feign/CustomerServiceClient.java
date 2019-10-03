package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  Customer createCustomer(@RequestBody Customer customer);

  @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  Customer getCustomer(@PathVariable(value = "id") int customerId);

  @RequestMapping(value = "/customers/all-customers", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<Customer> getAllCustomers();

  @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void updateCustomer(@PathVariable(value = "id") int customerId, @RequestBody Customer customer);

  @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void deleteCustomer(@PathVariable(value = "id") int customerId);
}
