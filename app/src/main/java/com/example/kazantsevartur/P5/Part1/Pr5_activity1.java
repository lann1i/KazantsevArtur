package com.example.kazantsevartur.P5.Part1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kazantsevartur.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr5_activity1 extends AppCompatActivity {

    private EditText etFilename, etContent;
    private TextView tvOutput;
    private static final String KEY_FILENAME = "filename";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_OUTPUT = "output";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr51);
        etFilename = findViewById(R.id.et_filename);
        etContent = findViewById(R.id.et_content);
        tvOutput = findViewById(R.id.tv_output);
        Button btnCreate = findViewById(R.id.btn_create);
        Button btnAppend = findViewById(R.id.btn_append);
        Button btnRead = findViewById(R.id.btn_read);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnReadActivity = findViewById(R.id.btn_read_activity);

        if (savedInstanceState != null) {
            etFilename.setText(savedInstanceState.getString(KEY_FILENAME, ""));
            etContent.setText(savedInstanceState.getString(KEY_CONTENT, ""));
            tvOutput.setText(savedInstanceState.getString(KEY_OUTPUT,""));
        }

        btnCreate.setOnClickListener(v -> {
            String filename = etFilename.getText().toString();
            String content = etContent.getText().toString();
            if (filename.isEmpty()) { Toast.makeText(this, "Введите имя файла", Toast.LENGTH_SHORT).show(); return; }
            try (FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE)) {
                fos.write(content.getBytes());
                Toast.makeText(this, "Файл создан", Toast.LENGTH_SHORT).show();
            } catch (IOException e) { e.printStackTrace(); }
            writeExternal(filename, content);
        });

        btnAppend.setOnClickListener(v -> {
            String filename = etFilename.getText().toString();
            String content = etContent.getText().toString();
            if (filename.isEmpty()) { Toast.makeText(this, "Введите имя файла", Toast.LENGTH_SHORT).show(); return; }
            try (FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND)) {
                fos.write(("\n" + content).getBytes());
                Toast.makeText(this, "Данные добавлены", Toast.LENGTH_SHORT).show();
            } catch (IOException e) { e.printStackTrace(); }
        });

        btnRead.setOnClickListener(v -> {
            String filename = etFilename.getText().toString();
            if (filename.isEmpty()) { Toast.makeText(this, "Введите имя файла", Toast.LENGTH_SHORT).show(); return; }
            try (FileInputStream fis = openFileInput(filename)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line!=null) {
                    sb.append(line).append("\n");
                    line=reader.readLine();
                }
                tvOutput.setText(sb.toString());
            } catch (IOException e) { Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show(); }
        });

        btnDelete.setOnClickListener(v -> {
            String filename = etFilename.getText().toString();
            if (filename.isEmpty()) { Toast.makeText(this, "Введите имя файла", Toast.LENGTH_SHORT).show(); return; }
            new AlertDialog.Builder(this).setMessage("Удалить файл \"" + filename + "?")
                    .setPositiveButton("Да", (d,w) -> {
                        File file =new File(getFilesDir(),filename);
                        if (file.delete()) {
                            tvOutput.setText("");
                            Toast.makeText(this,"Файл удален",Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Нет", null).show();
        });

        btnReadActivity.setOnClickListener(v -> {
            Intent intent = new Intent(Pr5_activity1.this, ReadExternalActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FILENAME, etFilename.getText().toString());
        outState.putString(KEY_CONTENT, etContent.getText().toString());
        outState.putString(KEY_OUTPUT, tvOutput.getText().toString());
    }

    // Запись во внешнее хранилище
    private void writeExternal(String filename, String content) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.append(content);
            writer.flush();
            Toast.makeText(this, "Записано во внешнее хранилище", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка записи: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}