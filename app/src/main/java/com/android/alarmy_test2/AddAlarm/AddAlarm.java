package com.android.alarmy_test2.AddAlarm;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.alarmy_test2.Data.Alarm;
import com.android.alarmy_test2.Data.AlarmDao;
import com.android.alarmy_test2.Data.AlarmDatabase;
import com.android.alarmy_test2.Data.AlarmRepository;
import com.android.alarmy_test2.MainActivity;
import com.android.alarmy_test2.R;
import com.android.alarmy_test2.Service.RescheduleAlarmsService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class AddAlarm extends AppCompatActivity {
    TimePicker timePicker;
    ImageView backBtn, playRingtoneBtn;
    TextView timeEstimate;
    private boolean isPlay;
    private boolean Test;
    private AlarmRepository alarmRepository;
    Calendar calendar;
    Button btnSave;
    private LiveData<List<Alarm>> alarmsLiveData;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<Boolean> checkBtn = new ArrayList<Boolean>();
    Boolean True = true;
    CheckBox daily;
    Boolean recurring;
    private CreateAlarmViewModel createAlarmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel.class);
        setContentView(R.layout.activity_add_alarm);
        timePicker = findViewById(R.id.timePicker);
        btnSave = findViewById(R.id.extended_fab);
        backBtn = findViewById(R.id.back_pressed);
        timeEstimate = findViewById(R.id.timeEstimate);
        daily = findViewById(R.id.daily);
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
        //Take TimePicker
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            }
        });
        calendar = Calendar.getInstance();
        //When user click save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            // Kiem tra lich bao thuc
            public void onClick(View view) {
                for (int i = 0; i < buttons.size(); i++) {
                    if(buttons.get(i).getTag()==null)
                    {
                        checkBtn.add(false);
                    }
                    else {
                        recurring=true;
                        checkBtn.add((Boolean) buttons.get(i).getTag());
                    }
                }
                scheduleAlarm();
            }
        });
    }
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);
        String title = "Alarm";
//        for (int i = 0; i < buttons.size(); i++) {
//            Log.d("check", String.valueOf(checkBtn.get(i)));
//        }
        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(timePicker),
                TimePickerUtil.getTimePickerMinute(timePicker),
                title,
                true,
                recurring,
                daily.isChecked(),
                checkBtn.get(0),
                checkBtn.get(1),
                checkBtn.get(2),
                checkBtn.get(3),
                checkBtn.get(4),
                checkBtn.get(5),
                checkBtn.get(6)
        );
        Log.d("LOG --- Tới đây rồi", String.valueOf(recurring));
        createAlarmViewModel.insert(alarm);
        Log.d("LOG --- PASS Create Alarm Model --- with alarm =>", String.valueOf(alarm)+"---"+recurring+"-----"+daily.isChecked());
        alarm.schedule(getBaseContext());
        Log.d("LOG --- PASS Schedule =>", "Đã pass");
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