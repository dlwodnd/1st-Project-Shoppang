package com.project.test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcpetionResponse {
    private LocalDateTime localDate;
    private String message;
    private String details;
    private String errorCode;
}
