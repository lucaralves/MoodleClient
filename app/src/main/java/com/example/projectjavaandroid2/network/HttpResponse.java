package com.example.projectjavaandroid2.network;

public class HttpResponse {
    private int status;
    private String  body;

    public HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
