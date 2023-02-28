package com.example.todolist11;

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
    }

    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        rb_low = findViewById(R.id.rb_low);
        rb_medium = findViewById(R.id.rb_medium);
        btn_save = findViewById(R.id.btn_save);
    }


}
