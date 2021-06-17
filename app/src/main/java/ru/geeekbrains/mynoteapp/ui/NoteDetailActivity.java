package ru.geeekbrains.mynoteapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Note;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        if (savedInstanceState == null){

            Note note = getIntent().getParcelableExtra(ARG_NOTE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoteDetailFragment.newInstance(note))
                    .commit();
        }
    }
}