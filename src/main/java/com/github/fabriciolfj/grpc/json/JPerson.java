package com.github.fabriciolfj.grpc.json;

public class JPerson {

    private String name;
    private Integer age;

    public JPerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public JPerson() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
