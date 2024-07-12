package com.trycatch_vishal.fooddelivery;

public class UserModel {
    private String name, email, pass, conf;

    public UserModel() {
    }

    public UserModel(String name, String email, String pass, String conf) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.conf = conf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
}
