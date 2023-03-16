package com.example.mockreskill.common.exeption;

import com.example.mockreskill.model.response.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class BXGExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BXGExceptionHandler.class);

    public BXGExceptionHandler() {
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleInternalServerError(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDetail((String)null, "E_999"));
    }

    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException!", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetail((String)null, "E_001"));
    }

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
    }
    
    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<?> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException e) {
        log.error("HttpMediaTypeNotSupportedException!", e);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ErrorDetail((String)null, "E_006"));
    }
    @ResponseBody
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getError());
    }
    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> processMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException!", e);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorDetail((String)null, "E_007"));
    }

    @ResponseBody
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public ResponseEntity handleFileUploadSizeLimit(MaxUploadSizeExceededException e) {
        log.error("FileUploadBase$SizeLimitExceededException!", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetail((String)null, "E_004"));
    }

    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<?> handleParamException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getErrorFields(e));
    }

    @ResponseBody
    @ExceptionHandler({java.nio.file.AccessDeniedException.class})
    public ResponseEntity processAccessDeniedException(java.nio.file.AccessDeniedException e) {
        log.error("AccessDeniedException!", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDetail((String)null, "E_005"));
    }

    @ResponseBody
    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity handleMissingRequestHeader(MissingRequestHeaderException e) {
        log.error("MissingRequestHeaderException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getErrorFields(e));
    }

    @ExceptionHandler({MissingServletRequestPartException.class, MultipartException.class})
    @ResponseBody
    public ResponseEntity handleMissingRequestPart(MissingServletRequestPartException e) {
        log.error("MissingServletRequestPartException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getErrorFields(e));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMissingParameter(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        String errorCode = (String)e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetail((String)null, errorCode));
    }

    private ErrorDetail getErrorFields(Exception e) {
        String field = "";
        if (e instanceof MissingRequestHeaderException) {
            field = ((MissingRequestHeaderException)e).getHeaderName();
        } else if (e instanceof MissingServletRequestParameterException) {
            field = ((MissingServletRequestParameterException)e).getParameterName();
        } else if (e instanceof MissingServletRequestPartException) {
            field = ((MissingServletRequestPartException)e).getRequestPartName();
        }

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setField(field);
        errorDetail.setErrorCode("E_002");
        return errorDetail;
    }
}
