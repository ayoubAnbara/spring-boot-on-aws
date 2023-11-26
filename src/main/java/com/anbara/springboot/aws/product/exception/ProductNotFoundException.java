package com.anbara.springboot.aws.product.exception;

/**
 * @author Ayoub ANBARA
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
