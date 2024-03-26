package com.keikantswe.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private LocalDateTime timeStamp;
    private String message;
    private int statusCode;
    private HttpStatus status;
    private Object data;

}
