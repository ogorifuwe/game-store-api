package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

  @RequestMapping(value = "/inventory", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  Inventory createInventory(@RequestBody Inventory inventory);

  @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  Inventory getInventory(@PathVariable(value = "id") int inventoryId);

  @RequestMapping(value = "inventory/product/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Inventory getInventoryByProductId(@PathVariable(value = "id") int productId);

  @RequestMapping(value = "/inventory/all-inventory", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<Inventory> getAllInventory();

  @RequestMapping(value = "/inventory/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void updateInventory(@PathVariable(value = "id") int inventoryId,
                       @RequestBody Inventory inventory);


  @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void deleteInventory(@PathVariable(value = "id") int inventoryId);

}
