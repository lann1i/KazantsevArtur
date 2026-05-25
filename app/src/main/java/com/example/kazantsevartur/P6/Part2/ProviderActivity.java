package com.example.kazantsevartur.P6.Part2;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kazantsevartur.R;

public class ProviderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main);

        EditText etTitle = findViewById(R.id.et_title);
        EditText etAuthor = findViewById(R.id.et_author);
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnRead = findViewById(R.id.btn_read);
        TextView tvOutput = findViewById(R.id.tv_output);

        // ДОБАВИТЬ книгу через провайдер
        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show();
                return;
            }
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("author", author);
            getContentResolver().insert(BookProvider.CONTENT_URI, cv);
            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
            etTitle.setText(""); etAuthor.setText("");
        });

        // ЧИТАТЬ книги из провайдера (тот код, который ты спрашивал)
        btnRead.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder("Книги из провайдера:\n\n");
            Cursor cursor = getContentResolver().query(
                    BookProvider.CONTENT_URI, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                    sb.append("📚 ").append(title).append(" — ").append(author).append("\n");
                } while (cursor.moveToNext());
                cursor.close();
            }
            tvOutput.setText(sb.toString());
        });
    }
}