package com.example.franciscoandrade.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.jsonAccess.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    ArrayList<Datum> cards;
    Context context;

    public CardAdapter(ArrayList<Datum> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_contact, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
            String url= cards.get(position).getImages().getStandard_resolution().getUrl();
        Log.d("VIEWHOLDER", "onBindViewHolder: "+cards.get(position).toString());

            if (cards.get(position).getCaption() == null){

                 holder.textView.setText("No Caption");

            }
            else {
                holder.textView.setText(cards.get(position).getCaption().getText());
            }


            Picasso.with(context).load(url).into(holder.imageView);

//            TODO: add feature when click in image you get to seee details & comments in a new activity

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;


        public CardViewHolder(View itemView) {
            super(itemView);

            imageView= (ImageView)itemView.findViewById(R.id.imageView);
            textView= (TextView) itemView.findViewById(R.id.textView);


        }
    }
}
