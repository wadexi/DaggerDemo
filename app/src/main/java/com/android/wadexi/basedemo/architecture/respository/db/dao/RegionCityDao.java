package com.android.wadexi.basedemo.architecture.respository.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.android.wadexi.basedemo.beans.region.RegionCity;
import com.android.wadexi.basedemo.beans.region.RegionProvince;

import java.util.List;

/**
 * 创建一个数据访问对象 (DAO)
 * 定义 Dao 类后，从我们的数据库类引用该 DAO：
 */
@Dao
public interface RegionCityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(RegionCity regionCity);


    @Query("select * from region_cities")
    LiveData<List<RegionCity>> query();//注意此处返回的的livedata数据
}
