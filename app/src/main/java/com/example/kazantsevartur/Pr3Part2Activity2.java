package com.example.kazantsevartur;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Pr3Part2Activity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;  // Поле класса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr3_part22);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new TasksFragment())
                .commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        // БЕЗ типа - присваиваем полю класса, а не создаём локальную переменную
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open, R.string.close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_tasks) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new TasksFragment()).commit();
        } else if (item.getItemId() == R.id.nav_finance) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new FinanceFragment()).commit();
        } else if (item.getItemId() == R.id.nav_second_activity) {
            startActivity(new Intent(this, Pr3Part2Activity1.class));
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {  // Теперь toggle не null
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}