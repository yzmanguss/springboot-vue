package com.yunyun.financemanager.advice;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.response.ResponseCode;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author zhaoqin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数绑定到 Java Bean 上失败时抛出
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResponse<Void> handleBindException(BindException ex) {
        StringBuilder builder = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach(e -> builder.append(e.getField())
                .append(": ")
                .append(e.getDefaultMessage())
                .append(";"));
        return ApiResponse.failure(ResponseCode.BAD_REQUEST, builder.toString());
    }

    /**
     * 请求体绑定到 Java Bean 上失败时抛出
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder builder = new StringBuilder();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> builder.append(e.getField())
                .append(": ")
                .append(e.getDefaultMessage())
                .append(";"));
        return ApiResponse.failure(ResponseCode.BAD_REQUEST, builder.toString());
    }

    /**
     * 普通参数（非 Java Bean）校验出错时抛出
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiResponse<Void> handleConstraintViolationExceptionHandler(ConstraintViolationException e) {
        StringBuilder builder = new StringBuilder();
        String[] messages = e.getMessage().split(", ");
        for (String message : messages) {
            String[] fieldAndMessage = message.split(": ");
            builder.append(fieldAndMessage[0].split("\\.")[1])
                    .append(": ")
                    .append(fieldAndMessage[1])
                    .append(";");
        }
        return ApiResponse.failure(ResponseCode.BAD_REQUEST, builder.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.failure(ResponseCode.BAD_REQUEST, e.getMessage());
    }
}
