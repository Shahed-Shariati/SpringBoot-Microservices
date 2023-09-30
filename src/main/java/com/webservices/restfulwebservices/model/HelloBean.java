package com.webservices.restfulwebservices.model;

import java.io.Serializable;

public class HelloBean implements Serializable {
     private String message;
    public HelloBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
