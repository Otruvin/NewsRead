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

import com.example.newsread.Contracts.FavoriteDispAdapter;
import com.example.newsread.Presenter.AdapterFavorPresenter;
import com.example.newsread.R;
import com.example.newsread.databinding.FavoriteAdapterBinding;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsFavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FavoriteDispAdapter.IFavorAdapterView {

    private FirebaseUser firebaseUser;
    private List<Article> dataList;
    private RecyclerClickListener recyclerClickListener;
    private AdapterFavorPresenter presenter;

    public NewsFavoriteAdapter(FirebaseUser firebaseUser, List<Article> dataList,
                               RecyclerClickListener recyclerClickListener) {
        this.presenter = new AdapterFavorPresenter();
        this.firebaseUser = firebaseUser;
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        FavoriteAdapterBinding adapterBinding = FavoriteAdapterBinding.inflate(layoutInflater, viewGroup, false);
        return new FavoriteNewsHolder(adapterBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Article article = dataList.get(i);
        FavoriteNewsHolder holderArticle = (FavoriteNewsHolder) viewHolder;
        holderArticle.binding.setArticleFavor(dataList.get(i));


        ((FavoriteNewsHolder) viewHolder).buttonSend.setOnClickListener((View v) ->
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

        ((FavoriteNewsHolder) viewHolder).buttonDelete.setOnClickListener((View v) ->
        {
            dataList.remove(i);
            this.notifyDataSetChanged();
            presenter.deleteFavorNews(article.getTitle(), firebaseUser);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class FavoriteNewsHolder extends RecyclerView.ViewHolder
    {

        public FavoriteAdapterBinding binding;
        public Button buttonSend;
        public Button buttonDelete;

        public FavoriteNewsHolder(@NonNull View itemView) {
            super(itemView);
            buttonSend = (Button) itemView.findViewById(R.id.button_send_favor);
            buttonDelete = (Button) itemView.findViewById(R.id.button_del_favor);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @BindingAdapter("imageUrlFavor")
    public static void loadImage(ImageView imageView, String url)
    {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
