package com.example.tp4.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4.BookDetailsFragment;
import com.example.tp4.MainActivity;
import com.example.tp4.R;
import com.example.tp4.models.Book;
import com.example.tp4.models.BookPreview;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolder> {

    private ArrayList<BookPreview> favoriteBooks;

    public FavoriteFragmentAdapter(ArrayList<BookPreview> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    @NonNull
    @Override
    public FavoriteFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFragmentAdapter.ViewHolder holder, int position) {
        BookPreview favoriteBook = favoriteBooks.get(position);
        holder.setData(favoriteBook);
    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size();
    }

    public void updateData(ArrayList<BookPreview> newData) {
        this.favoriteBooks.clear();
        this.favoriteBooks.addAll(newData);
        notifyDataSetChanged(); // agar tampilan diperbarui
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textAuthor;
        private ImageView imageCover;
        private LinearLayout layoutRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textAuthor = itemView.findViewById(R.id.text_author);
            imageCover = itemView.findViewById(R.id.image_background);
            layoutRating = itemView.findViewById(R.id.layout_rating);
        }

        public void setData(BookPreview favoriteBook) {
            Context context = itemView.getContext();

            textTitle.setText(favoriteBook.getJudul());
            textAuthor.setText(favoriteBook.getPenulis());

            if (favoriteBook.getCoverUri() != null) {
                imageCover.setImageURI(favoriteBook.getCoverUri());
            } else {
                imageCover.setImageResource(favoriteBook.getCoverResId());
            }

            // Logika untuk mengatur jumlah rating dan warna bintang
            layoutRating.removeAllViews();

            int totalStars = 5;
            int rating = favoriteBook.getRating();

            for (int i = 0; i < totalStars; i++) {
                ImageView star = new ImageView(context);
                int sizeInDp = 16;
                int sizeInPx = dpToPx(sizeInDp, context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                star.setLayoutParams(params);

                if (i < rating) {
                    star.setImageResource(R.drawable.ic_star_filled);
                    star.setColorFilter(ContextCompat.getColor(context, R.color.yellow));
                } else {
                    star.setImageResource(R.drawable.ic_star_filled);
                    star.setColorFilter(ContextCompat.getColor(context, R.color.star_off));
                }
                layoutRating.addView(star);
            }

            // Setiap item yang diklik akan mengarah ke halaman detail
            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("id", favoriteBook.getId());

                BookDetailsFragment detailsFragment = new BookDetailsFragment();
                detailsFragment.setArguments(bundle);

                ((MainActivity) v.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            });
        }

        private int dpToPx(int dp, Context context) {
            float density = context.getResources().getDisplayMetrics().density;
            return Math.round(dp * density);
        }
    }
}
