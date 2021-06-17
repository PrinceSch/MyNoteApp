package ru.geeekbrains.mynoteapp.domain;

import java.util.ArrayList;
import java.util.List;

import ru.geeekbrains.mynoteapp.R;

public class NoteRepositoryImpl implements NoteRepository {

    @Override
    public List<Note> getNotes(){
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note(R.string.firstHead, R.string.firstBody, "2020-05-1"));
        result.add(new Note(R.string.secondHead, R.string.secondBody, "2020-05-2"));
        result.add(new Note(R.string.thirdHead, R.string.thirdBody, "2020-05-3"));
        result.add(new Note(R.string.fourthHead, R.string.fourthBody, "2020-05-4"));
        result.add(new Note(R.string.fifthHead, R.string.fifthBody, "2020-05-5"));

        return result;
    }

}
