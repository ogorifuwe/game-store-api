package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String INSERT_INTO_INVOICE_SQL =
          "insert into invoice(customer_id, purchase_date) values(?, ?)";
  private static final String SELECT_INVOICE_SQL =
          "select * from invoice where invoice_id = ?";
  private static final String SELECT_ALL_INVOICES =
          "select * from invoice";
  private static final String SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL =
          "select * from invoice where customer_id = ?";
  private static final String UPDATE_INVOICE_SQL =
          "update invoice set customer_id = ?, purchase_date = ? where invoice_id = ?";
  private static final String DELETE_INVOICE_SQL =
          "delete from invoice where invoice_id = ?";

  @Override
  @Transactional
  public Invoice createInvoice(Invoice invoice) {

    jdbcTemplate.update(INSERT_INTO_INVOICE_SQL,
            invoice.getCustomerId(),
            invoice.getPurchaseDate());

    int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
    invoice.setInvoiceId(id);
    return invoice;

  }

  @Override
  public Invoice getInvoice(int invoiceId) {

    try {
      return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL,
              this::mapRowToInvoice, invoiceId);
    } catch(EmptyResultDataAccessException e) {
        return null;
    }

  }

  @Override
  public List<Invoice> getAllInvoices() {

    return jdbcTemplate.query(SELECT_ALL_INVOICES, this::mapRowToInvoice);

  }

  @Override
  public List<Invoice> getInvoicesByCustomerId(int customerId) {

    return jdbcTemplate.query(SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customerId);

  }

  @Override
  public void updateInvoice(Invoice invoice) {

    jdbcTemplate.update(UPDATE_INVOICE_SQL,
            invoice.getCustomerId(),
            invoice.getPurchaseDate(),
            invoice.getInvoiceId());

  }

  @Override
  public void deleteInvoice(int invoiceId) {

    jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);

  }

  private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {

    Invoice invoice = new Invoice();
    invoice.setInvoiceId(rs.getInt("invoice_id"));
    invoice.setCustomerId(rs.getInt("customer_id"));
    invoice.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
    return invoice;

  }
}
