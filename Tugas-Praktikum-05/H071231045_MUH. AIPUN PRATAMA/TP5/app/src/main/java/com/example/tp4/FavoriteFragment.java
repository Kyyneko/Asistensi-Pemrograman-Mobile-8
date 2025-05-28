package com.example.tp4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp4.adapter.FavoriteFragmentAdapter;
import com.example.tp4.adapter.HomeFragmentAdapter;
import com.example.tp4.dataSource.DataSource;
import com.example.tp4.databinding.FragmentFavoriteBinding;
import com.example.tp4.databinding.FragmentHomeBinding;
import com.example.tp4.models.Book;
import com.example.tp4.models.BookPreview;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment {
    FragmentFavoriteBinding binding;
    public static FavoriteFragmentAdapter adapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //      RecyclerView Daftar Buku
        adapter = new FavoriteFragmentAdapter(new ArrayList<>(DataSource.getFavoriteBookPreviews()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvFavoriteBooks.setLayoutManager(layoutManager);
        binding.rvFavoriteBooks.setAdapter(adapter);
        binding.rvFavoriteBooks.setVisibility(View.GONE);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            try {
                Thread.sleep(300); // Bisa dihilangkan atau disesuaikan
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                if (adapter != null) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.rvFavoriteBooks.setVisibility(View.VISIBLE);
                    adapter.updateData(new ArrayList<>(DataSource.getFavoriteBookPreviews())); // Pastikan kamu buat method ini

                }
            });
        });
    }
}