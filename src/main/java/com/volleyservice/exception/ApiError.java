package com.volleyservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ApiError {

    private HttpStatus status;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String path;

    public ApiError(HttpStatus status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
}
