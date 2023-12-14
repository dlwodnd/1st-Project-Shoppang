package com.project.test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcpetionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
