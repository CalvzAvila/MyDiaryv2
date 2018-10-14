package com.project.diary.MyDiary.models;

public class LoginModel {

    private int success;

    public LoginModel(int success, int user_id) {
        this.success = success;
        this.user_id = user_id;
    }

    public int getUser_id() {

        return user_id;
    }

    private int user_id;

    public int getSuccess() {
        return success;
    }

}
