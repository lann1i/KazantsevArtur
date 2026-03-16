package com.example.kazantsevartur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.io.Serializable;

public class Pr2_Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_KazantsevArtur);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pr2_activity1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2,
                SecondFragment.class, null).commit();
    }

    public void onNextActivity4(View view) {
        EditText text = findViewById(R.id.editTextText3);
        String fullName = text.getText().toString();
        Intent intent = new Intent(this, Pr2_Activity2.class);
        intent.putExtra("fullName", fullName);
        startActivity(intent);
    }

    public void Fragment1(View v) {
        replaceFragment(new FirstFragment());
    }

    public void Fragment2(View v) {
        replaceFragment(new SecondFragment());
    }

    public void Fragment3(View v) {
        replaceFragment(new ThirdFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout2, fragment);
        fragmentTransaction.commit();
    }
}


