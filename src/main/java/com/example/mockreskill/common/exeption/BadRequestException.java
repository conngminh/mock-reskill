package com.example.mockreskill.common.exeption;

import com.example.mockreskill.model.response.ErrorDetail;

public class BadRequestException extends RuntimeException {
    private ErrorDetail error;
    public BadRequestException(String field, String errorCode) {
        this.error = new ErrorDetail();
        this.error.setField(field);
        this.error.setErrorCode(errorCode);
    }
    public BadRequestException(String errorCode) {
        this.error = new ErrorDetail();
        this.error.setErrorCode(errorCode);
    }
    public ErrorDetail getError() {
        return this.error;
    }
    public void setError(final ErrorDetail error) {
        this.error = error;
    }
    public BadRequestException(final ErrorDetail error) {
        this.error = error;
    }
}
