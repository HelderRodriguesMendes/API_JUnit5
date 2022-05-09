package com.cursoUdemy.apiJunit5.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandarError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
