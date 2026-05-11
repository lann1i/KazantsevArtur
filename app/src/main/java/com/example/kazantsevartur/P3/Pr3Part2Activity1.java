package com.example.kazantsevartur.P3;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kazantsevartur.AnalyticsFragment;
import com.example.kazantsevartur.DashboardFragment;
import com.example.kazantsevartur.ProfileFragment;
import com.example.kazantsevartur.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pr3Part2Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pr3_part21);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DashboardFragment()).commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Обзор");
        }

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.dashboard) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new DashboardFragment()).commit(); //создаем новый DashboardFragment и заменяем содердимое контейнера
                    if (getSupportActionBar() != null) getSupportActionBar().setTitle("Обзор");
                } else if (item.getItemId() == R.id.analytics) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AnalyticsFragment()).commit();
                    if (getSupportActionBar() != null) getSupportActionBar().setTitle("Аналитика");
                } else if (item.getItemId() == R.id.profile) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProfileFragment()).commit();
                    if (getSupportActionBar() != null) getSupportActionBar().setTitle("Профиль");
                }
                return true;
            }
        });
    }
}