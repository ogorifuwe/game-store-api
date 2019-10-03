package com.trilogyed.invoiceservice.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class InvoiceItem {

  private int invoiceItemId;
  @NotNull(message = "Please provide a value for INVOICE ID.")
  private int invoiceId;
  @NotNull(message = "Please provide a value for INVENTORY ID.")
  private int inventoryId;
  @NotNull(message = "please provide a value for QUANTITY")
  private int quantity;
  @NotNull(message = "Please provide a value for UNIT PRICE.")
  private BigDecimal unitPrice;
  private MathContext mc = new MathContext(2);

  public int getInvoiceItemId() {
    return invoiceItemId;
  }

  public void setInvoiceItemId(int invoiceItemId) {
    this.invoiceItemId = invoiceItemId;
  }

  public int getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(int invoiceId) {
    this.invoiceId = invoiceId;
  }

  public int getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(int inventoryId) {
    this.inventoryId = inventoryId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice.round(mc);
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice.round(mc);
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvoiceItem that = (InvoiceItem) o;
    return invoiceItemId == that.invoiceItemId &&
            invoiceId == that.invoiceId &&
            inventoryId == that.inventoryId &&
            quantity == that.quantity &&
            unitPrice.equals(that.unitPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceItemId, invoiceId, inventoryId, quantity, unitPrice);
  }

  @Override
  public String toString() {
    return "InvoiceItem{" +
            "invoiceItemId=" + invoiceItemId +
            ", invoiceId=" + invoiceId +
            ", inventoryId=" + inventoryId +
            ", quantity=" + quantity +
            ", unitPrice=" + unitPrice +
            '}';
  }
}
