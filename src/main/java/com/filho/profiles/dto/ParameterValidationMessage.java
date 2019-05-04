package com.filho.profiles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterValidationMessage {
    private String parameter, message;
}
