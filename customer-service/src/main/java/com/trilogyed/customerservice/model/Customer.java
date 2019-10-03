package com.trilogyed.customerservice.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Customer {

  private int customerId;
  @NotEmpty(message = "Please provide a value for FIRST NAME.")
  private String firstName;
  @NotEmpty(message = "Please provide a value for LAST NAME.")
  private String lastName;
  @NotEmpty(message = "Please provide a value for STREET.")
  private String street;
  @NotEmpty(message = "Please provide a value for CITY.")
  private String city;
  @NotEmpty(message = "Please provide a value for ZIP.")
  private String zip;
  @NotEmpty(message = "Please provide a value for EMAIL.")
  private String email;
  @NotEmpty(message = "Please provide a value for PHONE.")
  private String phone;

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return customerId == customer.customerId &&
            firstName.equals(customer.firstName) &&
            lastName.equals(customer.lastName) &&
            street.equals(customer.street) &&
            city.equals(customer.city) &&
            zip.equals(customer.zip) &&
            email.equals(customer.email) &&
            phone.equals(customer.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, firstName, lastName, street, city, zip, email, phone);
  }

  @Override
  public String toString() {
    return "Customer{" +
            "customerId=" + customerId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", street='" + street + '\'' +
            ", city='" + city + '\'' +
            ", zip='" + zip + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            '}';
  }
}
