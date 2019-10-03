package com.trilogyed.retailapi.viewmodel;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.model.LevelUp;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

  private Invoice invoice;
  private BigDecimal totalCost;
  private int levelUpPoints;

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }

  public int getLevelUpPoints() {
    return levelUpPoints;
  }

  public void setLevelUpPoints(int levelUpPoints) {
    this.levelUpPoints = levelUpPoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvoiceViewModel that = (InvoiceViewModel) o;
    return levelUpPoints == that.levelUpPoints &&
            invoice.equals(that.invoice) &&
            totalCost.equals(that.totalCost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoice, totalCost, levelUpPoints);
  }

  @Override
  public String toString() {
    return "InvoiceViewModel{" +
            "invoice=" + invoice +
            ", totalCost=" + totalCost +
            ", levelUpPoints=" + levelUpPoints +
            '}';
  }
}
