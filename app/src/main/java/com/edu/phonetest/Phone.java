package com.edu.phonetest;

public class Phone {
    private int id;
    private String phonename;
    private String password;
    public Phone() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Phone(String phonename, String password) {
        super();
        this.phonename = phonename;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phonename='" + phonename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
