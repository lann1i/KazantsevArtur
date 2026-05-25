package com.example.kazantsevartur.P6.Part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kazantsevartur.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JsonActivity extends AppCompatActivity {
    private static final String FILE_NAME = "books.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        EditText etTitle = findViewById(R.id.et_title);
        EditText etAuthor = findViewById(R.id.et_author);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnLoad = findViewById(R.id.btn_load);
        TextView tvOutput = findViewById(R.id.tv_output);

        // СОХРАНИТЬ в JSON
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show();
                return;
            }
            Book book = new Book(0, title, author);
            saveToJson(book);
            Toast.makeText(this, "Сохранено в JSON", Toast.LENGTH_SHORT).show();
        });

        // ЗАГРУЗИТЬ из JSON
        btnLoad.setOnClickListener(v -> {
            Book book = loadFromJson();
            if (book != null) {
                tvOutput.setText("Загружено:\n📚 " + book.getTitle() + " — " + book.getAuthor());
            } else {
                tvOutput.setText("Файл не найден или ошибка");
            }
        });
    }

    // Сохранение объекта в JSON-файл
    private void saveToJson(Book book) {
        Gson gson = new Gson();
        String json = gson.toJson(book);
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(json.getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Чтение объекта из JSON-файла
    private Book loadFromJson() {
        Gson gson = new Gson();
        try (FileInputStream fis = openFileInput(FILE_NAME)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            return gson.fromJson(sb.toString(), Book.class);
        } catch (IOException e) { return null; }
    }
}