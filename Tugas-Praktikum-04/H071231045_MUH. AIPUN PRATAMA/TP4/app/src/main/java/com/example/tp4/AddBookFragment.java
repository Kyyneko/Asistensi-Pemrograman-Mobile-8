package com.example.tp4;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tp4.dataSource.DataSource;
import com.example.tp4.databinding.FragmentAddBookBinding;
import com.example.tp4.models.Book;

public class AddBookFragment extends Fragment {

    FragmentAddBookBinding binding;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<String> permissionLauncher;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back2.setOnClickListener(v -> ((MainActivity) getActivity()).goToHomeFragment());

        // Untuk buka galeri
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        if (imageUri != null) {
                            // Gunakan imageUri, misal tampilkan
                            binding.image.setImageURI(imageUri);
                            binding.image.setVisibility(View.VISIBLE);
                            binding.iconFoto.setVisibility(View.GONE);
                        }
                    }
                });

        // Untuk izin
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        openGallery();
                    } else {
                        Toast.makeText(requireContext(), "Izin ditolak", Toast.LENGTH_SHORT).show();
                    }
                });

        binding.iconFoto.setOnClickListener(v -> checkPermissionAndOpenGallery());

        try {
            binding.btnTambahBuku.setOnClickListener(v -> {
                String judul = binding.etJudul.getText().toString();
                String penulis = binding.etPenulis.getText().toString();
                String tahunTerbit = binding.etTahunTerbit.getText().toString();
                String genre = binding.etGenre.getText().toString();
                String blurb = binding.etBlurb.getText().toString();
                String publisher = binding.etPublisher.getText().toString();
                String edition = binding.etPublisher.getText().toString();
                Uri coverUri = imageUri;
                String language = binding.etBahasa.getText().toString();
                String category = binding.etCategory.getText().toString();
                String ISBN = binding.etIsbn.getText().toString();
                String country = binding.etNegara.getText().toString();

                if (imageUri != null) {
                    Uri cover = imageUri;
                }
                if (binding.etNumber.getText().toString().isEmpty() || binding.etRating.getText().toString().isEmpty() || judul.isEmpty() || penulis.isEmpty() || tahunTerbit.isEmpty() || genre.isEmpty() || blurb.isEmpty()) {
                    Toast.makeText(requireContext(), "Masukkan semua data", Toast.LENGTH_SHORT).show();

                } else {
                    int number = Integer.parseInt(binding.etNumber.getText().toString());
                    int rating = Integer.parseInt(binding.etRating.getText().toString());
                    DataSource.books.add(0, new Book(judul, penulis, tahunTerbit, genre, blurb, number, language, category, ISBN, country, publisher, edition, coverUri,  rating, false));
                    DataSource.bookBaruDitambahkan = true;
                    DataSource.tambahBuku(DataSource.books.get(0));
                }


            });
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Gagal menambahkan buku", Toast.LENGTH_SHORT).show();
        }


        // Inflate the layout for this fragment
        return view;
    }

    public void checkPermissionAndOpenGallery() {
        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else if (shouldShowRequestPermissionRationale(permission)) {
            Toast.makeText(requireContext(), "Aplikasi butuh izin untuk akses galeri", Toast.LENGTH_SHORT).show();
            permissionLauncher.launch(permission);
        } else {
            permissionLauncher.launch(permission);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}