package com.example.kazantsevartur.P4.Part1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kazantsevartur.R;

import java.util.Calendar;

public class P4Z2 extends AppCompatActivity {
    private TextView tvAlertResult, tvDateResult, tvTimeResult, tvCustomResult;
    private String selectedDate = "не выбрана";
    private String selectedTime = "не выбрано";
    private String enteredName = "не введено";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4_z2);

        tvAlertResult = findViewById(R.id.tvAlertResult);
        tvDateResult = findViewById(R.id.tvDateResult);
        tvTimeResult = findViewById(R.id.tvTimeResult);
        tvCustomResult = findViewById(R.id.tvCustomResult);

        // 1. AlertDialog
        findViewById(R.id.btnAlertDialog).setOnClickListener(v -> showAlertDialog());

        // 2. DatePickerDialog
        findViewById(R.id.btnDatePicker).setOnClickListener(v -> showDatePickerDialog());

        // 3. TimePickerDialog
        findViewById(R.id.btnTimePicker).setOnClickListener(v -> showTimePickerDialog());

        // 4. Custom Dialog
        findViewById(R.id.btnCustomDialog).setOnClickListener(v -> showCustomDialog());
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Подтверждение");
        builder.setMessage("Вы хотите перейти к таймеру с выбранными данными?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Да", (dialog, which) -> {
            Intent intent = new Intent(P4Z2.this, P4Part1.class);
            intent.putExtra("selectedDate", selectedDate);
            intent.putExtra("selectedTime", selectedTime);
            intent.putExtra("enteredName", enteredName);
            startActivity(intent);
            tvAlertResult.setText("AlertDialog: переход к таймеру");
        });
        builder.setNegativeButton("Нет", (dialog, which) -> {
            dialog.dismiss();
            tvAlertResult.setText("AlertDialog: отмена");
        });
        builder.show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    tvDateResult.setText("Дата: " + selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    selectedTime = hourOfDay + ":" + (minute1 < 10 ? "0" + minute1 : minute1);
                    tvTimeResult.setText("Время: " + selectedTime);
                }, hour, minute, true);
        timePickerDialog.show();
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Ввод имени");

        EditText etName = dialog.findViewById(R.id.etCustomName);
        Button btnOk = dialog.findViewById(R.id.btnCustomOk);

        btnOk.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                enteredName = name;
                tvCustomResult.setText("Custom: введено имя - " + enteredName);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

}