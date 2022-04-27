package com.android.alarmy_test2;

import static java.time.DayOfWeek.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationBarView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Boolean True = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Intent gửi đi
        Intent intentReceiver = new Intent(MainActivity.this, AlarmReceiver.class);
        ArrayList<Boolean> myList = (ArrayList<Boolean>) getIntent().getSerializableExtra("list");
        int hour = (int) getIntent().getIntExtra("hour", -1);
        int minute = (int) getIntent().getIntExtra("minute", -1);
        Log.d("----------Calendar on MainActivity-------- ", String.valueOf(calendar));
        Log.d("----------Hour on MainActivity-------- ", String.valueOf(hour));
        Log.d("----------Minute on MainActivity-------- ", String.valueOf(minute));
        if (myList != null) {
            for (int i = 0; i < myList.size(); i++) {
                int temp = i + 1;
                if (myList.get(i) == True) {
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intentReceiver, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    Log.d("Đã set", String.valueOf(calendar));

                }
            }
        }
    }

    private NavigationBarView.OnItemSelectedListener navListener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_record:
                            selectedFragment = new RecordFragment();
                            break;
                        case R.id.nav_news:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.nav_setting:
                            selectedFragment = new SettingFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}