package com.example.todolist11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
    private LinearLayout linearLayout;
    private FloatingActionButton button;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

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
        linearLayout = findViewById(R.id.recyclerViewNote);
        button = findViewById(R.id.buttonAddNote);
    }

    protected void showNotes() {
        linearLayout.removeAllViews();
        for (Note note : database.getNotes()) {
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayout,
                    false
            );
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    database.remove(note.getId());
                    showNotes();
                }
            });

            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            int colorResId;
            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_dark;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_dark;
                    break;
                default:
                    colorResId = android.R.color.holo_red_dark;
                    break;
            }
            int color = ContextCompat.getColor(this, colorResId);
            textViewNote.setBackgroundColor(color);
            linearLayout.addView(view);
        }
    }
}
