package com.example.kazantsevartur;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        //Находим элемент RecycleView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //Устанавливает макет отображения - гориозонтально
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Пример списка строк
        List<Item> items = new ArrayList<>();
        items.add(new Item("Яблоко", R.drawable.apple));
        items.add(new Item("Банан", R.drawable.banana));
        items.add(new Item("Апельсин", R.drawable.orange));
        //Создаем адаптер
        ItemAdapter adapter = new ItemAdapter(items);
        //Устанавливаем для спсика адаптер
        recyclerView.setAdapter(adapter);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
