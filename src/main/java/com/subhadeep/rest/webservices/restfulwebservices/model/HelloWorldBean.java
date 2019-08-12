package com.subhadeep.rest.webservices.restfulwebservices.model;

public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = "Hello World, " + message;
    }

    public HelloWorldBean() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String helloWorld) {
        this.message = helloWorld;
    }
}
