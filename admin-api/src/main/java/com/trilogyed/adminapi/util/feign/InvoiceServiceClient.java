package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.Invoice;
import com.trilogyed.adminapi.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceClient {

  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  Invoice createInvoice(@RequestBody @Valid Invoice invoice);

  @RequestMapping(value = "/invoices/invoice-item", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

  @RequestMapping(value = "invoices/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  Invoice getInvoice(@PathVariable(value = "id") int invoiceId);

  @RequestMapping(value = "invoices/customer/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<Invoice> getInvoicesByCustomer(@PathVariable(value = "id") int customerId);

  @RequestMapping(value = "/invoices/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<Invoice> getAllInvoices();

  @RequestMapping(value = "/invoices/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void updateInvoice(@PathVariable(value = "id") int invoiceId,
                     @RequestBody @Valid Invoice invoice);

  @RequestMapping(value = "/invoices/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void deleteInvoice(@PathVariable(value = "id") int invoiceId);

}
