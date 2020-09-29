package com.yunyun.financemanager.common.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 接口返回包装类
 * @author zhaoqin
 */
public class ApiResponse<T> implements Serializable {

    private Integer code;

    private Long total;

    private String message;

    private T data;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;

    public static ApiResponse<Void> ok() {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ResponseCode.OK.getValue());
        apiResponse.setMessage(ResponseCode.OK.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());
        return apiResponse;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ResponseCode.OK.getValue());
        apiResponse.setMessage(ResponseCode.OK.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> ok(T data, Long total) {
        ApiResponse<T> apiResponse = ApiResponse.ok(data);
        apiResponse.setTotal(total);
        return apiResponse;
    }

    public static ApiResponse<Void> failure(ResponseCode responseCode) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(responseCode.getValue());
        apiResponse.setMessage(responseCode.getMessage());
        apiResponse.setTimestamp(LocalDateTime.now());
        return apiResponse;
    }

    public static ApiResponse<Void> failure(ResponseCode responseCode, String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(responseCode.getValue());
        apiResponse.setMessage(message);
        apiResponse.setTimestamp(LocalDateTime.now());
        return apiResponse;
    }

    public static ApiResponse<Void> failure(String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(message);
        apiResponse.setTimestamp(LocalDateTime.now());
        return apiResponse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
