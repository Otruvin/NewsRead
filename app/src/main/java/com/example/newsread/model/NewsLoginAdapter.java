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
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsread.Contracts.AddFavoratiesContract;
import com.example.newsread.Interactor.AddNewsFavorIntractor;
import com.example.newsread.Presenter.AddFavoratiesPresenter;
import com.example.newsread.R;
import com.example.newsread.databinding.PieceNewsLoginCardAdapterBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsLoginAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AddFavoratiesContract.IAdapterAddNewsFavorView {

    private ArrayList<Article> dataList;
    private RecyclerClickListener recyclerClickListener;
    private FirebaseUser firebaseUser;
    private AddFavoratiesPresenter presenter;

    public NewsLoginAdapter(ArrayList<Article> dataList, RecyclerClickListener recyclerClickListener,
                            FirebaseUser firebaseUser) {
        this.presenter = new AddFavoratiesPresenter(this, new AddNewsFavorIntractor());
        this.firebaseUser = firebaseUser;
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        PieceNewsLoginCardAdapterBinding binding = PieceNewsLoginCardAdapterBinding.inflate(layoutInflater, viewGroup, false);
        return new PieseNewsLoginHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Article article = dataList.get(i);
        PieseNewsLoginHolder holderArticle = (PieseNewsLoginHolder) viewHolder;
        holderArticle.pieceNewsLoginCardAdapterBinding.setArticleLogin(dataList.get(i));


        ((PieseNewsLoginHolder) viewHolder).sendButton.setOnClickListener((View v) ->
        {
            Intent send = new Intent(Intent.ACTION_SEND);

            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_SUBJECT, article.getTitle());
            send.putExtra(Intent.EXTRA_TEXT, article.toString());

            v.getContext().startActivity(send);
        });
        viewHolder.itemView.setOnClickListener( (View v) -> {

            recyclerClickListener.onItemClick(dataList.get(i));
        });

        ((PieseNewsLoginHolder) viewHolder).addFavorButton.setOnClickListener((View v) ->
        {
            presenter.annNews(article, firebaseUser);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PieseNewsLoginHolder extends RecyclerView.ViewHolder
    {
        public PieceNewsLoginCardAdapterBinding pieceNewsLoginCardAdapterBinding;
        public Button sendButton;
        public Button addFavorButton;

        public PieseNewsLoginHolder(@NonNull View itemView) {
            super(itemView);

            sendButton = (Button) itemView.findViewById(R.id.button_send_login);
            addFavorButton = (Button) itemView.findViewById(R.id.button_add_favor);
            pieceNewsLoginCardAdapterBinding = DataBindingUtil.bind(itemView);
        }
    }



    @BindingAdapter("imageUrlLogin")
    public static void loadImage(ImageView imageView, String url)
    {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
