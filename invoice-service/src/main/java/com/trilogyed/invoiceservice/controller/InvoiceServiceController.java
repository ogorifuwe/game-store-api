package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.dao.InvoiceItemDao;
import com.trilogyed.invoiceservice.exception.NotFoundException;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class InvoiceServiceController {

  @Autowired
  private InvoiceService service;

  @Autowired
  private InvoiceItemDao invoiceItemDao;

  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {

    Invoice newInvoice = service.createInvoice(invoice);
    return newInvoice;
  }

  @RequestMapping(value = "/invoices/invoice-item", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {

    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
    return newInvoiceItem;
  }

  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Invoice getInvoice(@PathVariable(value = "id") int invoiceId) {

    Invoice invoice = service.getInvoiceById(invoiceId);
    if (invoice == null) {
      throw new NotFoundException("No Invoice matching the given Invoice Id was found. ID: "+invoiceId);
    }

    return invoice;
  }

  @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Invoice> getInvoicesByCustomer(@PathVariable(value = "id") int customerId) {
    List<Invoice> invoices = service.getInvoiceByCustomerId(customerId);
    if (invoices != null && invoices.size() == 0) {
      throw new NotFoundException("No Invoice matching given customer Id was found in the System.");
    }

    return invoices;
  }

  @RequestMapping(value = "/invoices/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Invoice> getAllInvoices() {

    List<Invoice> invoices = service.getAllInvoices();
    if (invoices != null && invoices.size() == 0) {
      throw new NotFoundException("No Invoice was found in the System.");
    }

    return invoices;
  }

  @RequestMapping(value = "/invoices/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateInvoice(@PathVariable(value = "id") int invoiceId,
                            @RequestBody @Valid Invoice invoice) {

    if (!(invoiceId == invoice.getInvoiceId())) {
      throw new IllegalArgumentException("Id on path must be the same as Id of Invoice to be updated.");
    }

    service.updateInvoice(invoice);
  }

  @RequestMapping(value = "/invoices/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteInvoice(@PathVariable(value = "id") int invoiceId) {

    service.deleteInvoice(invoiceId);
  }
}
