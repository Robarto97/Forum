package com.edu.mse.pwc.dtos;

import lombok.Data;

@Data
public class Counter <T> {
    private int status;
    private String message;
    private Object result;
    private long count;


    public Counter(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Counter(int status, String message, Object result, long count) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.count = count;
    }
}