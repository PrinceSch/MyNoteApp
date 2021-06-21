package ru.geeekbrains.mynoteapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    @StringRes
    private final int head;
    @StringRes
    private final int body;
    private final String date;
    private boolean favourite = false;

    public Note(int head, int body, String date) {
        this.head = head;
        this.body = body;
        this.date = date;
    }

    protected Note(Parcel in) {
        head = in.readInt();
        body = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(head);
        dest.writeInt(body);
        dest.writeString(date);
        dest.writeByte((byte) (favourite ? 1 : 0));
    }
}
