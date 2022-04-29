package com.android.alarmy_test2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class AddAlarm extends AppCompatActivity {
    TimePicker timePicker;
    ImageView backBtn, playRingtoneBtn;
    TextView timeEstimate;
    private boolean isPlay;
    private boolean Test;
    Calendar calendar;
    Button btnSave;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<Boolean> checkBtn = new ArrayList<Boolean>();
    Boolean True = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        btnSave = findViewById(R.id.extended_fab);
        backBtn = findViewById(R.id.back_pressed);
        timeEstimate = findViewById(R.id.timeEstimate);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (buttons.size() == 0) {
            buttons.add((Button) findViewById(R.id.mondayBtn));
            buttons.add((Button) findViewById(R.id.tuesdayBtn));
            buttons.add((Button) findViewById(R.id.wednesdayBtn));
            buttons.add((Button) findViewById(R.id.thursdayBtn));
            buttons.add((Button) findViewById(R.id.fridayBtn));
            buttons.add((Button) findViewById(R.id.saturdayBtn));
            buttons.add((Button) findViewById(R.id.sundayBtn));
        }
        Intent intent = new Intent(AddAlarm.this, AlarmReceiver.class);
        Intent intentBtn = new Intent(AddAlarm.this, MainActivity.class);
        Bundle bundle = new Bundle();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAlarm.super.onBackPressed();
            }
        });

        isPlay = false;
        playRingtoneBtn = findViewById(R.id.play_ring_tone);
        playRingtoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) {
                    playRingtoneBtn.setImageResource(R.drawable.ic_play);
                    isPlay = false;
                } else {
                    playRingtoneBtn.setImageResource(R.drawable.ic_stop);
                    isPlay = true;
                }
            }
        });
        Log.d("Button day", buttons.get(0).toString());
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            }
        });
        calendar = Calendar.getInstance();
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            // Kiem tra lich bao thuc
            public void onClick(View view) {
//                for (int i = 0; i < buttons.size(); i++) {
//                    int temp=i+1;
//                    if(buttons.get(i).getTag()==True ){
//                        calendar.set(Calendar.DAY_OF_WEEK, temp);
//                        Log.d("Kiem tra lich bao thuc", String.valueOf(temp));
//                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
//                        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
//                        pendingIntent = PendingIntent.getBroadcast(AddAlarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                    }
//                }
//                int hourEstimate = hour-currentHour;
//                int minuteEstimate = minute-currentMinute;
//                if (hourEstimate < 0 ){
//                    hourEstimate = 24 + hourEstimate;
//                }
//                String HourEstimate = String.valueOf(hourEstimate);
//                String MinuteEstimate = String.valueOf(minute-currentMinute);
//                timeEstimate.setText("Báo thức được đặt trong " +HourEstimate+" giờ "+MinuteEstimate+" phút tính từ bây giờ");
                for (int i = 0; i < buttons.size(); i++) {
                    checkBtn.add((Boolean) buttons.get(i).getTag());
                }
                intentBtn.setAction(Intent.ACTION_SEND);
                intentBtn.putExtra("list",checkBtn);
                intentBtn.putExtra("hour",timePicker.getCurrentHour());
                intentBtn.putExtra("minute",timePicker.getCurrentMinute());
                startActivity(intentBtn);
            }
        });
    }


    public void takeBtn(View view) {
        int index = 0;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getId() == view.getId()) {
                index = i;
                if ((buttons.get(i).getTag() == null) || (buttons.get(i).getTag() != True)) {
                    buttons.get(i).setTag(true);
                    buttons.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.dayBtn));
                } else {
                    buttons.get(i).setTag(false);
                    buttons.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundbtn_alarm));
                }
                break;
            }
        }
    }
}