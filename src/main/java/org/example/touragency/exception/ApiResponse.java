package org.example.touragency.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;

    public ApiResponse() {}

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(String error) {
        this.success = false;
        this.error = error;
    }
}

