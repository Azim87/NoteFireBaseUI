package com.kubatov.note;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnItemClickListener {
    void onItemClickListener(DocumentSnapshot snapshot, int position);
}
