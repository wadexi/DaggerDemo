package com.android.wadexi.basedemo.beans.region;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "region_cities",foreignKeys = @ForeignKey(entity = RegionProvince.class,parentColumns = "id",childColumns = "pId"))
public class RegionCity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private int id;//城市id

    @ColumnInfo(name = "name")
    private String name;//城市名称

//    @ForeignKey(entity = RegionProvince.class,parentColumns = "id",childColumns = "pId")
    @ColumnInfo(name = "pId")
    private int pId;//上级区域id(省id)


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "RegionProvinceDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pId=" + pId +
                '}';
    }
}
