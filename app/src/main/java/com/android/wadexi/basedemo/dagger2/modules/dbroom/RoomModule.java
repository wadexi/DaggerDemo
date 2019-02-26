package com.android.wadexi.basedemo.dagger2.modules.dbroom;

import android.app.Application;

import com.android.wadexi.basedemo.architecture.respository.db.AppDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {


    @Provides
    @Singleton
    static AppDB provideAppDB(Application application){
        return AppDB.getDatabase(application);
    }
}
