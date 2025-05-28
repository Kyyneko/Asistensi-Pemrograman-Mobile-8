package com.example.tp6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCharacterDetail extends Fragment {

    private static final String ARG_ID = "character_id";

    public static FragmentCharacterDetail newInstance(int id) {
        FragmentCharacterDetail fragment = new FragmentCharacterDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView ivImage;
    private TextView tvName, tvStatus, tvSpecies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ivImage = view.findViewById(R.id.ivImage);
        tvName = view.findViewById(R.id.tvName);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvSpecies = view.findViewById(R.id.tvSpecies);

        int id = getArguments() != null ? getArguments().getInt(ARG_ID, -1) : -1;
        if (id == -1) return;

        ApiService apiService = ApiConfig.getClient().create(ApiService.class);
        apiService.getCharacterDetail(id).enqueue(new Callback<Entity>() {
            @Override
            public void onResponse(Call<Entity> call, Response<Entity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Entity character = response.body();
                    tvName.setText(character.getName());
                    tvStatus.setText(character.getStatus());
                    tvSpecies.setText(character.getSpecies());

                    Glide.with(requireContext())
                            .load(character.getImage())
                            .into(ivImage);
                }
            }

            @Override
            public void onFailure(Call<Entity> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}