# 📌 Panduan Pengumpulan Tugas Praktikum Mobile

## 📂 Struktur Repository
```
Asistensi-Pemrograman-Mobile-8/
│── Tugas Praktikum 1/
│   ├── NIM/  
│   │   ├── tugas1
│── Tugas Praktikum 2/
│   ├── NIM/  
│   │   ├── tugas2
│── README.md
```

---

## 📝 1. Langkah Awal (Fork Repository)
1. **Login ke GitHub** dan buka repository praktikum.
2. Klik tombol **Fork** (kanan atas) untuk membuat salinan repository ke akun kalian.
3. Setelah fork, kalian akan memiliki repository dengan URL:
   ```
   https://github.com/username/praktikum-mobile-2025
   ```

---

## 🔽 2. Clone Repository ke Laptop
1. **Buka Terminal / Git Bash**.
2. Clone repository ke komputer dengan perintah:
   ```sh
   git clone https://github.com/username/asistensi-pemrograman-mobile-8.git
   ```
3. Masuk ke folder repository:
   ```sh
   cd asistensi-pemrograman-mobile-8
   ```

---

## 🏗️ 3. Tambahkan Tugas Praktikum
1. Masuk ke folder tugas praktikum yang sesuai, lalu buat folder dengan NIM kalian:
   ```sh
   mkdir "Tugas Praktikum 1/NIM"
   ```
2. Pindahkan file tugas kalian ke dalam folder tersebut:
   ```sh
   mv tugas1.kt laporan.pdf "Tugas Praktikum 1/NIM/"
   ```
3. Cek apakah file sudah ada dengan:
   ```sh
   ls "Tugas Praktikum 1/NIM/"
   ```

---

## 📤 4. Commit dan Push Tugas
1. Tambahkan perubahan ke Git:
   ```sh
   git add .
   ```
2. Buat commit dengan pesan yang jelas:
   ```sh
   git commit -m "Tambah Tugas Praktikum 1 - NIM"
   ```
3. Kirim perubahan ke repository GitHub kalian:
   ```sh
   git push origin main
   ```

---

## 🔄 5. Buat Pull Request (PR)
1. **Buka repository GitHub kalian**.
2. Klik tombol **Pull Request**.
3. Pilih **Compare changes** dan pastikan repository tujuan adalah repository utama praktikum.
4. Klik **Create Pull Request**, beri judul:
   ```
   Pengumpulan Tugas Praktikum 1 - NIM
   ```
5. Tunggu dosen/aslab untuk review dan merge PR.

---

## ✅ 6. Cek Status Tugas
- Jika PR **diterima (merged)**, tugas berhasil dikumpulkan.
- Jika PR **ditolak**, periksa komentar dan lakukan perbaikan.
- Jika ada revisi, ulangi proses commit, push, dan update PR.

---

### 🎯 **Catatan**
✅ Pastikan file tugas benar sebelum commit.  
✅ Gunakan NIM sebagai nama folder untuk identifikasi mudah.  
✅ Jika ada kendala, tanyakan di grup atau hubungi asisten praktikum.  

---

🚀 **Selamat mengerjakan!**
