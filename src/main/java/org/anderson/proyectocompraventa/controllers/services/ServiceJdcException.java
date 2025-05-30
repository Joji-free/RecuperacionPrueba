package org.anderson.proyectocompraventa.controllers.services;

public class ServiceJdcException extends RuntimeException{
    public ServiceJdcException(String message) {
        super(message);
    }
    public ServiceJdcException(String message, Throwable cause) {
        super(message, cause);
    }
}
