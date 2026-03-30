package com.example.kazantsevartur;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                            .replace(R.id.fragment_container, new DashboardFragment()).commit();
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