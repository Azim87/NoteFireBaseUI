package com.kubatov.note.pojo;

import androidx.annotation.NonNull;

public class NoteModel {
    private String title;
    private String description;
    private int priority;

    public NoteModel() {

    }

    public NoteModel(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + description + " " + priority;
    }
}
