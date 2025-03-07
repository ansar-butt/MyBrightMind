package com.example.mybrightmind;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterModule extends RecyclerView.Adapter<CustomAdapterModule.ViewHolder> {
    private final ArrayList<String> mTopicList;
    private final ArrayList<Integer> mResources;

    public CustomAdapterModule(ArrayList<String> topics, ArrayList<Integer> resources) {
        mTopicList = topics;
        mResources = resources;
    }

    @NonNull
    @Override
    public CustomAdapterModule.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item, parent, false);
        return new CustomAdapterModule.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mTopicList.get(position));
        holder.getTopicImage().setImageResource(mResources.get(position));
        holder.getButton().setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, moduleActivity.class);
            intent.putExtra("lessonName", mTopicList.get(position));
            context.startActivity(intent);
        });
        Button b = holder.getButton();
        b.setBackgroundColor( b.getResources().getColor(position%2==0?R.color.buttonEven:R.color.buttonOdd));
        holder.getCardView().setCardBackgroundColor(b.getResources().getColor(position%2==0?R.color.cardEven:R.color.cardOdd));
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView topicName;
        ImageView topicImage;
        Button topicButton;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            topicImage = itemView.findViewById(R.id.topicImage);
            topicName = itemView.findViewById(R.id.topic);
            topicButton = itemView.findViewById(R.id.startLearning);
            cardView = itemView.findViewById(R.id.itemCard);

        }

        CardView getCardView(){
            return cardView;
        }

        TextView getTextView() {
            return topicName;
        }

        ImageView getTopicImage() {
            return topicImage;
        }

        Button getButton() {
            return topicButton;
        }
    }

}
