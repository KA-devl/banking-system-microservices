package com.banking.bankapi.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{

    @Getter
    private final Set<String> violations;

    @Getter
    private final String violationSource;
}
