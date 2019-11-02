package com.kubatov.note.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.kubatov.note.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewNoteCreateActivity extends AppCompatActivity {
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
        switch (item.getItemId()) {
            case R.id.note_save:
                saveNoteToFireBase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNoteToFireBase(){

    }
}
