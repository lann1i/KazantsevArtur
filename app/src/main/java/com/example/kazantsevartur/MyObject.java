package com.example.kazantsevartur;

import java.io.Serializable;

public class MyObject implements Serializable {
    private String name;
    private int age;
    private String group;
    private int score;

    public MyObject(String name, String group, int age, int score) {
        this.name=name;
        this.group=group;
        this.age=age;
        this.score=score;
    }
    public String getName() {
        return name;
    }
    public String getGroup() {
        return group;
    }
    public int getAge() {
        return age;
    }
    public int getScore() {
        return score;
    }
}
