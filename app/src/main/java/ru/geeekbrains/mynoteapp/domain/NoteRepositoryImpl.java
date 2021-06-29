package ru.geeekbrains.mynoteapp.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.geeekbrains.mynoteapp.R;

public class NoteRepositoryImpl implements NoteRepository {

    private final ArrayList<Note> result = new ArrayList<>();

    public NoteRepositoryImpl() {
        result.add(0, new Note("First Head", "First Body", "2020-05-1"));
        result.add(0, new Note("Second Head", "Second Body", "2020-05-2"));
        result.add(0, new Note("Third Head", "Third Body", "2020-05-3"));
        result.add(0, new Note("Fourth Head Head", "Fourth Head Body", "2020-05-4"));
        result.add(0, new Note("Fifth Head Head", "Fifth Head Body", "2020-05-5"));
        result.add(0, new Note("Sixth Head", "Sixth Body", "2020-05-6"));
        result.add(0, new Note("Seventh Head", "Seventh Body", "2020-05-7"));
        result.add(0, new Note("Eighth Head", "Eighth Body", "2020-05-8"));
        result.add(0, new Note("Ninth Head", "Ninth Body", "2020-05-9"));
        result.add(0, new Note("Tenth Head", "Tenth Body", "2020-05-10"));
        result.add(0, new Note("Eleventh Head", "Eleventh Body", "2020-05-11"));
        result.add(0, new Note("Twelfth Head", "Twelfth Body", "2020-05-12"));
    }

    @Override
    public List<Note> getNotes(){
        return result;
    }

    @Override
    public Note addNote(String head, String body) {
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Note note = new Note(head, body, formatDate.format(date));
        result.add(0,note);
        return note;
    }

    @Override
    public void removeNote(Note note) {
        result.remove(note);
    }

}
