package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;


  private static final String INSERT_PRODUCT_SQL =
          "insert into product(product_name, product_description, "+
                  "list_price, unit_cost) values(?,?,?,?)";
  private static final String SELECT_PRODUCT_BY_ID_SQL =
          "select * from product where product_id = ?";
  private static final String SELECT_ALL_PRODUCTS_SQL =
          "select * from product";
  private static final String UPDATE_PRODUCT_SQL =
          "update product set product_name = ?, product_description = ?, "+
                  "list_price = ?, unit_cost = ? where product_id = ?";
  private static final String DELETE_PRODUCT_SQL =
          "delete from product where product_id = ?";

  @Override
  public Product createProduct(Product product) {

    jdbcTemplate.update(INSERT_PRODUCT_SQL,
            product.getProductName(),
            product.getProductDescription(),
            product.getListPrice(),
            product.getUnitCost());

    int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
    product.setProductId(id);
    return product;

  }

  @Override
  public Product getProduct(int productId) {

    try {
      return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_SQL,
              this::mapRowToProduct, productId);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }

  }

  @Override
  public List<Product> getAllProducts() {

    return jdbcTemplate.query(SELECT_ALL_PRODUCTS_SQL, this::mapRowToProduct);

  }

  @Override
  public void updateProduct(Product product) {

    jdbcTemplate.update(UPDATE_PRODUCT_SQL,
            product.getProductName(),
            product.getProductDescription(),
            product.getListPrice(),
            product.getUnitCost(),
            product.getProductId());

  }

  @Override
  public void deleteProduct(int productId) {

    jdbcTemplate.update(DELETE_PRODUCT_SQL, productId);

  }

  private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {

    Product product = new Product();
    product.setProductId(rs.getInt("product_id"));
    product.setProductName(rs.getString("product_name"));
    product.setProductDescription(rs.getString("product_description"));
    product.setListPrice(rs.getBigDecimal("list_price"));
    product.setUnitCost(rs.getBigDecimal("unit_cost"));
    return product;
  }
}
