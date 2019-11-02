package com.kubatov.note.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kubatov.note.R;
import com.kubatov.note.pojo.NoteModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewNoteCreateActivity extends AppCompatActivity {
    private View view;


    @BindView(R.id.edit_text_title) EditText editTextTitle;
    @BindView(R.id.edit_text_description) EditText editTextDescription;
    @BindView(R.id.number_picker_priority) NumberPicker numberPickerPriority;

    public static void start(Context context){
        context.startActivity(new Intent(context, NewNoteCreateActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_create);
        ButterKnife.bind(this);
        setNumberPickerValue();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("add note");
    }

    private void setNumberPickerValue() {
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.note_save) {
            saveNoteToFireBase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNoteToFireBase(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        checkForValidation(title, description, priority);
    }

    private void checkForValidation(String title, String description, int priority) {
        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("Notebook");
        collectionReference.add(new NoteModel(title, description, priority));
        Toast.makeText(this, "Note added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
