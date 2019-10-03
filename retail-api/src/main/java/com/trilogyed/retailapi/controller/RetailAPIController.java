package com.trilogyed.retailapi.controller;

import com.trilogyed.retailapi.service.RetailAPIService;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import com.trilogyed.retailapi.viewmodel.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class RetailAPIController {

  @Autowired
  private RetailAPIService retailAPIService;

  @RequestMapping(value = "/purchases", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public InvoiceViewModel createOrder(@RequestBody Order order) {

    InvoiceViewModel receipt = retailAPIService.createOrder(order);
    if (receipt == null) {
      throw new IllegalArgumentException("There was a problem processing your transaction");
    }
    return receipt;
  }
}
