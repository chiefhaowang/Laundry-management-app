package com.example.washingstore.bean;

public class User {
    private String id;
    private String email;
    private String password;
    private String username;
    private String address;
    private int role;

    public User(String s, String s1, String s2, String s3, String s4, int i) {
        this.id=s;
        this.email=s1;
        this.password=s2;
        this.username=s3;
        this.address=s4;
        this.role=i;
    }

    public String  getId(){
        return id;
    }
    public void setId(String  id){
        this.id=id;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
}