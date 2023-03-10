package com.example.todolist11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // private RecyclerView recyclerViewNote;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private NotesAdapter notesAdapter;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        notesAdapter = new NotesAdapter();

        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                database.remove(note.getId());
                showNotes();
            }
        });

        recyclerView.setAdapter(notesAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    protected void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.buttonAddNote);
    }

    protected void showNotes() {
        notesAdapter.setNotes(database.getNotes());
    }
}
