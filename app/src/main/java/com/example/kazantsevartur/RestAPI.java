package com.example.kazantsevartur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class RestAPI extends AppCompatActivity {

    private ImageView imageView;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        imageView = findViewById(R.id.imageView);
        Button btnLoadImage = findViewById(R.id.btnLoadImage);
        btnLoadImage.setOnClickListener(v -> loadRandomDogImage());
    }

    private void loadRandomDogImage() {
        Request request = new Request.Builder()
                .url("https://random.dog/woof.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(RestAPI.this, "Ошибка сети", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(() -> Toast.makeText(RestAPI.this, "Ошибка сервера", Toast.LENGTH_SHORT).show());
                    return;
                }
                try {
                    String jsonData = response.body().string();
                    String imageUrl = new JSONObject(jsonData).getString("url");
                    runOnUiThread(() -> Glide.with(RestAPI.this).load(imageUrl).into(imageView));
                } catch (JSONException e) {
                    runOnUiThread(() -> Toast.makeText(RestAPI.this, "Ошибка данных", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}