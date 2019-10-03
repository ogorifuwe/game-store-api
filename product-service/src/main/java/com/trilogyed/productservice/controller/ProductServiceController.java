package com.trilogyed.productservice.controller;

import com.trilogyed.productservice.dao.ProductDao;
import com.trilogyed.productservice.exception.NotFoundException;
import com.trilogyed.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class ProductServiceController {

  @Autowired
  private ProductDao productDao;

  @RequestMapping(value = "/products", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product product) {

    Product newProduct = productDao.createProduct(product);
    return newProduct;
  }

  @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Product getProduct(@PathVariable(value = "id") int productId) {

    Product product = productDao.getProduct(productId);
    if (product == null) {
      throw new NotFoundException("No Product with the given Product Id was found in the System. ID: "+productId);
    }

    return product;
  }

  @RequestMapping(value = "/products/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Product> getAllProdcuts() {

    List<Product> products = productDao.getAllProducts();
    if (products != null && products.size() == 0) {
      throw new IllegalArgumentException("No Products were found in the System.");
    }

    return products;
  }

  @RequestMapping(value = "products/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateProduct(@PathVariable(value = "id") int productId,
                            @RequestBody Product product) {

    if (!(productId == product.getProductId())) {
      throw new IllegalArgumentException("Product Id on path must be the same as the Id of Product to be updated.");
    }

    productDao.updateProduct(product);
  }

  @RequestMapping(value = "products/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable(value = "id") int productId) {

    productDao.deleteProduct(productId);
  }

}