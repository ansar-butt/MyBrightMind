package com.example.mybrightmind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapterModule extends RecyclerView<customAdapterModule.ViewHolder> {
    private final ArrayList<String> mTopicList;

    public customAdapterModule(ArrayList<String> topics) {
        mTopicList = topics;
    }

    @NonNull
    @Override
    public customAdapterModule.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item, parent, false);
        return new customAdapterModule.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mTopicList.get(position));
        holder.getTopicImage().setImageDrawable(mBookingList.get(position).getBookingEnd());
        holder.getButton();
    }

    @Override
    public int getItemCount() {
        return mBookingList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView topicName;
        ImageView topicImage;
        Button topicButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            topicImage = itemView.findViewById(R.id.topicImage);
            topicName = itemView.findViewById(R.id.topic);
            topicButton = itemView.findViewById(R.id.startLearning);

            topicButton.setOnClickListener(v->goToTopic());
        }

        private void goToTopic() {

        }

        TextView getTextView() {
            return topicName;
        }

        ImageView getTopicImage(){
            return  topicImage;
        }

        Button getButton() {
            return topicButton;
        }
    }

}
