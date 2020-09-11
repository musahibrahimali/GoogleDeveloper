package com.example.googledeveloper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    ArrayList<User> learningLeaders;
    Context context;

    public MainRecyclerViewAdapter(ArrayList<User> learningLeaders, Context context) {
        this.learningLeaders = learningLeaders;
        this.context = context;
    }

    private static final String TAG = "Recycler View";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Setting all the necessary text to their respective views");
        User user = learningLeaders.get(position);
        holder.userName.setText(user.getUserName());
        String fullHours = user.getUserHours() + " " + user.getUserDetail() + " , " + user.getUserCountry();
        holder.userHours.setText(fullHours);
        holder.userImage.setImageResource(user.getUserImage());
    }

    @Override
    public int getItemCount() {
        return learningLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userHours;
        ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tvName);
            userHours = itemView.findViewById(R.id.tvHours);
            userImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
