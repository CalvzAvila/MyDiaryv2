package com.project.diary.MyDiary.models;

public class GetDataModel {

    private int id;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public GetDataModel(int id, String firstname, String lastname, String phone) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    private String firstname;
    private String lastname;
    private String phone;
}
