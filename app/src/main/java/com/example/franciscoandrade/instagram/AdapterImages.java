package com.example.franciscoandrade.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.franciscoandrade.instagram.UnsplashPOJO.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ImagesViewHolder> {
    List<Result> list;
    Context context;

    public AdapterImages( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_item_view, parent, false);

        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, final int position) {
        String url=list.get(position).getUrls().getRegular().toString();
        Picasso.with(context).load(url)
                .resize(400, 400)
                .centerCrop()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent= new Intent(context, CollapsingActivity.class);
//                intent.putExtra("imageUrl", list.get(position).getUrls().getRegular());
//                intent.putExtra("imageColor", list.get(position).getColor());
//                intent.putExtra("imageHeight", list.get(position).getHeight());
//                intent.putExtra("imageLikes", list.get(position).getLikes());
//                intent.putExtra("imageWidth", list.get(position).getWidth());
//                intent.putExtra("imageUserName", list.get(position).getUser().getUsername());
//                intent.putExtra("imageUserTwiter", list.get(position).getUser().getTwitter_username());
//                context.startActivity(intent);

//                    Intent intent= new Intent(context, CollapsingActivity.class);
//                    context.startActivity(intent);

            }
        });

        Log.d("SIZE==", "onBindViewHolder: "+holder.imageView.getWidth());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addImages(List<Result> rootObject) {

        for (Result result:rootObject) {
            list.add(result);
        }
//        list =new List<Result>(Arrays.asList(rootObject));

        notifyDataSetChanged();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ImagesViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
