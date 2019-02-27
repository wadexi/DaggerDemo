package com.android.wadexi.basedemo.architecture.respository.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.android.wadexi.basedemo.beans.region.RegionProvince;

import java.util.List;

/**
 * 创建一个数据访问对象 (DAO)
 * 定义 UserDao 类后，从我们的数据库类引用该 DAO：
 */
@Dao
public interface RegionProvinceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(RegionProvince regionProvince);

//    @Insert("INSERT INTO `region_province` VALUES (12, '天津市', 0);")
//    void addProvince();


    @Query("select * from region_province")
    LiveData<List<RegionProvince>> query();//注意此处返回的的livedata数据
}
