package ru.geeekbrains.mynoteapp.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


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
                .orderBy(DATE, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            ArrayList<Note> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()){

                                String head = (String) document.get(HEAD);
                                String body = (String) document.get(BODY);
                                Date date = ((Timestamp) document.get(DATE)).toDate();
                                result.add(0, new Note(document.getId(), head, body, date));
                            }

                            callback.onSuccess(result);
                        } else {
                            task.getException();
                        }
                    }
                });
    }

    @Override
    public void addNote(String head, String body, Callback<Note> callback) {
        Date date = new Date();

        HashMap<String, Object> data = new HashMap<>();
        data.put(HEAD, head);
        data.put(BODY, body);
        data.put(DATE, date);

        firebaseFirestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            Note note = new Note(task.getResult().getId().toString(),head, body, date);
                            callback.onSuccess(note);
                        }
                    }
                });
    }

    @Override
    public void removeNote(Note note, Callback<Object> callback) {
        firebaseFirestore.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            callback.onSuccess(note);
                        }
                    }
                });
    }

}
