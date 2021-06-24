package ru.geeekbrains.mynoteapp.domain;

import java.util.List;

public interface NoteRepository {

    List<Note> getNotes();

    Note addNote(String head, String body);
}
