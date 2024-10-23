package com.example.VuDuyDuc_Task_springboot.service;

public class StorageException extends RuntimeException  {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
