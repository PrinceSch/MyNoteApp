package ru.geeekbrains.mynoteapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Note;

public class NoteDetailFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NoteDetailFragment newInstance (Note note){
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    public NoteDetailFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView noteHead = view.findViewById(R.id.note_head);
        TextView noteDate = view.findViewById(R.id.note_date);
        TextView noteBody = view.findViewById(R.id.note_body);

        Note note = getArguments().getParcelable(ARG_NOTE);
        noteHead.setText(note.getHead());
        noteDate.setText(note.getDate());
        noteBody.setText(note.getBody());
    }
}