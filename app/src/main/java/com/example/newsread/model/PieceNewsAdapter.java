package com.example.newsread.model;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.newsread.databinding.PieceNewsCardBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PieceNewsAdapter extends RecyclerView.Adapter<PieceNewsAdapter.PieceNewsHolder> {

    private ArrayList<Article> dataList;
    private RecyclerClickListener recyclerClickListener;

    public PieceNewsAdapter(ArrayList<Article> dataList, RecyclerClickListener recyclerClickListener) {
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public PieceNewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        PieceNewsCardBinding binding = PieceNewsCardBinding.inflate(layoutInflater, viewGroup, false);
        return new PieceNewsHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PieceNewsHolder pieceNewsHolder, int i) {

        pieceNewsHolder.binding.setArticle(dataList.get(i));

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
        PieceNewsCardBinding binding;

        public PieceNewsHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url)
    {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
