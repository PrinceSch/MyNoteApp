package ru.geeekbrains.mynoteapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.geeekbrains.mynoteapp.R;
import ru.geeekbrains.mynoteapp.domain.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final List<Note> notes = new ArrayList<>();

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
        holder.noteHead.setText(note.getHead());
        holder.noteDate.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteHead;
        TextView noteDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            noteHead = itemView.findViewById(R.id.head);
            noteDate = itemView.findViewById(R.id.date);
        }
    }


}
