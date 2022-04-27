package com.android.alarmy_test2.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("SELECT * FROM alarm_table ")
    LiveData<List<Alarm>> getAlarms();

    @Query("SELECT * FROM alarm_table ")
    List<Alarm> getList();

    @Update
    void update(Alarm alarm);
}
