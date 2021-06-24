package ru.geeekbrains.mynoteapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.NoteRepositoryImpl;

public class AddNoteFragment extends Fragment {

    NoteRepositoryImpl repository = NoteListFragment.noteRepository;

    public AddNoteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText head = view.findViewById(R.id.note_head_edit);
        EditText body = view.findViewById(R.id.note_body_edit);
        Button addButton = view.findViewById(R.id.button_add);

        addButton.setOnClickListener(v -> {
            repository.addNote(String.valueOf(head.getText()), String.valueOf(body.getText()));
            getActivity().getSupportFragmentManager().popBackStack();
        });

    }
}