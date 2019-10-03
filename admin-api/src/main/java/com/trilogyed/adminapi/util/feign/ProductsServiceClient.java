package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductsServiceClient {

  @RequestMapping(value = "/products", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  Product createProduct(@RequestBody Product product);

  @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  Product getProduct(@PathVariable(value = "id") int productId);

  @RequestMapping(value = "/products/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<Product> getAllProducts();

  @RequestMapping(value = "products/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void updateProduct(@PathVariable(value = "id") int productId, @RequestBody Product product);

  @RequestMapping(value = "products/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void deleteProduct(@PathVariable(value = "id") int productId);

}
