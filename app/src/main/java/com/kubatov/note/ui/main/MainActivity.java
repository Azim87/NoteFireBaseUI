package com.kubatov.note.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kubatov.note.OnItemClickListener;
import com.kubatov.note.R;
import com.kubatov.note.adapter.NoteAdapter;
import com.kubatov.note.pojo.NoteModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = dataBase.collection("Notebook");
    private NoteAdapter mNoteAdapter;

    @BindView(R.id.note_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fabAddNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpRecyclerViewQuery();
    }

    private void setUpRecyclerViewQuery() {
        Query query = collectionReference.orderBy("priority", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<NoteModel> options = new FirestoreRecyclerOptions.Builder<NoteModel>()
                .setQuery(query, NoteModel.class)
                .build();
        mNoteAdapter = new NoteAdapter(options);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mNoteAdapter);
        deleteOnSwipe();
    }

    private void deleteOnSwipe() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mNoteAdapter.deleteNoteItem(viewHolder.getAdapterPosition());
                Toast.makeText(MainActivity.this, "note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        mNoteAdapter.setOnItemClickListener((snapshot, position) -> {
            NoteModel note = snapshot.toObject(NoteModel.class);
            if(note != null)
            Toast.makeText(MainActivity.this, "" + note.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNoteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNoteAdapter.stopListening();
    }

    @OnClick(R.id.fab)
    void addNote(View view) {
        NewNoteCreateActivity.start(this);
    }
}
