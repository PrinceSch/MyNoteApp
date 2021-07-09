package ru.geeekbrains.mynoteapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    private String id;
    private String head;
    private String body;
    private Date date;
    private boolean favourite = false;

    public Note(String id, String head, String body, Date date) {
        this.id = id;
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
        id = in.readString();
        head = in.readString();
        body = in.readString();
        date = new Date(in.readLong());
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

    public Date getDate() {
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
        dest.writeString(id);
        dest.writeString(head);
        dest.writeString(body);
        dest.writeLong(date.getTime());
        dest.writeByte((byte) (favourite ? 1 : 0));
    }

    public String getId() {
        return id;
    }
}
