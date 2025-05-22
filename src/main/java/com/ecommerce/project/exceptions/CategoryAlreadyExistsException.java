package com.ecommerce.project.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategoryAlreadyExistsException() {
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
