package com.example.kazantsevartur.P3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kazantsevartur.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pr3Activity2 extends AppCompatActivity {

    private List<String> itemsList;
    private ArrayAdapter<String> adapter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr32);

        category = getIntent().getStringExtra("category");
        TextView tvTitle = findViewById(R.id.tvCategoryTitle);
        tvTitle.setText(category);

        ListView listView = findViewById(R.id.listViewItems);
        EditText etNewItem = findViewById(R.id.etNewItem);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnRemove = findViewById(R.id.btnRemove);
        Button btnBack = findViewById(R.id.btnBack);

        // Заполнение списка
        itemsList = new ArrayList<>();
        if (category.equals("Фрукты")) {
            itemsList.addAll(Arrays.asList(getResources().getStringArray(R.array.fruits)));
        } else if (category.equals("Овощи")) {
            itemsList.addAll(Arrays.asList(getResources().getStringArray(R.array.vegetables)));
        } else {
            itemsList.addAll(Arrays.asList(getResources().getStringArray(R.array.berries)));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        listView.setAdapter(adapter);

        // Добавление
        btnAdd.setOnClickListener(v -> {
            if (category.equals("Фрукты")) {
                String newItem = etNewItem.getText().toString().trim();
                if (!newItem.isEmpty()) {
                    itemsList.add(newItem);
                    adapter.notifyDataSetChanged();
                    etNewItem.setText("");
                }
            }
        });

        // Удаление выбранного элемента
        btnRemove.setOnClickListener(v -> {
            int pos = listView.getCheckedItemPosition();
            if (pos != ListView.INVALID_POSITION) {
                itemsList.remove(pos);
                adapter.notifyDataSetChanged();
                listView.clearChoices();
            }
        });

        btnBack.setOnClickListener(v -> finish());
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}