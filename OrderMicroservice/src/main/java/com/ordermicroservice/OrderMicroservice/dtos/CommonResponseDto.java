package com.ordermicroservice.OrderMicroservice.dtos;

import org.springframework.http.HttpStatus;

public class CommonResponseDto {
    private String message;
   // private HttpStatus status;

    public CommonResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   /* public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }*/
}

