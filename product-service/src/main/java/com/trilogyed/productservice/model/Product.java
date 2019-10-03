package com.trilogyed.productservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Product {

  private int productId;
  @NotEmpty(message = "Please provide a value for PRODUCT NAME.")
  private String productName;
  @NotEmpty(message = "Please provide a value for PRODUCT DESCRIPTION.")
  private String productDescription;
  @NotNull(message = "Please provide a value for LIST PRICE.")
  private BigDecimal listPrice;
  @NotNull(message = "Please provide a value for UNIT COST.")
  private BigDecimal unitCost;

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public BigDecimal getListPrice() {
    return listPrice;
  }

  public void setListPrice(BigDecimal listPrice) {
    this.listPrice = listPrice;
  }

  public BigDecimal getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(BigDecimal unitCost) {
    this.unitCost = unitCost;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return productId == product.productId &&
            productName.equals(product.productName) &&
            productDescription.equals(product.productDescription) &&
            listPrice.equals(product.listPrice) &&
            unitCost.equals(product.unitCost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, productName, productDescription, listPrice, unitCost);
  }

  @Override
  public String toString() {
    return "Product{" +
            "productId=" + productId +
            ", name='" + productName + '\'' +
            ", productDescription='" + productDescription + '\'' +
            ", listPrice=" + listPrice +
            ", unitCost=" + unitCost +
            '}';
  }
}
