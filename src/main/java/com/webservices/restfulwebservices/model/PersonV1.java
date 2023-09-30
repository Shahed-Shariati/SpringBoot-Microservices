package com.webservices.restfulwebservices.model;

public class PersonV1 {

    private String name;
    public PersonV1(String fullName) {
        this.name = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
