package ru.geeekbrains.mynoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ru.geeekbrains.mynoteapp.domain.Note;
import ru.geeekbrains.mynoteapp.ui.NoteDetailActivity;
import ru.geeekbrains.mynoteapp.ui.NoteDetailFragment;
import ru.geeekbrains.mynoteapp.ui.NoteListFragment;

/*
1. Подумайте о функционале вашего приложения заметок. Какие экраны там могут быть, помимо основного со списком заметок?
Как можно использовать меню и всплывающее меню в вашем приложении? Не обязательно сразу пытаться реализовать весь этот
функционал, достаточно создать макеты и структуру, а реализацию пока заменить на заглушки или всплывающие уведомления (Toast).
Используйте подход Single Activity для отображения экранов.

2. Создайте боковое навигационное меню для своего приложения и добавьте туда хотя бы один экран,
например «Настройки» или «О приложении».

3. * Создайте полноценный заголовок для NavigationDrawer’а. К примеру, аватарка пользователя,
его имя и какая-то дополнительная информация.*/

public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteClicked {

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_fragment, new NoteListFragment(), "NoteListFragment")
                .commit();
    }

    @Override
    public void onNoteClicked(Note note) {

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isLandscape){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_fragment, NoteDetailFragment.newInstance(note))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_fragment, NoteDetailFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }
    }
}