package com.example.newsread.model;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newsread.R;
import com.example.newsread.databinding.PieceNewsCardBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PieceNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Article> dataList;
    private RecyclerClickListener recyclerClickListener;

    public PieceNewsAdapter(ArrayList<Article> dataList, RecyclerClickListener recyclerClickListener) {
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        PieceNewsCardBinding binding = PieceNewsCardBinding.inflate(layoutInflater, viewGroup, false);
        return new PieceNewsHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pieceNewsHolder, int i) {

        Article article = dataList.get(i);
        PieceNewsHolder holderArticle = (PieceNewsHolder) pieceNewsHolder;
        holderArticle.binding.setArticle(dataList.get(i));

        ((PieceNewsHolder) pieceNewsHolder).buttonSend.setOnClickListener((View v) ->
        {
            Intent send = new Intent(Intent.ACTION_SEND);

            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_SUBJECT, article.getTitle());
            send.putExtra(Intent.EXTRA_TEXT, article.toString());

            v.getContext().startActivity(send);
        });
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
        public PieceNewsCardBinding binding;
        public Button buttonSend;

        public PieceNewsHolder(@NonNull View itemView) {
            super(itemView);
            buttonSend = (Button) itemView.findViewById(R.id.button_send);
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

    /*public class HandlerClick
    {
        public void onSend(Article article, View view)
        {
            Intent send = new Intent(Intent.ACTION_SEND);

            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_SUBJECT, article.getTitle());
            send.putExtra(Intent.EXTRA_TEXT, article.toString());

            view.getContext().startActivity(send);

        }
    }*/

    /*class PieceNewsLoadingHolder extends RecyclerView.ViewHolder
    {
        public ProgressBar progressBar;

        public PieceNewsLoadingHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar_piece_news);
        }
    }*/
}


