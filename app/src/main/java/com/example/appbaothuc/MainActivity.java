package com.example.appbaothuc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    Button btnHenGio, btnDungLai;
    TextView txtHienThi;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        onClick();
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);//hàm báo thức
        intent = new Intent(MainActivity.this, AlarmReceiver.class);

    }

    private void mapping() {
        btnHenGio = findViewById(R.id.btnHenGio);
        btnDungLai = findViewById(R.id.btnDungLai);
        timePicker = findViewById(R.id.timePicker);
        txtHienThi = findViewById(R.id.textview);

    }

    private void onClick() {
        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();

                String string_gio = String.valueOf(gio);
                String string_phut = String.valueOf(phut);
                if (gio > 12) {
                    string_gio = String.valueOf(gio - 12);
                }
                if (phut < 10) {
                    string_phut = "0" + String.valueOf(phut);
                }
                intent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                txtHienThi.setText("Giờ bạn đặt là " + string_gio + ":" + string_phut);

            }
        });
        btnDungLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHienThi.setText("Dừng lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
            }
        });
    }
}
