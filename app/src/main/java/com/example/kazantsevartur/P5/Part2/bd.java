package com.example.kazantsevartur.P5.Part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kazantsevartur.R;

import java.util.List;

public class bd extends AppCompatActivity {

    private EditText etName, etGenre, etYear, etDirector;
    private RatingBar ratingBar;
    private TextView tvOutput;
    private Button btnAdd, btnRead, btnSearch, btnUpdate, btnDelete;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        etName = findViewById(R.id.et_name);
        etGenre = findViewById(R.id.et_genre);
        etYear = findViewById(R.id.et_year);
        etDirector = findViewById(R.id.et_director);
        ratingBar = findViewById(R.id.rating_bar);
        tvOutput = findViewById(R.id.tv_output);
        btnAdd = findViewById(R.id.btn_add);
        btnRead = findViewById(R.id.btn_read);
        btnSearch = findViewById(R.id.btn_search);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        dbHelper = new Database(this);

        // CREATE
        btnAdd.setOnClickListener(v -> {
            if (!validateInput()) return;
            Film film = getFilmFromInput();
            if (dbHelper.addFilm(film.getName(), film.getRate(), film.getGenre(), film.getYear(), film.getDirector())) {
                toast("Фильм добавлен");
                clearFields();
                loadAllFilms();
            } else {
                toast("Ошибка добавления");
            }
        });

        // READ
        btnRead.setOnClickListener(v -> loadAllFilms());

        // SEARCH
        btnSearch.setOnClickListener(v -> {
            String genre = etGenre.getText().toString().trim();
            if (genre.isEmpty()) { toast("Введите жанр для поиска"); return; }
            List<Film> results = dbHelper.searchByGenre(genre);
            displayFilms(results, "Поиск по жанру: " + genre);
        });

        // UPDATE
        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) { toast("Введите название для обновления"); return; }
            // Ищем фильм по названию
            List<Film> all = dbHelper.getAllFilms();
            for (Film f : all) {
                if (f.getName().equals(name)) {
                    Film updated = getFilmFromInput();
                    if (dbHelper.updateFilm(f.getId(), updated.getName(), updated.getRate(),
                            updated.getGenre(), updated.getYear(), updated.getDirector())) {
                        toast("Обновлено");
                        clearFields();
                        loadAllFilms();
                    }
                    return;
                }
            }
            toast("Фильм не найден");
        });

        // DELETE
        btnDelete.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) { toast("Введите название для удаления"); return; }
            List<Film> all = dbHelper.getAllFilms();
            for (Film f : all) {
                if (f.getName().equals(name)) {
                    if (dbHelper.deleteFilm(f.getId())) {
                        toast("Удалено");
                        clearFields();
                        loadAllFilms();
                    }
                    return;
                }
            }
            toast("Фильм не найден");
        });

        // Показать все фильмы при старте
        loadAllFilms();
    }

    // Вспомогательные методы
    private Film getFilmFromInput() {
        int year = 2024;
        try { year = Integer.parseInt(etYear.getText().toString()); } catch (Exception e) {}
        return new Film(0, etName.getText().toString().trim(), ratingBar.getRating(),
                etGenre.getText().toString().trim(), year, etDirector.getText().toString().trim());
    }

    private boolean validateInput() {
        if (etName.getText().toString().trim().isEmpty()) { toast("Введите название"); return false; }
        return true;
    }

    private void loadAllFilms() {
        List<Film> films = dbHelper.getAllFilms();
        displayFilms(films, "Всего фильмов: " + films.size());
    }

    private void displayFilms(List<Film> films, String header) {
        StringBuilder sb = new StringBuilder(header + "\n\n");
        if (films.isEmpty()) {
            sb.append("Пусто");
        } else {
            for (Film f : films) {
                sb.append("ID:").append(f.getId()).append(" | ").append(f).append("\n\n");
            }
        }
        tvOutput.setText(sb.toString());
    }

    private void clearFields() {
        etName.setText(""); etGenre.setText(""); etYear.setText(""); etDirector.setText("");
        ratingBar.setRating(0);
        etName.requestFocus();
    }

    private void toast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}