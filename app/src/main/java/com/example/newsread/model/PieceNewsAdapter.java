package com.example.newsread.model;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.newsread.R;
import com.example.newsread.activity.ILoadMore;
import com.example.newsread.databinding.PieceNewsCardBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PieceNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    private Activity listActivity;
    private ArrayList<Article> dataList;
    private RecyclerClickListener recyclerClickListener;
    private int visibleTreShord = 5;
    private int lastVisibleItem, totalItemCount;

    public PieceNewsAdapter(ArrayList<Article> dataList, RecyclerClickListener recyclerClickListener,
                            Activity listActivity, RecyclerView recyclerView) {
        this.dataList = dataList;
        this.recyclerClickListener = recyclerClickListener;
        this.listActivity = listActivity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleTreShord))
                {
                    if (loadMore != null)
                    {
                        loadMore.onLoadMore();
                        isLoading = true;
                    }
                }

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            PieceNewsCardBinding binding = PieceNewsCardBinding.inflate(layoutInflater, viewGroup, false);
            return new PieceNewsHolder(binding.getRoot());
        }
        else if (i == VIEW_TYPE_LOADING)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_piece_news_progr_bar,
                    viewGroup, false);
            return new PieceNewsLoadingHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pieceNewsHolder, int i) {

        if (pieceNewsHolder instanceof PieceNewsHolder)
        {
            Article article = dataList.get(i);
            PieceNewsHolder holderArticle = (PieceNewsHolder) pieceNewsHolder;
            holderArticle.binding.setArticle(dataList.get(i));
        }
        else if (pieceNewsHolder instanceof PieceNewsLoadingHolder)
        {
            PieceNewsLoadingHolder pieceNewsLoadingHolder = (PieceNewsLoadingHolder) pieceNewsHolder;
            pieceNewsLoadingHolder.progressBar.setIndeterminate(true);
        }

        pieceNewsHolder.itemView.setOnClickListener( (View v) -> {

            recyclerClickListener.onItemClick(dataList.get(i));
        });
    }

    public void setLoaded(boolean loading) {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PieceNewsHolder extends RecyclerView.ViewHolder
    {
        public PieceNewsCardBinding binding;

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

class PieceNewsLoadingHolder extends RecyclerView.ViewHolder
{
    public ProgressBar progressBar;

    public PieceNewsLoadingHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar_piece_news);
    }
}
