package com.project.diary.MyDiary.models;

public class GetDataModel {
    private int id;
    private String title;
    private String contents;


    public GetDataModel(int id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
