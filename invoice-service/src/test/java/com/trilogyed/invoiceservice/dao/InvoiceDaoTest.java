package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

  @Autowired
  private InvoiceDao invoiceDao;
  @Autowired
  private InvoiceItemDao invoiceItemDao;

  @Before
  public void setUp() throws Exception {

    List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();
    invoiceItems
            .stream()
            .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

    List<Invoice> invoices = invoiceDao.getAllInvoices();
    invoices
            .stream()
            .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));

  }

  @After
  public void tearDown() throws Exception {

    invoiceItemDao = null;
    invoiceDao = null;
  }

  @Test
  public void createGetInvoice() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));

    Invoice newInvoice = invoiceDao.createInvoice(invoice);
    Invoice testInvoice = invoiceDao.getInvoice(newInvoice.getInvoiceId());

    assertNotNull(testInvoice);
    assertEquals(newInvoice, testInvoice);

  }

  @Test
  public void getAllInvoices() {

    Invoice invoice = new Invoice();
    invoice.setInvoiceId(1);
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    Invoice invoice2 = new Invoice();
    invoice2.setInvoiceId(2);
    invoice2.setCustomerId(2);
    invoice2.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice2 = invoiceDao.createInvoice(invoice2);

    List<Invoice> invoices = invoiceDao.getAllInvoices();
    assertNotNull(invoices);
    assertEquals(2, invoices.size());

  }

  @Test
  public void getInvoicesByCustomerId() {

    Invoice invoice = new Invoice();
    invoice.setInvoiceId(1);
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    Invoice invoice2 = new Invoice();
    invoice2.setInvoiceId(2);
    invoice2.setCustomerId(2);
    invoice2.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice2 = invoiceDao.createInvoice(invoice2);

    List<Invoice> invoices = invoiceDao.getInvoicesByCustomerId(1);
    assertNotNull(invoices);
    assertEquals(1, invoices.size());

  }

  @Test
  public void updateInvoice() {

    Invoice invoice = new Invoice();
    invoice.setInvoiceId(1);
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    newInvoice.setPurchaseDate(LocalDate.of(2019, 9, 11));
    invoiceDao.updateInvoice(newInvoice);

    Invoice testInvoice = invoiceDao.getInvoice(newInvoice.getInvoiceId());
    assertNotNull(testInvoice);
    assertEquals(newInvoice, testInvoice);

  }

  @Test
  public void deleteInvoice() {

    Invoice invoice = new Invoice();
    invoice.setInvoiceId(1);
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    invoiceDao.deleteInvoice(newInvoice.getInvoiceId());
    Invoice testInvoice = invoiceDao.getInvoice(newInvoice.getInvoiceId());
    assertNull(testInvoice);

  }

}
