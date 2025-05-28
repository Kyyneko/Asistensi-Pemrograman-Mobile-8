// HomeFragment.java
package com.example.tp4;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tp4.adapter.HomeFragmentAdapter;
import com.example.tp4.dataSource.DataSource;
import com.example.tp4.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    public static HomeFragmentAdapter adapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        binding.searchView.setOnClickListener(v -> {
            binding.searchView.onActionViewExpanded();
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!binding.searchView.isIconified()) {
                    collapseSearchViewIfOpen();
                } else {
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
            }
        });

        binding.getRoot().setOnTouchListener((view1, motionEvent) -> {
            if (!binding.searchView.isIconified()) {
                collapseSearchViewIfOpen();
                return true;
            }
            return false;
        });

        binding.rvBooks.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HomeFragmentAdapter(new ArrayList<>(DataSource.bookPreviews));
        binding.rvBooks.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvBooks.setAdapter(adapter);

        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.rvBooks.setVisibility(View.GONE);

                executor.execute(() -> {
                    // Simulasi delay filter
                    try {
                        Thread.sleep(300); // Bisa dihilangkan atau disesuaikan
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mainHandler.post(() -> {
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.getFilter().filter(newText);
                        binding.rvBooks.setVisibility((View.VISIBLE));
                    });
                });

                return true;
            }
        });


        binding.filterByGenre.setOnClickListener(v -> showGenreFilterDialog());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null) {
            binding.searchView.setQuery("", false);
            binding.searchView.clearFocus();
            binding.searchView.onActionViewCollapsed();
        }
    }

    private void collapseSearchViewIfOpen() {
        if (!binding.searchView.isIconified()) {
            binding.searchView.setQuery("", false);
            binding.searchView.clearFocus();
            binding.searchView.onActionViewCollapsed();
        }
    }

    private void showGenreFilterDialog() {
        Set<String> genreSet = new HashSet<>();
        for (var book : DataSource.books) {
            genreSet.add(book.getGenre());
        }

        String[] genres = genreSet.toArray(new String[0]);
        boolean[] checkedItems = new boolean[genres.length];
        List<String> selectedGenres = new ArrayList<>();

        new AlertDialog.Builder(requireContext())
                .setTitle("Filter by Genre")
                .setMultiChoiceItems(genres, checkedItems, (dialog, which, isChecked) -> {
                    if (isChecked) {
                        selectedGenres.add(genres[which]);
                    } else {
                        selectedGenres.remove(genres[which]);
                    }
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    adapter.filterByGenres(selectedGenres);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
