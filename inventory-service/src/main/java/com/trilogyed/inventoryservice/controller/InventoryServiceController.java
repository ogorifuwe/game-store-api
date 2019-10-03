package com.trilogyed.inventoryservice.controller;

import com.trilogyed.inventoryservice.dao.InventoryDao;
import com.trilogyed.inventoryservice.exception.NotFoundException;
import com.trilogyed.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class InventoryServiceController {

  @Autowired
  private InventoryDao inventoryDao;

  @RequestMapping(value = "/inventory", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Inventory createInventory(@RequestBody Inventory inventory) {

    Inventory newInventory = inventoryDao.createInventory(inventory);
    return newInventory;
  }

  @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Inventory getInventory(@PathVariable(value = "id") int inventoryId) {

    Inventory inventory = inventoryDao.getInventory(inventoryId);
    if (inventory == null) {
      throw new NotFoundException("No Inventory with given Id was found in the System. ID: "+inventoryId);
    }

    return inventory;
  }

  @RequestMapping(value = "inventory/product/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Inventory getInventoryByProductId(@PathVariable(value = "id") int productId) {

    Inventory inventory = inventoryDao.getInventoryByProduct(productId);
    if (inventory == null) {
      throw new NotFoundException("No Inventory with given Id was found in the System: "+productId);
    }

    return inventory;
  }

  @RequestMapping(value = "/inventory/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Inventory> getAllInventory() {

    List<Inventory> inventoryList = inventoryDao.getAllInventory();
    if (inventoryList != null && inventoryList.size() == 0) {
      throw new NotFoundException("No Inventory was found in the System.");
    }

    return inventoryList;
  }

  @RequestMapping(value = "/inventory/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateInventory(@PathVariable(value = "id") int inventoryId,
                              @RequestBody Inventory inventory) {

    if (!(inventoryId == inventory.getInventoryId())) {
      throw new IllegalArgumentException("Inventory Id on path must be the same as Id of Inventory to be updated.");
    }

    inventoryDao.updateInventory(inventory);
  }

  @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteInventory(@PathVariable(value = "id") int inventoryId) {

    inventoryDao.deleteInventory(inventoryId);
  }
}
