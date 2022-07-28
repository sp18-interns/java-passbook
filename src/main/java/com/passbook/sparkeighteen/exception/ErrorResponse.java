package com.passbook.sparkeighteen.exception;

import com.passbook.sparkeighteen.constant.ErrorMessage;
import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private final ErrorMessage message;
    private final List<String> details;

    public ErrorResponse(ErrorMessage message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
}