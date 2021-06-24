package ru.geeekbrains.mynoteapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String head;
    private String body;
    private String date;
    private boolean favourite = false;

    public Note(String head, String body, String date) {
        this.head = head;
        this.body = body;
        this.date = date;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    protected Note(Parcel in) {
        head = in.readString();
        body = in.readString();
        date = in.readString();
        favourite = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeString(body);
        dest.writeString(date);
        dest.writeByte((byte) (favourite ? 1 : 0));
    }
}
