package com.trilogyed.inventoryservice.dao;

import com.trilogyed.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcTemplateImpl implements InventoryDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String INSERT_INTO_INVENTORY_SQL =
          "insert into inventory(product_id, quantity) values(?, ?)";
  private static final String SELECT_INVENTORY_SQL =
          "select * from inventory where inventory_id = ?";
  private static final String SELECT_INVENTORY_BY_PRODUCT_SQL =
          "select * from inventory where product_id = ?";
  private static final String SELECT_ALL_INVENTORIES_SQL =
          "select * from inventory";
  private static final String UPDATE_INVENTORY_SQL =
          "update inventory set product_id = ?, quantity = ? where inventory_id = ?";
  private static final String DELETE_INVENTORY_SQL =
          "delete from inventory where inventory_id = ?";

  @Override
  public Inventory createInventory(Inventory inventory) {

    jdbcTemplate.update(INSERT_INTO_INVENTORY_SQL,
            inventory.getProductId(),
            inventory.getQuantity());

    int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
    inventory.setInventoryId(id);
    return inventory;

  }

  @Override
  public Inventory getInventory(int inventoryId) {

    try {
      return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL,
              this::mapRowToInventory, inventoryId);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }

  }

  @Override
  public Inventory getInventoryByProduct(int productId) {

    try {
      return jdbcTemplate.queryForObject(SELECT_INVENTORY_BY_PRODUCT_SQL,
              this::mapRowToInventory, productId);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
  }

  @Override
  public List<Inventory> getAllInventory() {

    return jdbcTemplate.query(SELECT_ALL_INVENTORIES_SQL, this::mapRowToInventory);

  }

  @Override
  public void updateInventory(Inventory inventory) {

    jdbcTemplate.update(UPDATE_INVENTORY_SQL,
            inventory.getProductId(),
            inventory.getQuantity(),
            inventory.getInventoryId());

  }

  @Override
  public void deleteInventory(int inventoryId) {

    jdbcTemplate.update(DELETE_INVENTORY_SQL, inventoryId);

  }

  private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {

    Inventory inventory = new Inventory();
    inventory.setInventoryId(rs.getInt("inventory_id"));
    inventory.setProductId(rs.getInt("product_id"));
    inventory.setQuantity(rs.getInt("quantity"));
    return inventory;

  }
}
