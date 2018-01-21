package com.example.franciscoandrade.instagram.container;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.franciscoandrade.instagram.R;
import com.example.franciscoandrade.instagram.jsonAccess.Data;
import com.example.franciscoandrade.instagram.jsonAccess.Datum;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murodjon.rahimov on 1/17/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

  ArrayList<Datum> cards;

  public SearchAdapter(ArrayList<Datum> cards) {
    this.cards = cards;
  }

  @Override
  public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.cardview_grid_contact, parent, false);
    return new SearchAdapter.SearchViewHolder(view);
  }

  @Override
  public void onBindViewHolder(SearchViewHolder holder, int position) {

      String url = cards.get(position)
        .getImages()
        .getStandard_resolution()
        .getUrl();
      Log.d("VIEWHOLDER", "onBindViewHolder: " + cards.get(position)
        .toString());

      if (cards.get(position)
        .getCaption() == null) {

        holder.textView.setText("No Caption");
      } else {
        holder.textView.setText(cards.get(position)
          .getCaption()
          .getText());
      }

      Picasso.with(holder.imageView.getContext())
        .load(url)
        .into(holder.imageView);


  }

  @Override
  public int getItemCount() {
    return cards.size();
  }

  public class SearchViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public SearchViewHolder(View itemView) {
      super(itemView);

      imageView = (ImageView) itemView.findViewById(R.id.imageView);
      textView = (TextView) itemView.findViewById(R.id.textView);
    }
  }
}