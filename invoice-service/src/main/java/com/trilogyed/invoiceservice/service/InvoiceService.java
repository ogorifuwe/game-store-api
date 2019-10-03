package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceDao;
import com.trilogyed.invoiceservice.dao.InvoiceItemDao;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class InvoiceService {

  @Autowired
  private InvoiceDao invoiceDao;
  @Autowired
  private InvoiceItemDao invoiceItemDao;

  @Transactional
  public Invoice createInvoice(Invoice invoice) {

    return buildInvoice(invoice);

  }

  public Invoice getInvoiceById(int invoiceId) {

    Invoice invoice = invoiceDao.getInvoice(invoiceId);
    if (invoice == null) return null;
    List<InvoiceItem> utilInvoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceId);
    invoice.setInvoiceItems(utilInvoiceItems);
    return invoice;
  }

  public List<Invoice> getInvoiceByCustomerId(int customerId) {

    List<Invoice> invoices = invoiceDao.getInvoicesByCustomerId(customerId);

    for (Invoice invoice: invoices) {

      List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
      invoice.setInvoiceItems(invoiceItems);
    }
    return invoices;

  }

  public List<Invoice> getAllInvoices() {

    List<Invoice> utilInvoices = invoiceDao.getAllInvoices();

    for (Invoice utilInvoice: utilInvoices) {

      List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(utilInvoice.getInvoiceId());
      utilInvoice.setInvoiceItems(invoiceItems);
    }

    return utilInvoices;
  }

  public void updateInvoice(Invoice invoice) {

    invoiceDao.updateInvoice(invoice);
    List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();

    for (InvoiceItem invoiceItem: invoiceItems) {
      invoiceItem.setInvoiceId(invoice.getInvoiceId());
      invoiceItem.setInventoryId(invoiceItem.getInventoryId());
      invoiceItem.setQuantity(invoiceItem.getQuantity());
      invoiceItem.setInvoiceItemId(invoiceItem.getInvoiceItemId());
      invoiceItemDao.updateInvoiceItem(invoiceItem);
    }
  }

  public void deleteInvoice(int invoiceId) {

    Invoice invoice = invoiceDao.getInvoice(invoiceId);
    List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceId);
    invoiceItems
            .stream()
            .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

    invoiceDao.deleteInvoice(invoiceId);
  }

  private Invoice buildInvoice(Invoice invoice) {

    Invoice utilInvoice = new Invoice();
    utilInvoice.setCustomerId(invoice.getCustomerId());
    utilInvoice.setPurchaseDate(invoice.getPurchaseDate());
    Invoice newInvoice = invoiceDao.createInvoice(utilInvoice);

    System.out.println(newInvoice.toString());

    int invoiceId = newInvoice.getInvoiceId();
    List<InvoiceItem> utilInvoiceItems = invoice.getInvoiceItems();

    if (utilInvoiceItems != null && utilInvoiceItems.size() > 0) {
      for (InvoiceItem invoiceItem : utilInvoiceItems) {
        invoiceItem.setInvoiceId(invoiceId);
        InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
      }
    }

    newInvoice.setInvoiceItems(utilInvoiceItems);
    return newInvoice;

  }

}
