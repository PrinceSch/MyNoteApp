package ru.geeekbrains.mynoteapp.domain;

import androidx.annotation.StringRes;

public class Note {

    @StringRes
    private int head;
    @StringRes
    private int body;
    private String date;
    private boolean favourite = false;

    public Note(int head, int body, String date) {
        this.head = head;
        this.body = body;
        this.date = date;
    }

    public int getHead() {
        return head;
    }

    public int getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
