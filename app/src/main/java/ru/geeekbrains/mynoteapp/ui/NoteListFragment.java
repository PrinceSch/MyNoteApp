package ru.geeekbrains.mynoteapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.geeekbrains.mynoteapp.MainActivity;
import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Callback;
import ru.geeekbrains.mynoteapp.domain.MainRouter;
import ru.geeekbrains.mynoteapp.domain.Note;
import ru.geeekbrains.mynoteapp.domain.NoteRepository;
import ru.geeekbrains.mynoteapp.domain.NotesFirestoreRepository;
import ru.geeekbrains.mynoteapp.domain.RouterHolder;


public class NoteListFragment extends Fragment {

    public interface OnNoteClicked{
        void onNoteClicked(Note note);
    }

    static NoteRepository noteRepository;
    static NoteAdapter noteAdapter;
    private OnNoteClicked onNoteClicked;
    public final static String TAG = "NoteListFragment";

    private int longClickedIndex;
    private Note longClickedNote;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked){
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onNoteClicked = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteAdapter = new NoteAdapter(this);

        noteRepository = NotesFirestoreRepository.INSTANCE;
        noteRepository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> result) {
                noteAdapter.setData(result);
                noteAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView notesList = view.findViewById(R.id.recycler_view_notes);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext()));

        notesList.setAdapter(noteAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        notesList.addItemDecoration(itemDecoration);

        noteAdapter.setListener(note -> {
            if (requireActivity() instanceof RouterHolder){
                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                router.showDetail(note);
            }
        });

        noteAdapter.setListenerLong((note, index) -> {
            longClickedNote = note;
            longClickedIndex = index;
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add){
            if (requireActivity() instanceof RouterHolder){
                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                router.showDetail(longClickedNote);
            }
            return true;
        }
        if (item.getItemId() == R.id.menu_update){
            noteAdapter.notifyDataSetChanged();
            return true;
        }

        if (item.getItemId() == R.id.menu_delete){
            noteRepository.removeNote(longClickedNote);
            noteAdapter.remove(longClickedNote);
            noteAdapter.notifyItemRemoved(longClickedIndex);
            return true;
        }

        return super.onContextItemSelected(item);
    }
}