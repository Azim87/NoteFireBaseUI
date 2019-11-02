package com.kubatov.note.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.kubatov.note.R;
import com.kubatov.note.pojo.NoteModel;
import com.kubatov.note.viewholder.NoteViewHolder;

public class NoteAdapter extends FirestoreRecyclerAdapter<NoteModel, NoteViewHolder> {

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<NoteModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull NoteModel model) {
        holder.titleView.setText(model.getTitle());
        holder.descriptionView.setText(model.getDescription());
        holder.priorityView.setText(String.valueOf(model.getPriority()));
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(rootView);
    }
}
