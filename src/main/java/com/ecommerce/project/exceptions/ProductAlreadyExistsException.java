package com.ecommerce.project.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductAlreadyExistsException() {
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
