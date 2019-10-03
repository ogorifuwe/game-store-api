package com.trilogyed.adminapi.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class Product {

  private int productId;
  private String productName;
  private String productDescription;
  private BigDecimal listPrice;
  private BigDecimal unitCost;
  MathContext mc = new MathContext(2);

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
    return listPrice.round(mc);
  }

  public void setListPrice(BigDecimal listPrice) {
    this.listPrice = listPrice.round(mc);
  }

  public BigDecimal getUnitCost() {
    return unitCost.round(mc);
  }

  public void setUnitCost(BigDecimal unitCost) {
    this.unitCost = unitCost.round(mc);
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
            ", productName='" + productName + '\'' +
            ", productDescription='" + productDescription + '\'' +
            ", listPrice=" + listPrice +
            ", unitCost=" + unitCost +
            '}';
  }
}
