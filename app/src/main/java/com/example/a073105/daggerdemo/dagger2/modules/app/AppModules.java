package com.example.a073105.daggerdemo.dagger2.modules.app;

import android.app.Application;

import com.example.a073105.daggerdemo.application.MyApplication;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;


@Module(
        includes = AndroidSupportInjectionModule.class
)
public abstract class AppModules {


    @Provides
    static String provideString1(){
        return"1";
    }

//    @Singleton
//    @Provides
//    @Named("SettingActivity_title")
//    static String provideSettingActivityTitle(){
//        return String.valueOf(new Random(System.currentTimeMillis()).nextInt());
//    }

    @Binds
//    @Singleton
    // Singleton annotation isn't necessary (in this case since Application instance is unique)
    // but is here for convention.
    abstract Application application(MyApplication app);


}
