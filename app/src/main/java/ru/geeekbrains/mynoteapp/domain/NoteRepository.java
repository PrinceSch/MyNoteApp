package ru.geeekbrains.mynoteapp.domain;

import java.util.List;

public interface NoteRepository {

    void getNotes(Callback<List<Note>> callback);;

    void addNote(String head, String body, Callback<Note> callback);

    void removeNote (Note note, Callback<Object> callback);
}
