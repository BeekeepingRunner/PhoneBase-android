package com.example.phonebase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;

    @NonNull
    @ColumnInfo(name = "androidVersion")
    private String androidVersion;

    @NonNull
    @ColumnInfo(name = "site")
    private String site;

    public Phone(@NonNull String manufacturer, @NonNull String model,
                 @NonNull String androidVersion, @NonNull String site) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NonNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(@NonNull String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @NonNull
    public String getSite() {
        return site;
    }

    public void setSite(@NonNull String site) {
        this.site = site;
    }
}
