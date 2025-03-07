package com.example.mybrightmind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterLesson extends RecyclerView.Adapter<CustomAdapterLesson.ViewHolder> {
    private final ArrayList<String> mLessonTitle;
    private final ArrayList<String> mDescriptionList;

    public CustomAdapterLesson(ArrayList<String> titles, ArrayList<String> descriptionList) {
        mLessonTitle = titles;
        mDescriptionList = descriptionList;
    }

    @NonNull
    @Override
    public CustomAdapterLesson.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item, parent, false);
        return new CustomAdapterLesson.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView(1).setText(mLessonTitle.get(position));
        holder.getTextView(2).setText(mDescriptionList.get(position));
        holder.getButton().setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, moduleActivity.class);
            intent.putExtra("lessonTitle", mLessonTitle.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mLessonTitle.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lessonTitle;
        TextView lessonDescription;
        Button lessonButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lessonTitle = itemView.findViewById(R.id.lesson_title);
            lessonButton = itemView.findViewById(R.id.start_lesson);
            lessonDescription = itemView.findViewById(R.id.lesson_description);
        }

        TextView getTextView(int i) {
            if (i == 1) return lessonTitle;
            return lessonDescription;
        }

        Button getButton() {
            return lessonButton;
        }
    }

}
