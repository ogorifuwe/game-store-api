package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.model.*;
import com.trilogyed.retailapi.util.feign.*;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import com.trilogyed.retailapi.viewmodel.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Component
public class RetailAPIService {

  @Autowired
  CustomerServiceClient customerService;
  @Autowired
  LevelUpServiceClient levelUpService;
  @Autowired
  InventoryServiceClient inventoryService;
  @Autowired
  InvoiceServiceClient invoiceService;
  @Autowired
  ProductServiceClient productService;

  public InvoiceViewModel createOrder(Order order) {
    /** get customer from order */
    // TODO: change implementation to use already existing
    //  customer who already has a customerId
    Customer customer = order.getCustomer();
    Customer newCustomer = customerService.createCustomer(customer);

    /** get list of products from order */
    List<Product> products = order.getProducts();

    /** get product information from order */
    Map<Integer, Integer> productsMap = getProductInformation(products);

    /** check that there's enough product quantity in the Inventory */
    boolean isStocked = isInventoryStockedWithProducts(productsMap);
    Iterator<Map.Entry<Integer, Integer>> productIdIter = productsMap.entrySet().iterator();

    /** calculate to cost of products */
    BigDecimal totalCost = getTotalCost(products);

    /** calculate level up point */
    Integer total = totalCost.intValueExact();
    Integer levelUpPoint = getLevelUpPoints(customer, total);

    /* create invoice */
    Invoice invoice = new Invoice();
    invoice.setCustomerId(customer.getCustomerId());
    invoice.setPurchaseDate(LocalDate.now());
    invoice.setInvoiceItems(new ArrayList<>());
    Invoice newInvoice = invoiceService.createInvoice(invoice);
    List<InvoiceItem> utilInvoiceItems = new ArrayList<>();

    /* create invoice-items from product and update invoice item list in invoice */
    for (Product product: products) {
      InvoiceItem invoiceItem = new InvoiceItem();
      invoiceItem.setInvoiceId(newInvoice.getInvoiceId());
      Inventory inventory = inventoryService.getInventoryByProductId(product.getProductId());
      invoiceItem.setInventoryId(inventory.getInventoryId());
      invoiceItem.setQuantity(productsMap.get(product.getProductId()));
      invoiceItem.setUnitPrice(product.getUnitCost());
      InvoiceItem newInvoiceItem = invoiceService.createInvoiceItem(invoiceItem);
      utilInvoiceItems.add(newInvoiceItem);
    }

    newInvoice.setInvoiceItems(utilInvoiceItems);
    invoiceService.updateInvoice(newInvoice.getInvoiceId(), newInvoice);

    InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
    invoiceViewModel.setInvoice(newInvoice);
    invoiceViewModel.setTotalCost(totalCost);
    invoiceViewModel.setLevelUpPoints(levelUpPoint);
    return invoiceViewModel;
  }

  private Integer getLevelUpPoints(Customer customer, Integer total) {
    /**
     * @param customer is the customer making the order.
     * @param total is the total cost of all the products in the order.
     * @return invoiceLevelUpPoints is the level up points the based on total.
     */
    // calculate level up point
    Integer points = (total/50) * 10;

    // create and submit level up to the level up database
    LevelUp levelUp = new LevelUp();
    levelUp.setCustomerId(customer.getCustomerId());
    levelUp.setPoints(points);
    levelUp.setMemberDate(LocalDate.now());
    LevelUp newLevelUp = levelUpService.createLevelUp(levelUp);

    /*TODO: Refactor to check if customerId is already in levelup DB
       then update levelup points for thr customer otherwise create
       new level up for the customer*/
    //LevelUp existingLevelUp = levelUp.getLevelByCustomer(customer.getCustomerId());
    // get level up points associated with customer from level up DB
    LevelUp customerLevelUp = levelUpService.getLevelUp(customer.getCustomerId());
    Integer invoiceLevelUpPoints = customerLevelUp.getPoints();

    return invoiceLevelUpPoints;
  }

  private BigDecimal getTotalCost(List<Product> products) {
    /**
     * @param products is a list of products in the order.
     * @return totalCost is the total amount of all the products in the products.
     */
    BigDecimal totalCost = BigDecimal.ZERO;
    for (Product product: products) {
      totalCost = totalCost.add(product.getUnitCost());
    }

    return totalCost;
  }

  private boolean isInventoryStockedWithProducts(Map<Integer, Integer> productsMap) {
    /**
     * @param productsMap contains the information about the products in order.
     * @return isStocked, if and only if product's Quantity <= Inventory's Quantity.
     * @see getProductInformation(List<Product> products)
     */
    boolean isStocked = true;
    Iterator<Map.Entry<Integer, Integer>> productsIterator = productsMap.entrySet().iterator();
    while(productsIterator.hasNext()) {
      Map.Entry<Integer, Integer> entry = productsIterator.next();
      Integer productId = entry.getKey();
      Integer productQuantity = entry.getValue();
      Inventory inventory = inventoryService.getInventoryByProductId(productId);
      if (productQuantity < inventory.getQuantity()) {
        isStocked = true;
      } else {
        isStocked = false;
      }
    }

    return isStocked;
  }

  private Map<Integer, Integer> getProductInformation(List<Product> products) {
    /**
     * @param products is a list of the products contained in the order.
     * @return a Map that contains a breakdown of products in the order.
     * The key is the productId of a product and its corresponding value
     * is the quantity of that product.
     */
    Map<Integer, Integer> productsMap = new HashMap<>();

    for (Product product: products) {
      Integer productId = product.getProductId();
      if (!(productsMap.containsKey(productId))) {
        productsMap.put(productId, 1);
      } else {
        Integer productQuantity = productsMap.get(productId);
        productsMap.put(productId, ++productQuantity);
      }
    }

    return productsMap;
  }
}
