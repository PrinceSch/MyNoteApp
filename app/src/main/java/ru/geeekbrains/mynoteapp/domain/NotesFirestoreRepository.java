package ru.geeekbrains.mynoteapp.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ru.geeekbrains.mynoteapp.MainActivity;

public class NotesFirestoreRepository implements NoteRepository {

    public static final NoteRepository INSTANCE = new NotesFirestoreRepository();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final static String NOTES = "notes";
    private final static String HEAD = "head";
    private final static String BODY = "body";
    private final static String DATE = "date";
    private final static String FAV = "favourite";

    @Override
    public void getNotes(Callback<List<Note>> callback) {
        firebaseFirestore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            ArrayList<Note> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()){
                                String head = (String) document.get(HEAD);
                                String body = (String) document.get(BODY);
                                String date = (String) document.get(DATE);
                                result.add(0, new Note(head, body, date));
                            }

                            callback.onSuccess(result);
                        } else {
                            task.getException();
                        }
                    }
                });
    }

    @Override
    public Note addNote(String head, String body) {
        return null;
    }

    @Override
    public void removeNote(Note note) {

    }
}
