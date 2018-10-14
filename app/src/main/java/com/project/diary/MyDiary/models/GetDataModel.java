package com.project.diary.MyDiary.models;

public class GetDataModel {

    private int id;
    private String firstname;
    private String lastname;


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }


    public GetDataModel(int id, String firstname, String lastname) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
