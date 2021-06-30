package ru.geeekbrains.mynoteapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public interface OnNoteClickedListener{
        void onNoteClickedListener(@NonNull Note note);
    }

    public interface OnNoteLongClickedListener{
        void onNoteLongClickedListener(@NonNull Note note, int index);
    }

    private final List<Note> notes = new ArrayList<>();
    private final Fragment fragment;
    private OnNoteClickedListener listener;

    private OnNoteLongClickedListener listenerLong;

    public OnNoteClickedListener getListener() {
        return listener;
    }
    public OnNoteLongClickedListener getListenerLong() {
        return listenerLong;
    }

    public void setListenerLong(OnNoteLongClickedListener listenerLong) {
        this.listenerLong = listenerLong;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    public NoteAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setData(List<Note> toSet){
        notes.clear();
        notes.addAll(toSet);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {

        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void remove(Note note) {
        notes.remove(note);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteHead;
        TextView noteDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(v -> {
               if (getListener() != null){
                   getListener().onNoteClickedListener(notes.get(getAdapterPosition()));
               }
            });

            itemView.setOnLongClickListener(v -> {
                itemView.showContextMenu();
                if (getListenerLong() != null){
                    int index = getAdapterPosition();
                    getListenerLong().onNoteLongClickedListener(notes.get(index), index);
                }
                return true;
            });
        }

        public void bind(Note note) {

            noteHead = itemView.findViewById(R.id.head);
            noteDate = itemView.findViewById(R.id.date);

            noteHead.setText(note.getHead());
            noteDate.setText(note.getDate().toString());
        }

    }


}
