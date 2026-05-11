package com.example.kazantsevartur.P3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kazantsevartur.R;

public class Pr3Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr31);

        String[] categories = getResources().getStringArray(R.array.categories);
        ListView listView = findViewById(R.id.listViewCategories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = categories[position];
            Intent intent = new Intent(Pr3Activity1.this, Pr3Activity2.class);
            intent.putExtra("category", selected);
            startActivity(intent);
        });

        Button btnRecycler = findViewById(R.id.btnRecycler);
        btnRecycler.setOnClickListener(v -> startActivity(new Intent(this, RecyclerActivity.class)));

        Button btnScroll = findViewById(R.id.btnScroll);
        btnScroll.setOnClickListener(v -> startActivity(new Intent(this, ScrollActivity.class)));

        Button btnSpinner = findViewById(R.id.btnSpinner);
        btnSpinner.setOnClickListener(v -> startActivity(new Intent(this, SpinnerActivity.class)));
    }
}