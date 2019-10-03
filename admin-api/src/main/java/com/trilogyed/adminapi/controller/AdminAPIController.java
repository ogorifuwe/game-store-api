package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.model.*;
import com.trilogyed.adminapi.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RefreshScope
public class AdminAPIController {

  @Autowired
  CustomerServiceClient customerService;
  @Autowired
  ProductsServiceClient productsService;
  @Autowired
  InventoryServiceClient inventoryService;
  @Autowired
  InvoiceServiceClient invoiceService;
  @Autowired
  LevelUpServiceClient levelUpService;

  /*-----------------------------*
   * End Point: Customer Service *
   *-----------------------------*/
  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Customer createCustomer(@RequestBody @Valid Customer customer, Principal principal) {
    return customerService.createCustomer(customer);
  }

  @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Customer getCustomer(@PathVariable(value = "id") int customerId, Principal principal) {
    return customerService.getCustomer(customerId);
  }

  @RequestMapping(value = "/customers/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Customer> getAllCustomers(Principal principal) {
    return customerService.getAllCustomers();
  }

  @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateCustomer(@PathVariable(value = "id") int customerId,
                             @RequestBody @Valid Customer customer, Principal principal) {
    customerService.updateCustomer(customerId, customer);
  }

  @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable(value = "id") int customerId, Principal principal) {
    customerService.deleteCustomer(customerId);
  }

  /*-----------------------------*
   * End Points: Product Service *
   *-----------------------------*/
  @RequestMapping(value = "/products", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Product createProduct(@RequestBody @Valid Product product, Principal principal) {
    return productsService.createProduct(product);
  }

  @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Product getProductById(@PathVariable(value = "id") int productId, Principal principal) {
    return productsService.getProduct(productId);
  }

  @RequestMapping(value = "/products/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Product> getAllProducts(Principal principal) {
    return productsService.getAllProducts();
  }

  @RequestMapping(value = "/products/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateProduct(@PathVariable("id") int productId, @RequestBody @Valid Product product, Principal principal) {
    productsService.updateProduct(productId, product);
  }

  @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable(value = "id") int productId, Principal principal) {
    productsService.deleteProduct(productId);
  }

  /*-------------------------------*
   * End Points: Inventory Service *
   *-------------------------------*/
  @RequestMapping(value = "/inventory", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Inventory createInventory(@RequestBody @Valid Inventory inventory, Principal principal) {
    return inventoryService.createInventory(inventory);
  }

  @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Inventory getInventory(@PathVariable(value = "id") int inventoryId, Principal principal) {
    return inventoryService.getInventory(inventoryId);
  }

  @RequestMapping(value = "inventory/product/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Inventory getInventoryByProductId(@PathVariable(value = "id") int productId, Principal principal) {
    return inventoryService.getInventoryByProductId(productId);
  }

  @RequestMapping(value = "/inventory/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Inventory> getAllInventory(Principal principal) {
    return inventoryService.getAllInventory();
  }

  @RequestMapping(value = "/inventory/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateInventory(@PathVariable(value = "id") int inventoryId,
                              @RequestBody @Valid Inventory inventory) {
    inventoryService.updateInventory(inventoryId, inventory);
  }

  @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteInventory(@PathVariable(value = "id") int inventoryId, Principal principal) {
    inventoryService.deleteInventory(inventoryId);
  }

  /*----------------------------*
   * End Point: Invoice Service *
   *----------------------------*/
  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Invoice createInvoice(@RequestBody @Valid Invoice invoice, Principal principal) {
    return invoiceService.createInvoice(invoice);
  }

  @RequestMapping(value = "invoices/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Invoice getInvoice(@PathVariable(value = "id") int invoiceId, Principal principal) {
    return invoiceService.getInvoice(invoiceId);
  }

  @RequestMapping(value = "invoices/customer/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Invoice> getInvoicesByCustomer(@PathVariable(value = "id") int customerId, Principal principal) {
    return invoiceService.getInvoicesByCustomer(customerId);
  }

  @RequestMapping(value = "/invoices/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Invoice> getAllInvoices(Principal principal) {
    return invoiceService.getAllInvoices();
  }

  @RequestMapping(value = "/invoices/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateInvoice(@PathVariable(value = "id") int invoiceId,
                            @RequestBody @Valid Invoice invoice,
                            Principal principal) {
    invoiceService.updateInvoice(invoiceId, invoice);
  }

  @RequestMapping(value = "/invoices/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteInvoice(@PathVariable(value = "id") int invoiceId, Principal principal) {
    invoiceService.deleteInvoice(invoiceId);
  }

  /*-----------------------------*
   * End Point: Level Up Service *
   *-----------------------------*/
  @RequestMapping(value = "/levelups", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public LevelUp createLevelUp(@RequestBody LevelUp levelUp, Principal principal) {
    return levelUpService.createLevelUp(levelUp);
  }

  @RequestMapping(value = "levelups/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public LevelUp getLevelUp(@PathVariable(value = "id") int levelUpId, Principal principal) {
    return levelUpService.getLevelUp(levelUpId);
  }

  @RequestMapping(value = "/levelups/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<LevelUp> getAllLevelUps(Principal principal) {
    return levelUpService.getAllLevelUps();
  }

  @RequestMapping(value = "/levelups/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateLevelUp(@PathVariable(value = "id") int levelUpId,
                            @RequestBody @Valid LevelUp levelUp,
                            Principal principal) {
    levelUpService.updateLevelUp(levelUpId, levelUp);
  }

  @RequestMapping(value = "/levelups/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteLevelUp(@PathVariable(value = "id") int levelUpId, Principal principal) {
    levelUpService.deleteLevelUp(levelUpId);
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public String logout() {
    return "Thanks for your time, see you soon!";
  }
}