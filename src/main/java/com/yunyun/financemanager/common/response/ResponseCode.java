package com.yunyun.financemanager.common.response;

/**
 * @author zhaoqin
 */
public enum ResponseCode {

    OK(200, "success"),
    ERROR(500, "server internal error"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    ;

    private final int value;
    private final String message;

    ResponseCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
