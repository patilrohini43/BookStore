package com.bridgelabz.bookstore.model;

public class Response {

    private int statusCode;
    private String statusMessage;
    private String token;

    public Response(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Response(int statusCode, String statusMessage, String token) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.token=token;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
