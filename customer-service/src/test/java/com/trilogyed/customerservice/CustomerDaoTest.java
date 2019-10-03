package com.trilogyed.customerservice;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

  @Autowired
  CustomerDao fixture;

  @Before
  public void setUp() throws Exception {

    List<Customer> fixtures = fixture.getAllCustomers();
    fixtures
            .stream()
            .forEach(customer -> fixture.deleteCustomer(customer.getCustomerId()));

  }

  @After
  public void tearDown() throws Exception {
    fixture = null;
  }

  @Test
  public void createGet() {
    /**
     * Creates a Customer object.
     * Retrieves the created Customer object using its Id.
     * Asserts that the created object and retrieved object are equal.
     */
    Customer customer = new Customer();
    customer.setFirstName("Albert");
    customer.setLastName("Ifuwe");
    customer.setStreet("First Street");
    customer.setCity("New York");
    customer.setZip("055");
    customer.setEmail("aifuwe@gmail.com");
    customer.setPhone("649-864-8222");
    Customer createdCustomer = fixture.createCustomer(customer);

    Customer testCustomer = fixture.getCustomer(createdCustomer.getCustomerId());
    assertNotNull(testCustomer);
    assertEquals(createdCustomer, testCustomer);

  }

  @Test
  public void getAllCustomers() {
    /**
     * Creates a couple Customers objects and adds them to the DB.
     * Retrieves the created couple Customers from the DB and saves them in a list.
     * Asserts that the size of the retrieved list is equal to the number of Customers objects created.
     */
    Customer customer = new Customer();
    customer.setFirstName("Albert");
    customer.setLastName("Ifuwe");
    customer.setStreet("First Street");
    customer.setCity("New York");
    customer.setZip("055");
    customer.setEmail("aifuwe@gmail.com");
    customer.setPhone("649-864-8222");
    Customer createdCustomer = fixture.createCustomer(customer);

    Customer customer2 = new Customer();
    customer2.setFirstName("Albert");
    customer2.setLastName("Ifuwe");
    customer2.setStreet("First Street");
    customer2.setCity("New York");
    customer2.setZip("055");
    customer2.setEmail("aifuwe@gmail.com");
    customer2.setPhone("649-864-8222");
    Customer createdCustomer2 = fixture.createCustomer(customer2);

    List<Customer> customerList = fixture.getAllCustomers();
    assertNotNull(customerList);
    assertEquals(2, customerList.size());

  }

  @Test
  public void updateCustomer() {
    /**
     * Creates a new Customer object and adds it to the DB.
     * Updates the street and zip of the Customer and saves it to the DB.
     * Retrieves the updated Customer object from the DB and asserts that
     * its equal to the created Customer object.
     */
    Customer customer = new Customer();
    customer.setFirstName("Albert");
    customer.setLastName("Ifuwe");
    customer.setStreet("First Street");
    customer.setCity("New York");
    customer.setZip("055");
    customer.setEmail("aifuwe@gmail.com");
    customer.setPhone("649-864-8222");
    Customer createdCustomer = fixture.createCustomer(customer);

    customer.setStreet("Second Street");
    customer.setZip("056");
    fixture.updateCustomer(customer);

    Customer testCustomer = fixture.getCustomer(customer.getCustomerId());
    assertNotNull(testCustomer);
    assertEquals(createdCustomer, testCustomer);

  }

  @Test
  public void deleteCustomer() {
    /**
     * Creates a new Customer object and adds it to the DB.
     * Deletes the Customer object from the DB.
     * Retrieves the Customer object from the DB and assert that it is null.
     */
    Customer customer = new Customer();
    customer.setFirstName("Albert");
    customer.setLastName("Ifuwe");
    customer.setStreet("First Street");
    customer.setCity("New York");
    customer.setZip("055");
    customer.setEmail("aifuwe@gmail.com");
    customer.setPhone("649-864-8222");
    Customer createdCustomer = fixture.createCustomer(customer);

    fixture.deleteCustomer(customer.getCustomerId());
    Customer deletedCustomer = fixture.getCustomer(customer.getCustomerId());
    assertNull(deletedCustomer);

  }

}
