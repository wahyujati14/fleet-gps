Perkenalkan saya Wahyuning Jagat Sukmo Jati, berikut mengenai instruksi configurasi :

# Fleet GPS Tracking API

REST API sederhana untuk pelacakan GPS kendaraan dengan autentikasi JWT, flag pelanggaran kecepatan, dan pembersihan otomatis data lama.

---

## Instruksi Singkat

1. **Siapkan Database**
   - Buat database `fleetdb` di PostgreSQL/MySQL.
   - Import skema dari `docs/schema.sql` jika perlu.

2. **Konfigurasi Koneksi**
   - Edit `src/main/resources/application.properties` sesuai database yang digunakan (PostgreSQL/MySQL).

3. **Build & Jalankan**
   ```
   mvn clean install
   mvn spring-boot:run
   ```

4. **Tes API**
   - Gunakan [Postman Collection ini](https://documenter.getpostman.com/view/18124912/2sB2qUnji9) untuk mencoba semua endpoint.

5. **Unit Test**
   - Unit test tersedia di folder `src/test/java/com/example/fleet/`.
   - Untuk menjalankan seluruh unit test:
     ```
     mvn test
     ```
   - Pada mode test, validasi JWT otomatis di-bypass agar pengujian controller berjalan lancar tanpa token asli.

---

## Catatan

- Login: username `admin`, password `password123`
- Semua endpoint (kecuali login) wajib pakai JWT token di header Authorization.
- Data GPS dengan kecepatan > 100 km/jam otomatis di-flag pelanggaran = true.
- Data GPS lama akan dihapus otomatis sesuai pengaturan `gps.cleanup.days`.
- Skema database sudah dinormalisasi dan diindeks.

---

**Terima kasih**