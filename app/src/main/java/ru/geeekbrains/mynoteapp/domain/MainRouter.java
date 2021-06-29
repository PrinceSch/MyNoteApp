package ru.geeekbrains.mynoteapp.domain;

import androidx.fragment.app.FragmentManager;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.ui.AboutFragment;
import ru.geeekbrains.mynoteapp.ui.AddNoteFragment;
import ru.geeekbrains.mynoteapp.ui.NoteDetailFragment;
import ru.geeekbrains.mynoteapp.ui.NoteListFragment;

public class MainRouter {

    private final FragmentManager fragmentManager;


    public MainRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotes(){
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_fragment, new NoteListFragment(), NoteListFragment.TAG)
                .commit();
    }

    public void addNote(){
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_fragment, new AddNoteFragment(), AddNoteFragment.TAG)
                .addToBackStack(AddNoteFragment.TAG)
                .commit();
    }

    public void showAbout(){
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_fragment, new AboutFragment(), AboutFragment.TAG)
                .addToBackStack(AboutFragment.TAG)
                .commit();
    }

    public void showDetail(boolean L, Note note){
        if (L){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.notes_detail_fragment, NoteDetailFragment.newInstance(note))
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.notes_fragment, NoteDetailFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showDetail(Note note){
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_fragment, NoteDetailFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

}
