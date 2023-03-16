package com.example.mockreskill.common.exeption;

import com.example.mockreskill.model.response.ErrorDetail;

public class NotFoundException extends RuntimeException {
    private ErrorDetail error;

    public NotFoundException(String field, String errorCode) {
        this.error = new ErrorDetail();
        this.error.setField(field);
        this.error.setErrorCode(errorCode);
    }

    public NotFoundException(String errorCode) {
        this.error = new ErrorDetail();
        this.error.setErrorCode(errorCode);
    }

    public ErrorDetail getError() {
        return this.error;
    }

    public void setError(final ErrorDetail error) {
        this.error = error;
    }

    public NotFoundException(final ErrorDetail error) {
        this.error = error;
    }
}
