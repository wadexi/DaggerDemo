package com.android.wadexi.basedemo.dagger2.modules.app;

import android.app.Application;

import com.android.wadexi.basedemo.application.MyApplication;
import com.android.wadexi.basedemo.webservice.HttpService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(
        includes = AndroidSupportInjectionModule.class
)
public abstract class AppModules {

    @Binds
//    @Singleton
    // Singleton annotation isn't necessary (in this case since Application instance is unique)
    // but is here for convention.
    abstract Application application(MyApplication app);

    @Provides
    static String provideString1(){
        return"1";
    }


    @Provides
    @Singleton
    @Named("house_api")
    static Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;


    }

    @Provides
    @Named("house_api")
    @Singleton
    static HttpService provideHttpService(@Named("house_api") Retrofit retrofit){
        return retrofit.create(HttpService.class);
    }









    //    @Singleton
//    @Provides
//    @Named("SettingActivity_title")
//    static String provideSettingActivityTitle(){
//        return String.valueOf(new Random(System.currentTimeMillis()).nextInt());
//    }
}
