package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

  InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);

  InvoiceItem getInvoiceItem(int invoiceItemId);

  List<InvoiceItem> getInvoiceItemsByInvoiceId(int invoiceId);

  List<InvoiceItem> getAllInvoiceItems();

  void updateInvoiceItem(InvoiceItem invoiceItem);

  void deleteInvoiceItem(int invoiceItemId);

}
