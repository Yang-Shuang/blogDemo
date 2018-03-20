package com.yhs.blog.entity;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class ResponseEntity {
    private String statusCode;
    private String statusMessage;

    public ResponseEntity(String statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseEntity(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
