package com.trilogyed.customerservice.controller;

import com.trilogyed.customerservice.dao.CustomerDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerServiceController.class)
public class CustomerServiceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CustomerDao customerDao;

  @InjectMocks
  CustomerServiceController customerServiceController;
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void createCustomer() {
  }

  @Test
  public void getCustomer() {
  }

  @Test
  public void getAllCustomers() {
  }

  @Test
  public void updateCustomer() {
  }

  @Test
  public void deleteCustomer() {
  }
}