package ru.geeekbrains.mynoteapp.domain;

import java.util.List;

public interface NoteRepository {

    void getNotes(Callback<List<Note>> callback);;

    Note addNote(String head, String body);

    void removeNote (Note note);
}
