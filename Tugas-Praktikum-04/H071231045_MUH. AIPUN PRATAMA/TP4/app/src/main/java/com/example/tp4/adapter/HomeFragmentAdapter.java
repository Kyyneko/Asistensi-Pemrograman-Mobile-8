package com.example.tp4.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4.BookDetailsFragment;
import com.example.tp4.MainActivity;
import com.example.tp4.R;
import com.example.tp4.models.BookPreview;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> implements Filterable {
    private ArrayList<BookPreview> books;
    private final ArrayList<BookPreview> allBooks;

    private String currentGenreFilter = null;

    private List<String> currentGenreFilters = new ArrayList<>();

    public HomeFragmentAdapter(ArrayList<BookPreview> books) {
        this.books = books;
        this.allBooks = new ArrayList<>(books);
    }

    public void setGenreFilter(String genre) {
        currentGenreFilter = genre;
        getFilter().filter(""); // Trigger filtering
    }

    public void filterByGenres(List<String> genres) {
        currentGenreFilters = genres;
        getFilter().filter(""); // Trigger ulang filter keyword dan genre
    }

    @NonNull
    @Override
    public HomeFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentAdapter.ViewHolder holder, int position) {
        BookPreview book = books.get(position);
        holder.setData(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            protected FilterResults performFiltering(CharSequence keyword) {
                String searchText = keyword.toString().toLowerCase(Locale.ROOT).trim();
                ArrayList<BookPreview> filteredList = new ArrayList<>();

                for (BookPreview book : allBooks) {
                    boolean matchesSearch = searchText.isEmpty() || book.getJudul().toLowerCase(Locale.ROOT).contains(searchText);
                    boolean matchesGenre = currentGenreFilters.isEmpty() ||
                            currentGenreFilters.contains("All") ||
                            currentGenreFilters.contains(book.getGenre());

                    if (matchesSearch && matchesGenre) {
                        filteredList.add(book);
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }


            @Override
            protected void publishResults(CharSequence keyword, FilterResults results) {
                books.clear();
                books.addAll((List<BookPreview>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitle;
        private final TextView textAuthor;
        private final ImageView imageCover;
        private final LinearLayout layoutRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textAuthor = itemView.findViewById(R.id.text_author);
            imageCover = itemView.findViewById(R.id.image_cover);
            layoutRating = itemView.findViewById(R.id.layout_rating);
        }

        public void setData(BookPreview book) {

            Context context = itemView.getContext();

            textTitle.setText(book.getJudul());
            textAuthor.setText(book.getPenulis());

            if (book.getCoverUri() != null) {
                imageCover.setImageURI(book.getCoverUri());
            } else {
                imageCover.setImageResource(book.getCoverResId());
            }

            layoutRating.removeAllViews();

            int totalStars = 5;
            int rating = book.getRating();

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

            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("id", book.getId());

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
