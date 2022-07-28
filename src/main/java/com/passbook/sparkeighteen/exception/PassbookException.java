package com.passbook.sparkeighteen.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PassbookException extends RuntimeException {
    private final HttpStatus code;
    private final String message;

    public PassbookException(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }
}