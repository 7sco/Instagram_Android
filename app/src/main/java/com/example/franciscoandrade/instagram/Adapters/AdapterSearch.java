package com.example.franciscoandrade.instagram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.CropCircleTransformation;
import com.example.franciscoandrade.instagram.R;
import com.example.franciscoandrade.instagram.UserActivity;
import com.example.franciscoandrade.instagram.jsonUserSearch.DatumSearch;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.SearchViewHolder> {
    Context context;
    List<DatumSearch> listSearch;

    public AdapterSearch(Context context) {
        listSearch = new ArrayList<>();
        this.context = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_layout, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        String imageUrl= listSearch.get(position).getProfile_picture().toString();
        Picasso.with(context).load(imageUrl)
                .transform(new CropCircleTransformation()).into(holder.profile_image);
        holder.userNameTV.setText(listSearch.get(position).getUsername());
        holder.realNameTV.setText(listSearch.get(position).getfull_name());

        holder.searchLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLIKED++=", "onClick: "+listSearch.get(position).getId());
                Intent intent= new Intent(context, UserActivity.class);
                intent.putExtra("id", listSearch.get(position).getId().toString());
                intent.putExtra("username", listSearch.get(position).getUsername());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listSearch.size();
    }

    public void addImages(List<DatumSearch> rootObject) {
        listSearch=new ArrayList<>();
        listSearch.addAll(rootObject);
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView userNameTV, realNameTV;
        LinearLayout searchLL;
        public SearchViewHolder(View itemView) {
            super(itemView);
            profile_image=(CircleImageView)itemView.findViewById(R.id.profile_image);
            userNameTV=(TextView) itemView.findViewById(R.id.userNameTV);
            realNameTV=(TextView) itemView.findViewById(R.id.realNameTV);
            searchLL=(LinearLayout)itemView.findViewById(R.id.searchLL);
        }
    }
}
