package pers.chenbo.shownews.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pers.chenbo.shownews.R;
import pers.chenbo.shownews.databinding.SearchNewsItemBinding;
import pers.chenbo.shownews.databinding.SwipeNewsCardBinding;
import pers.chenbo.shownews.model.Article;

public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder> {
    // 1. Supporting data
    private List<Article> articles = new ArrayList<>();
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    // 2. CardSwipeViewHolder
    public class CardSwipeViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            SwipeNewsCardBinding binding = SwipeNewsCardBinding.bind(itemView);
            itemImageView = binding.swipeCardImageView;
            titleTextView = binding.swipeCardTitle;
            descriptionTextView = binding.swipeCardDescription;
        }
    }

    // 3. Adapter Override
    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Creating-ViewHolder", Integer.valueOf(viewType).toString());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_news_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        Log.d("Binding-ViewHolder", Integer.valueOf(position).toString());
        Article article = articles.get(position);
        holder.titleTextView.setText(article.title);
        holder.descriptionTextView.setText(article.description);
        String url = article.urlToImage;
        if (url != null) {
            Log.d("isImageEmpty", url);
            Picasso.get().load(url).resize(200, 200).into(holder.itemImageView);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
