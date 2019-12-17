package com.example.demo.response;

public enum HTTPStatus {
    SUCCESS(200, "Request success"),
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Access is forbidden"),
    NOT_FOUND(404, "Not found resource"),
    CONFLICT(409, "Data exist in system"),
    DATA_NOT_FOUND(405, "Data not found in system"),
    SERVER_ERROR(500, "Internal server error"),
    SERVICE_NOT_AVAILABLE(503, "Service not available");
    private Integer code;
    private String message;

    HTTPStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
