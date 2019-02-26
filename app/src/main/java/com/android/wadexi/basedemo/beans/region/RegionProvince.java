package com.android.wadexi.basedemo.beans.region;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "region_province")
public class RegionProvince {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private int id;//当前区域id(省id 市id 区id)

    @ColumnInfo(name = "name")
    private String name;//省市区名称

//    @ForeignKey(entity = RegionProvinceDao.class,parentColumns = "id",childColumns = "pId")
    @ColumnInfo(name = "pId")
    private int pId;//上级区域id(省id，市id)


    public RegionProvince(int id, String name, int pId) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

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
