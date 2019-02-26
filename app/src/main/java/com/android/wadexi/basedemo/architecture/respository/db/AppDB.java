package com.android.wadexi.basedemo.architecture.respository.db;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.android.wadexi.basedemo.annotates.AssetsPath;
import com.android.wadexi.basedemo.architecture.respository.db.dao.RegionAreaDao;
import com.android.wadexi.basedemo.architecture.respository.db.dao.RegionCityDao;
import com.android.wadexi.basedemo.architecture.respository.db.dao.RegionProvinceDao;
import com.android.wadexi.basedemo.beans.region.RegionArea;
import com.android.wadexi.basedemo.beans.region.RegionCity;
import com.android.wadexi.basedemo.beans.region.RegionProvince;
import com.android.wadexi.basedemo.exceptions.MySqlException;
import com.android.wadexi.basedemo.utils.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 通过为应用实现 RoomDatabase 来创建一个数据库类
 * 注意该类为抽象类
 */
@Database(entities = {RegionProvince.class,RegionCity.class,RegionArea.class},version = 1,exportSchema = false)
public abstract class AppDB extends RoomDatabase {



    private static volatile AppDB INSTANCE;


    public static AppDB getDatabase(final Context context) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/basedemo/demodatabase.db";
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                             AppDB.class, path)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract RegionProvinceDao regionProvinceDao();

    public abstract RegionCityDao regionCityDao();

    public abstract RegionAreaDao regionAreaDao();


    /**
     * 执行sql脚本
     */
    @SuppressLint("StaticFieldLeak")

    public LiveData<Boolean> executeSqlFile(Context context,@AssetsPath String path,
                                            LifecycleOwner lifecycleOwner,Observer<Boolean> observer)
                                            throws MySqlException{


        List<String> sqls = new ArrayList<>();

        BufferedReader bufReader = null;
        try {
            InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(path),"UTF-8" );
            bufReader = new BufferedReader(inputReader);
            String sql="";
            while((sql = bufReader.readLine()) != null){
//                String sql = bufReader.readLine();
                sqls.add(sql);
            }
        } catch (Exception e) {
            throw new MySqlException("请输入正确的路径（assets目录下的sql脚本文件路径）",e);
        }finally {
            if(Utils.checkNonNull(bufReader)){
                try {
                    assert bufReader != null;
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

//        BufferedReader streamReader =null;
//        try {
////            StandardCharsets.UTF_8
//            streamReader = new BufferedReader(new FileReader(context.getAssets().openFd(path).getFileDescriptor()));
//
//            while (streamReader.readLine() != null){
//                String sql = streamReader.readLine();
//                sqls.add(sql);
//            }
//        } catch (IOException e) {
//           throw new MySqlException("请输入正确的路径（assets目录下的sql脚本文件路径）",e);
//        }finally {
//            if(Utils.checkNonNull(streamReader)){
//                try {
//                    assert streamReader != null;
//                    streamReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        liveData.observe(lifecycleOwner,observer);
        new AsyncTask<Void,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Void[] objects) {
                SupportSQLiteDatabase mDatabase = INSTANCE.mDatabase;

                try {
                    mDatabase.beginTransaction();
                    for (String sql:sqls) {
                        mDatabase.execSQL(sql);
                    }
                    mDatabase.setTransactionSuccessful();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDatabase.endTransaction();
                }
               return false;

            }

            @Override
            protected void onPostExecute(Boolean o) {
                super.onPostExecute(o);
                liveData.setValue(o);
            }
        }.execute();

        return liveData;

    }

}
