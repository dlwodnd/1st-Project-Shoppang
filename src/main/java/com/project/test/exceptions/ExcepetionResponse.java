package com.project.test.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ExcepetionResponse {
    private final LocalDateTime localDate;
    private final String message;
    private final String detail;

}
