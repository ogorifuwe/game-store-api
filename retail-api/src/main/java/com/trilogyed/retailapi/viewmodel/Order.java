package com.trilogyed.retailapi.viewmodel;

import com.trilogyed.retailapi.model.Customer;
import com.trilogyed.retailapi.model.Product;

import java.util.List;
import java.util.Objects;

public class Order {

  private Customer customer;
  private List<Product> products;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return customer.equals(order.customer) &&
            products.equals(order.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, products);
  }

  @Override
  public String toString() {
    return "Order{" +
            "customer=" + customer +
            ", products=" + products +
            '}';
  }
}
