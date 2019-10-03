package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

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
    invoiceDao = null;
    invoiceItemDao = null;
  }

  @Test
  public void createGetIvoiceItem() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(newInvoice.getInvoiceId());
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

    InvoiceItem testInvoiceItem = invoiceItemDao.getInvoiceItem(newInvoiceItem.getInvoiceItemId());
    assertNotNull(testInvoiceItem);
    assertEquals(newInvoiceItem, testInvoiceItem);

  }

  @Test(expected = DataIntegrityViolationException.class)
  public void addWithRefIntegrityException() {

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(1);
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

  }


  @Test
  public void getInvoiceItemsByInvoiceId() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(invoice.getInvoiceId());
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

    InvoiceItem invoiceItem2 = new InvoiceItem();
    invoiceItem2.setInvoiceId(invoice.getInvoiceId());
    invoiceItem2.setInventoryId(1);
    invoiceItem2.setQuantity(10);
    invoiceItem2.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem2 = invoiceItemDao.createInvoiceItem(invoiceItem2);

    int invoiceId = newInvoice.getInvoiceId();
    List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceId);

    assertNotNull(invoiceItems);
    assertTrue(invoiceItems.size() == 2);

  }

  @Test
  public void getAllInvoiceItems() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(invoice.getInvoiceId());
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

    InvoiceItem invoiceItem2 = new InvoiceItem();
    invoiceItem2.setInvoiceId(invoice.getInvoiceId());
    invoiceItem2.setInventoryId(1);
    invoiceItem2.setQuantity(10);
    invoiceItem2.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem2 = invoiceItemDao.createInvoiceItem(invoiceItem2);

    List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();
    assertNotNull(invoiceItems);
    assertEquals(2, invoiceItems.size());

  }

  @Test
  public void updateInvoiceItem() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(invoice.getInvoiceId());
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

    invoiceItem.setInventoryId(2);
    invoiceItem.setQuantity(20);
    invoiceItemDao.updateInvoiceItem(newInvoiceItem);

    InvoiceItem testInvoiceItem = invoiceItemDao.getInvoiceItem(newInvoiceItem.getInvoiceItemId());
    assertNotNull(testInvoiceItem);
    assertEquals(newInvoiceItem, testInvoiceItem);

  }

  @Test
  public void deleteInvoiceItem() {

    Invoice invoice = new Invoice();
    invoice.setCustomerId(1);
    invoice.setPurchaseDate(LocalDate.of(2019, 9, 12));
    Invoice newInvoice = invoiceDao.createInvoice(invoice);

    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setInvoiceId(invoice.getInvoiceId());
    invoiceItem.setInventoryId(1);
    invoiceItem.setQuantity(10);
    invoiceItem.setUnitPrice(new BigDecimal(10.99));
    InvoiceItem newInvoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

    invoiceItemDao.deleteInvoiceItem(newInvoiceItem.getInvoiceItemId());
    InvoiceItem testInvoiceItem = invoiceItemDao.getInvoiceItem(newInvoiceItem.getInvoiceItemId());
    assertNull(testInvoiceItem);

  }


}
