package com.testRestful.restful.exceptions;

public class OrderException extends RuntimeException {

    public OrderException(Long id) {
        super("Could not find order id: " + id);
    }

}
