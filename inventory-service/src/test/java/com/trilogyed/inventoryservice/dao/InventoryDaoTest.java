package com.trilogyed.inventoryservice.dao;

import com.trilogyed.inventoryservice.model.Inventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoTest {

  @Autowired
  private InventoryDao inventoryDao;

  @Before
  public void setUp() throws Exception {

    List<Inventory> inventories = inventoryDao.getAllInventory();
    inventories
            .stream()
            .forEach(inventory -> inventoryDao.deleteInventory(inventory.getInventoryId()));

  }

  @After
  public void tearDown() throws Exception {

    inventoryDao = null;

  }

  @Test
  public void createGetInventory() {

    Inventory inventory = new Inventory();
    inventory.setProductId(1);
    inventory.setQuantity(10);

    Inventory newInventory = inventoryDao.createInventory(inventory);
    Inventory testInventory = inventoryDao.getInventory(newInventory.getInventoryId());

    assertNotNull(testInventory);
    assertEquals(newInventory, testInventory);

  }

  @Test
  public void getInventoryByProductId() {

    Inventory inventory = new Inventory();
    inventory.setProductId(1);
    inventory.setQuantity(10);

    Inventory newInventory = inventoryDao.createInventory(inventory);
    Inventory testInventory = inventoryDao.getInventoryByProduct(newInventory.getProductId());

    assertNotNull(testInventory);
    assertEquals(newInventory, testInventory);

  }

  @Test
  public void getAllInventories() {

    Inventory inventory = new Inventory();
    inventory.setProductId(1);
    inventory.setQuantity(10);

    Inventory newInventory = inventoryDao.createInventory(inventory);

    Inventory inventory2 = new Inventory();
    inventory2.setProductId(1);
    inventory2.setQuantity(10);

    Inventory newInventory2 = inventoryDao.createInventory(inventory2);

    List<Inventory> inventories = inventoryDao.getAllInventory();

    assertNotNull(inventories);
    assertTrue(inventories.size() == 2);

  }

  @Test
  public void updateInventory() {

    Inventory inventory = new Inventory();
    inventory.setProductId(1);
    inventory.setQuantity(10);

    Inventory newInventory = inventoryDao.createInventory(inventory);

    inventory.setQuantity(20);

    inventoryDao.updateInventory(newInventory);
    Inventory testInventory = inventoryDao.getInventory(newInventory.getInventoryId());

    assertNotNull(testInventory);
    assertEquals(newInventory, testInventory);

  }

  @Test
  public void deleteInventory() {

    Inventory inventory = new Inventory();
    inventory.setProductId(1);
    inventory.setQuantity(10);

    Inventory newInventory = inventoryDao.createInventory(inventory);

    inventoryDao.deleteInventory(newInventory.getInventoryId());
    Inventory testInventory = inventoryDao.getInventory(newInventory.getInventoryId());

    assertNull(testInventory);

  }
}
