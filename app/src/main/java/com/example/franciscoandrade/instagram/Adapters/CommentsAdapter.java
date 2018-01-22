package com.example.franciscoandrade.instagram.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.JsonComments.Data;
import com.example.franciscoandrade.instagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by franciscoandrade on 1/21/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    Context context;
    List<Data> listComments;

    public CommentsAdapter(Context context) {
        listComments= new ArrayList<>();
        this.context = context;
    }

    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item_view, parent, false);

        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.CommentsViewHolder holder, int position) {

        holder.usernameCommentTV.setText(listComments.get(position).getFrom().getUsername());
        holder.messageTV.setText(listComments.get(position).getText());
        String url= listComments.get(position).getFrom().getProfile_picture();
        Picasso.with(context).load(url)
                .resize(400, 400)
                .centerCrop()
                .into(holder.userImageCommentIV);


        Log.d("LOADED", "onResponse: ********** ONVIEWHOLDER");


    }

    @Override
    public int getItemCount() {
        return listComments.size();
    }

    public void addImages(List<Data> rootObject) {
        listComments.addAll(rootObject);
        notifyDataSetChanged();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView userImageCommentIV;
        TextView usernameCommentTV, messageTV, likesTV;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            userImageCommentIV=(CircleImageView)itemView.findViewById(R.id.userImageCommentIV);
            usernameCommentTV=(TextView)itemView.findViewById(R.id.usernameCommentTV);
            messageTV=(TextView)itemView.findViewById(R.id.messageTV);
            likesTV=(TextView)itemView.findViewById(R.id.likesTV);

        }
    }
}
