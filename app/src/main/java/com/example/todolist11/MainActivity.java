package com.example.todolist11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // private RecyclerView recyclerViewNote;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private NotesAdapter notesAdapter;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(getApplication()); ///
        initViews();
        notesAdapter = new NotesAdapter();

        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNotes(notes);
            }
        });

        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target
                    ) {
                        return false;
                    }

                    @Override
                    public void onSwiped(
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            int direction
                    ) {
                        int position = viewHolder.getAdapterPosition();
                        Note note = notesAdapter.getNotes().get(position);
                        viewModel.remove(note);
                    }
                }
        );
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(notesAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        showNotes();
//    }

    protected void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.buttonAddNote);
    }

//    protected void showNotes() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Note> notes = noteDatabase.notesDao().getNotes();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        notesAdapter.setNotes(noteDatabase.notesDao().getNotes());
//
//                    }
//                });
//            }
//        });
//        thread.start();
//    }
}