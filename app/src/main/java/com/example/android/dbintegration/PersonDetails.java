package com.example.android.dbintegration;

import java.io.Serializable;

/**
 * Created by simranjain1507 on 03/06/17.
 */

public class PersonDetails implements Serializable{
    String name, email,gender,area;
    String phone;
    int id;

    public PersonDetails(){

    }

    public PersonDetails(String name, String emial, String phone, String gender, String area){
        this.name=name;
        this.email=emial;
        this.gender=gender;
        this.phone=phone;
        this.area=area;
    }
    public PersonDetails(int id,String name, String emial, String phone, String gender, String area){
        this.name=name;
        this.email=emial;
        this.id=id;
        this.gender=gender;
        this.phone=phone;
        this.area=area;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
