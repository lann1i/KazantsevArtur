package com.example.kazantsevartur.P5.Part2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kazantsevartur.R;

public class SharedPreference extends AppCompatActivity {
    private static final String PREF_FILE = "user_settings";
    private static final String KEY_USERNAME = "username";

    private EditText etUsername;
    private TextView tvResult;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        etUsername = findViewById(R.id.et_username);
        TextView tvResult = findViewById(R.id.tv_result);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnRead = findViewById(R.id.btn_read);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);
        sharedPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);

        btnSave.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME, username);
            editor.apply();

            tvResult.setText("Сохранено: " + username);
            Toast.makeText(this, "Имя сохранено", Toast.LENGTH_SHORT).show();
        });

        btnRead.setOnClickListener(v -> {
            String username = sharedPreferences.getString(KEY_USERNAME, "Не задано");

            tvResult.setText("Текущее имя: " + username);
            Toast.makeText(this, "Прочитано", Toast.LENGTH_SHORT).show();
        });

        btnUpdate.setOnClickListener(v -> {
            String newUsername = etUsername.getText().toString().trim();
            String oldUsername = sharedPreferences.getString(KEY_USERNAME, null);

            if (newUsername.isEmpty()) {
                Toast.makeText(this, "Введите новое имя", Toast.LENGTH_SHORT).show();
                return;
            }
            if (oldUsername == null) {
                Toast.makeText(this, "Сначала сохраните имя", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME, newUsername);
            editor.apply();

            tvResult.setText("Обновлено: " + oldUsername + " → " + newUsername);
            Toast.makeText(this, "Имя изменено", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {
            String currentUsername = sharedPreferences.getString(KEY_USERNAME, null);

            if (currentUsername == null) {
                Toast.makeText(this, "Нечего удалять", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(KEY_USERNAME);
            editor.apply();

            tvResult.setText("Удалено: " + currentUsername);
            Toast.makeText(this, "Имя удалено", Toast.LENGTH_SHORT).show();
        });
    }
}