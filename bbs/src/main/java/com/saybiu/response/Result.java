package com.saybiu.response;

public class Result {
    private Object  name;
    private Integer age;

    public Result() {
    }

    public Result(Object name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
