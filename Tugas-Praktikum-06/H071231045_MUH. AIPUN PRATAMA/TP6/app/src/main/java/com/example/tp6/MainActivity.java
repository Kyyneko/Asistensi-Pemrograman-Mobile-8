package com.example.tp6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private EntityAdapter entityAdapter;
    private List<Entity> itemList;
    private Button btnLoadMore;
    private ProgressBar progressBar;
    private int currentPage = 1;
    private int totalPages = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);

        // Inisialisasi recyclerview
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi adapter
        itemList = new ArrayList<>();
        entityAdapter = new EntityAdapter(itemList);
        recyclerView.setAdapter(entityAdapter);

        loadPage(currentPage);

        // Tombol Muat Lebih Banyak diklik
        btnLoadMore = findViewById(R.id.btnLoadMore);
        btnLoadMore.setOnClickListener(v -> {
            if (currentPage < totalPages) {
                currentPage++;

                // Jalankan di thread terpisah
                new Thread(() -> {
                    runOnUiThread(() -> Toast.makeText(this, "Loading page " + currentPage, Toast.LENGTH_SHORT).show());
                    loadPage(currentPage);
                }).start();

            } else {
                Toast.makeText(this, "Tidak ada halaman lagi", Toast.LENGTH_SHORT).show();
            }
        });

        entityAdapter.setOnItemClickListener(entity -> {
            FragmentCharacterDetail fragment = FragmentCharacterDetail.newInstance(entity.getId());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) // ganti dengan id container yang sesuai
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void loadPage(int page) {
        runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE)); // Tampilkan loading

        try {
            Thread.sleep(1000); // Simulasi loading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runOnUiThread(() -> {
            apiService = ApiConfig.getClient().create(ApiService.class);
            Call<EntityResponse> call = apiService.getEntities(page);
            call.enqueue(new Callback<EntityResponse>() {
                @Override
                public void onResponse(Call<EntityResponse> call, Response<EntityResponse> response) {
                    progressBar.setVisibility(View.GONE); // Sembunyikan loading

                    if (response.isSuccessful() && response.body() != null) {
                        EntityResponse entityResponse = response.body();
                        itemList.addAll(entityResponse.getData());
                        entityAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<EntityResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE); // Sembunyikan loading
                    Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}