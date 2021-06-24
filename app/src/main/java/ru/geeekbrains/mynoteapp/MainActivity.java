package ru.geeekbrains.mynoteapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.geeekbrains.mynoteapp.domain.Note;
import ru.geeekbrains.mynoteapp.ui.AboutFragment;
import ru.geeekbrains.mynoteapp.ui.AddNoteFragment;
import ru.geeekbrains.mynoteapp.ui.NoteDetailFragment;
import ru.geeekbrains.mynoteapp.ui.NoteListFragment;


public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteClicked {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatImageButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_fragment, new AddNoteFragment(), "New Note")
                .addToBackStack(null)
                .commit());

        DrawerLayout drawerLayout = findViewById(R.id.main_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_fragment, new NoteListFragment(), "NoteListFragment")
                .commit();

        NavigationView navigationView = findViewById(R.id.menu_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);

            if(item.getItemId() == R.id.new_note){
                Toast.makeText(getApplicationContext(), R.string.create_new_note, Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.favourites){
                Toast.makeText(getApplicationContext(), R.string.favourite_notes, Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.settings){
                Toast.makeText(getApplicationContext(), R.string.settings, Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.about){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.notes_fragment, new AboutFragment(), "AboutFragment")
                        .addToBackStack(null)
                        .commit();
                return true;
            }

            return false;
        });
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