package com.trilogyed.customerservice.exception;

/**
 * Used to handle not found cases in the API
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(){
  }

  public NotFoundException(String message) {
    super(message);
  }
}
