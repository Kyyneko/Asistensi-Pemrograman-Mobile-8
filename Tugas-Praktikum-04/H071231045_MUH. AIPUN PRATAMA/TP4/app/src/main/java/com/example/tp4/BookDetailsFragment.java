package com.example.tp4;

import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tp4.dataSource.DataSource;
import com.example.tp4.databinding.FragmentBookDetailsBinding;
import com.example.tp4.models.Book;

public class BookDetailsFragment extends Fragment {
    private FragmentBookDetailsBinding binding;
    private Book book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back.setOnClickListener(v -> requireActivity().onBackPressed());

        // Mendapatkan id dari bundle
        int bookId = getArguments().getInt("id");
        book = DataSource.books.stream().filter(b -> bookId == b.getId()).findFirst().orElse(null);

        if (book != null) {
            Context context = requireContext();

            binding.judul.setText(book.getJudul());
            binding.penulis.setText(book.getPenulis());
            binding.year.setText(book.getTahunTerbit());
            binding.genre.setText(book.getGenre());
            binding.blurb.setText(book.getBlurb());
            binding.number.setText(String.valueOf(book.getNumber()));
            binding.language.setText(book.getLanguage());
            binding.author.setText(book.getPenulis());
            binding.category.setText(book.getCategory());
            binding.isbn.setText(book.getISBN());
            binding.country.setText(book.getCountry());
            binding.publisher.setText(book.getPublisher());
            binding.edition.setText(book.getEdition());
            if (book.getCoverUri() != null) {
                binding.cover.setImageURI(book.getCoverUri());
            } else {
                binding.cover.setImageResource(book.getCoverResId());
            }

            // Logika untuk mengatur jumlah rating dan warna bintang
            binding.layoutRating.removeAllViews();

            int totalStars = 5;
            int rating = book.getRating();

            for (int i = 0; i < totalStars; i++) {
                ImageView star = new ImageView(context);
                int sizeInDp = 16;
                int sizeInPx = dpToPx(sizeInDp, context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                star.setLayoutParams(params);

                star.setImageResource(R.drawable.ic_star_filled);
                if (i < rating) {
                    star.setColorFilter(ContextCompat.getColor(context, R.color.yellow));
                } else {
                    star.setColorFilter(ContextCompat.getColor(context, R.color.star_off));
                }
                binding.layoutRating.addView(star);
            }

            // Set initial like state
            updateLikeIcon();

            // Handle like button click
            binding.like.setOnClickListener(v -> {
                book.setLike(!book.isLike());
                updateLikeIcon();
            });
        }

        // Inflate the layout for this fragment
        return view;
    }

    // Helper method to update the like button icon based on the current state
    private void updateLikeIcon() {
        if (book != null) {
            binding.like.setImageResource(book.isLike() ? R.drawable.favorite_on : R.drawable.favorite);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update like icon when fragment resumes
        updateLikeIcon();
    }

    private int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
