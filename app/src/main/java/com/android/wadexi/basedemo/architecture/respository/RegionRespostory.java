package com.android.wadexi.basedemo.architecture.respository;

import android.arch.lifecycle.LiveData;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.android.wadexi.basedemo.architecture.respository.db.AppDB;
import com.android.wadexi.basedemo.architecture.respository.db.RoomExecutors;
import com.android.wadexi.basedemo.beans.region.RegionProvince;
import com.android.wadexi.basedemo.utils.Utils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.internal.bind.TreeTypeAdapter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegionRespostory {

    private static final String TAG = "RegionRespostory";

    private RoomExecutors roomExecutors;
    private AppDB appDB;

    @Inject
    public RegionRespostory(AppDB appDB,RoomExecutors roomExecutors) {
        this.appDB = appDB;
        this.roomExecutors = roomExecutors;
    }


    /**
     * 添加省份
     */
    public void addRegionProvince(){


        roomExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDB.regionProvinceDao().save(new RegionProvince(11,"北京市",0));
            }
        });

    }

    /**
     * 添加省份
     */
    public LiveData<List<RegionProvince>> queryRegionProvince(){

        return appDB.regionProvinceDao().query();
//        Future<List<RegionProvince>> future = roomExecutors.getDiskIO().submit(new Callable<List<RegionProvince>>() {
//
//            @Override
//            public List<RegionProvince> call() throws Exception {
//                if (Utils.isMainTread()) {
//                    Log.d(TAG, "queryRegionProvince: isMainThread == true" );
//                }else {
//                    Log.d(TAG, "queryRegionProvince: isMainThread == false" );
//                }
//                Thread.sleep(1000*5);
//                List<RegionProvince> query =
//                return query;
//            }
//        });
//
////        if (Utils.isMainTread()) {
////            Log.d(TAG, "queryRegionProvince: isMainThread == true" );
////        }
////        CompletableFuture
//
//        ListenableFuture
//
//        List<RegionProvince> regionProvinces = null;
//        try {
//            regionProvinces = future.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//
//            return regionProvinces;
//        }



    }


}
