package com.example.wordchaingame;

public class User {
    private String id;
    private String password;
    private String name;

    private int h_score;

    public User(String id, String password, String name, int h_score) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.h_score = h_score;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getH_score() {
        return h_score;
    }

    public void setH_score(int h_score) {
        this.h_score = h_score;
    }
}
