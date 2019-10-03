package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;

import java.util.List;

public interface ProductDao {

  Product createProduct(Product product);

  Product getProduct(int productId);

  List<Product> getAllProducts();

  //List<Product> getProductByInvoiceId(int invoiceId);

  //List<Product> getProductsInInventory();

  void updateProduct(Product product);

  void deleteProduct(int productId);

}
