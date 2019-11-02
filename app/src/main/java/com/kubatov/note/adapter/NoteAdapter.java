package com.kubatov.note.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.kubatov.note.OnItemClickListener;
import com.kubatov.note.R;
import com.kubatov.note.pojo.NoteModel;


import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteAdapter extends FirestoreRecyclerAdapter<NoteModel, NoteAdapter.NoteViewHolder> {
    private OnItemClickListener mListener;

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

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener listener;
        @BindView(R.id.text_view_title)
        public TextView titleView;
        @BindView(R.id.text_view_description)
        public TextView descriptionView;
        @BindView(R.id.text_view_priority)
        public TextView priorityView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mListener != null){
                    mListener.onItemClickListener(getSnapshots().getSnapshot(position), position);
                }
            });
        }
    }

    public void deleteNoteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
