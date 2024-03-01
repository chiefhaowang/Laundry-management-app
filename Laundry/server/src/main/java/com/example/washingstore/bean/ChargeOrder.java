package com.example.washingstore.bean;

public class ChargeOrder {
    private String id;
    private String clerkname;
    private String clerkphone;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public String getClerkphone() {
        return clerkphone;
    }

    public void setClerkphone(String clerkphone) {
        this.clerkphone = clerkphone;
    }
}
