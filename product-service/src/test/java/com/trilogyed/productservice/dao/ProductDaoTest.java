package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoTest {

  @Autowired
  private ProductDao fixture;

  @Before
  public void setUp() throws Exception {

    List<Product> fixtures = fixture.getAllProducts();
    fixtures
            .stream()
            .forEach(product -> fixture.deleteProduct(product.getProductId()));
  }

  @After
  public void tearDown() throws Exception {
    fixture = null;
  }

  @Test
  public void GetProductById() {
    /**
     * Creates a new Product object that is exactly same as one in test DB.
     * Retrieves Matching Product object from DB with productID.
     * Asserts that they are equal.
     */
    Product product = new Product();
    product.setProductName("PS4");
    product.setProductDescription("A video game console by Sony");
    product.setListPrice(new BigDecimal("299.99"));
    product.setUnitCost(new BigDecimal("299.99"));
    Product createdProduct = fixture.createProduct(product);

    Product productFromDB = fixture.getProduct(createdProduct.getProductId());
    assertNotNull(productFromDB);
    assertEquals(productFromDB, createdProduct);

  }

  @Test
  public void createProduct() {
    /**
     * Creates a new Product object and save it to DB.
     * Retrieves the Product from the DB using geProductById.
     * Assert that Products are equal.
     */
    Product product = new Product();
    product.setProductName("PS4 Pro");
    product.setProductDescription("A video game console by Sony");
    product.setListPrice(new BigDecimal("399.99"));
    product.setUnitCost(new BigDecimal("399.99"));
    Product createdProduct = fixture.createProduct(product);

    Product testProduct = fixture.getProduct(createdProduct.getProductId());
    assertNotNull(testProduct);
    assertEquals(createdProduct, testProduct);

  }

  @Test
  public void DeleteProduct() {
    /**
     * Creates a new Product object and saves it to the DB.
     * Deletes the Product from the DB.
     * Gets the Product from the DB.
     * Asserts that the Product returned is null.
     */
    Product product = new Product();
    product.setProductName("XBOX 360");
    product.setProductDescription("A video game console by Microsoft");
    product.setListPrice(new BigDecimal("399.99"));
    product.setUnitCost(new BigDecimal("399.99"));
    Product createdProduct = fixture.createProduct(product);

    fixture.deleteProduct(createdProduct.getProductId());
    Product testProduct = fixture.getProduct(createdProduct.getProductId());
    assertNull(testProduct);

  }

  @Test
  public void getAllProducts() {
    /**
     * Creates two Products and saves them to the DB.
     * Asserts that the list of Products in the DB are equal to 2.
     */
    Product product = new Product();
    product.setProductName("Nintendo Switch");
    product.setProductDescription("A video game console by Nintendo");
    product.setListPrice(new BigDecimal("199.99"));
    product.setUnitCost(new BigDecimal("199.99"));
    Product createdProduct = fixture.createProduct(product);

    Product product2 = new Product();
    product2.setProductName("PSP");
    product2.setProductDescription("A video game console by Sony");
    product2.setListPrice(new BigDecimal("199.99"));
    product2.setUnitCost(new BigDecimal("199.99"));
    Product createdProduct2 = fixture.createProduct(product2);

    List<Product> productList = fixture.getAllProducts();
    assertNotNull(productList);
    assertEquals(2, productList.size());

  }

  @Test
  public void updateProduct() {
    /**
     *  Creates a new Product object and saves it to the DB.
     *  Updates the Product entry and saves it to the DB.
     *  Retrieve the Product from the DB.
     *  Assert that they are equal.
     */
    Product product = new Product();
    product.setProductName("PS4 Pro");
    product.setProductDescription("Video game console by Sony");
    product.setListPrice(new BigDecimal("299.99"));
    product.setUnitCost(new BigDecimal("299.99"));
    Product createdProduct = fixture.createProduct(product);

    product.setListPrice(new BigDecimal("399.99"));
    product.setUnitCost(new BigDecimal("399.99"));
    fixture.updateProduct(createdProduct);

    Product testProduct = fixture.getProduct(createdProduct.getProductId());
    assertNotNull(testProduct);
    assertEquals(createdProduct, testProduct);

  }

}
