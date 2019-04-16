package com.papps.freddy_lazo.data.local.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "notifications")
public class NotificationTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "appointment_id")
    private String appointment_id;
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "from_doctor")
    private boolean from_doctor;

    public NotificationTable(String type, String appointment_id, String message, String time, boolean from_doctor) {
        this.type = type;
        this.appointment_id = appointment_id;
        this.message = message;
        this.time = time;
        this.from_doctor = from_doctor;
    }

    public boolean isFrom_doctor() {
        return from_doctor;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }


}
