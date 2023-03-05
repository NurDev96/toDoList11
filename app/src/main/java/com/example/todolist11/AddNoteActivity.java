package com.example.todolist11;

import android.content.Context;
import android.content.Intent;
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        setContentView(R.layout.add_note);
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

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }
}

