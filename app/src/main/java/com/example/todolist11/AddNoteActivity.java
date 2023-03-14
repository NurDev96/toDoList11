package com.example.todolist11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton rb_low;
    private RadioButton rb_medium;
    private Button btn_save;

    private NoteDatabase noteDatabase;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        noteDatabase = NoteDatabase.getInstance(getApplication());

        initViews();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        rb_low = findViewById(R.id.rb_low);
        rb_medium = findViewById(R.id.rb_medium);
        btn_save = findViewById(R.id.btn_save);
    }

    private void saveNote() {
        String text = editTextNote.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(text, priority);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDatabase.notesDao().add(note);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                });
            }
        });
        thread.start();
    }

    private int getPriority() {
        int priority;
        if (rb_low.isChecked()) {
            priority = 0;
        } else if (rb_medium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}
