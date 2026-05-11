package com.example.kazantsevartur.P5.Part1;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kazantsevartur.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadExternalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_external);

        EditText etFilename = findViewById(R.id.et_filename_read);
        Button btnRead = findViewById(R.id.btn_read_external);
        TextView tvOutput = findViewById(R.id.tv_output_read);

        btnRead.setOnClickListener(v -> {
            String filename = etFilename.getText().toString();
            if (filename.isEmpty()) {
                Toast.makeText(this, "Введите имя файла", Toast.LENGTH_SHORT).show();
                return;
            }
            String content = readExternal(filename);
            if (content != null) {
                tvOutput.setText(content);
            } else {
                tvOutput.setText("Файл не найден или ошибка чтения");
            }
        });
    }

    private String readExternal(String filename) {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageDir, filename);

        if (!file.exists()) return null;

        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text.toString();
    }
}