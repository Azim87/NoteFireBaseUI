/*
package com.kubatov.note.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.kubatov.note.OnItemClickListener;
import com.kubatov.note.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private DocumentSnapshot documentSnapshot;
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
            if (position != RecyclerView.NO_POSITION && listener != null){

            }
        });
    }
}
*/
