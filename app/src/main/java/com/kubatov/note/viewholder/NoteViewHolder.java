package com.kubatov.note.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.note.R;
import com.kubatov.note.pojo.NoteModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view_title) public TextView titleView;
    @BindView(R.id.text_view_description) public TextView descriptionView;
    @BindView(R.id.text_view_priority) public TextView priorityView;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }
}
