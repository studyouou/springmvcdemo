package org.ougen.application.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author: OuGen
 * Discription:
 * Date: 17:37 2019/7/19
 */
public class User {
    @NotNull
    private String name;

    @Size(max = 100,min = 10)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
