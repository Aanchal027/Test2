package com.androidjson.sqlitelogin_androidjsoncom.entity;
public class User {

    private byte[] bytes;
    private String id, email, password, name;
    private boolean active;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    public User() {
    }

    public User(boolean active) {
        this.active = active;
    }

    public User(String id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String id, String email, String password, byte[] bytes, String name) {
        this.bytes = bytes;
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getId() {
        return id;
    }

    public String getemail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
