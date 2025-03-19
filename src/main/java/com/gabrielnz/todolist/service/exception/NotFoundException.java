package com.gabrielnz.todolist.service.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(Object id) {
    super("Resource not found: "+id);
  }
}
