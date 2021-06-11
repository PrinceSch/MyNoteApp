package ru.geeekbrains.mynoteapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Note;
import ru.geeekbrains.mynoteapp.domain.NoteRepositoryImpl;


public class NoteListFragment extends Fragment {

    NoteRepositoryImpl noteRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRepository = new NoteRepositoryImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout notesList = view.findViewById(R.id.note_list_container);

        List<Note> notes = noteRepository.getNotes();

        for (Note note: notes){
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.note_head, notesList,false);

            TextView noteHead = itemView.findViewById(R.id.note_head_view);
            noteHead.setText(note.getHead());

            notesList.addView(itemView);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}