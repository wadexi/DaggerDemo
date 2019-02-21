package com.example.a073105.daggerdemo.dagger2.modules.activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingActivityModule {

    @Provides
    @Named("SettingActivity_title")
    static String provideSettingActivityTitle(){
        return "SettingActivity_title";
    }

}
