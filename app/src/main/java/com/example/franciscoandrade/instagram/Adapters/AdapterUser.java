package com.example.franciscoandrade.instagram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.ImageInfoActivity;
import com.example.franciscoandrade.instagram.R;
import com.example.franciscoandrade.instagram.pojoUserInfo.DataUSer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscoandrade on 1/22/18.
 */

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder> {
    Context context;
    List<DataUSer> dataUserList;
    String token;


    public AdapterUser(Context context) {
         token="";
        dataUserList= new ArrayList<>();
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_contact, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        String url= dataUserList.get(position).getImages().getStandard_resolution().getUrl();
        Log.d("VIEWHOLDER", "onBindViewHolder: "+dataUserList.get(position).toString());

        if (dataUserList.get(position).getCaption() == null){

            holder.textView.setText("No Caption");

        }
        else {
            holder.textView.setText(dataUserList.get(position).getCaption().getText());
        }


        Picasso.with(context).load(url).into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ImageInfoActivity.class);
                intent.putExtra("imageUrl", dataUserList.get(position).getImages().getStandard_resolution().getUrl().toString());
                if (dataUserList.get(position).getCaption() == null){

                    intent.putExtra("caption", "No Caption");

                }
                else {
                    intent.putExtra("caption", dataUserList.get(position).getCaption().getText().toString());
                }
                intent.putExtra("comments", dataUserList.get(position).getId());
                intent.putExtra("token", token);
                context.startActivity(intent);
                Log.d("TOKENAFTER", "onClick: "+token);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataUserList.size();
    }

    public void addImages(List<DataUSer> rootObject, String token1) {

        dataUserList.addAll(rootObject);
        notifyDataSetChanged();
        token=token1;
       // this.token=token;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public UserViewHolder(View itemView) {
            super(itemView);

            imageView= (ImageView)itemView.findViewById(R.id.imageView);
            textView= (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
