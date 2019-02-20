package com.example.a073105.daggerdemo.dagger2.modules;

import android.app.Application;

import com.example.a073105.daggerdemo.application.MyApplication;
import com.example.a073105.daggerdemo.beans.Bean1;
//import com.example.a073105.daggerdemo.dagger2.components.MainActivityComponent;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(/*subcomponents = {
        MainActivityComponent.class
    }*/
)
public abstract class AppModules {


    @Provides
    static String provideString1(){
        return"1";
    }


//    @Binds
//    @Singleton
//    // Singleton annotation isn't necessary (in this case since Application instance is unique)
//    // but is here for convention.
//    abstract Application application(MyApplication app);



}
