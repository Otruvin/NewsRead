package com.example.newsread.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsread.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PieceNewsAdapter extends RecyclerView.Adapter<PieceNewsAdapter.PieceNewsHolder> {

    private ArrayList<Article> dataList;
    private RecyclerClickListener recyclerClickListener;
    private Activity realTimeActivity;

    public PieceNewsAdapter(ArrayList<Article> dataList, RecyclerClickListener recyclerClickListener,
                            Activity realTimeActivity) {
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
        this.realTimeActivity = realTimeActivity;
    }

    @NonNull
    @Override
    public PieceNewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.piece_news_card, viewGroup, false);
        return new PieceNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceNewsHolder pieceNewsHolder, int i) {
        pieceNewsHolder.pieceNewsTitle.setText(dataList.get(i).getTitle());
        Picasso.with(realTimeActivity)
                .load(dataList.get(i).getUrlToImage())
                .fit()
                .centerCrop()
                .into(pieceNewsHolder.imagePieceNews);

        pieceNewsHolder.itemView.setOnClickListener( (View v) -> {
            recyclerClickListener.onItemClick(dataList.get(i));
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PieceNewsHolder extends RecyclerView.ViewHolder
    {
        TextView pieceNewsTitle;
        ImageView imagePieceNews;
        CardView cardView;

        public PieceNewsHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            pieceNewsTitle = (TextView) itemView.findViewById(R.id.piece_news_ttl);
            imagePieceNews = (ImageView) itemView.findViewById(R.id.piece_news_img);
        }
    }
}
