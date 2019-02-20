package com.example.a073105.daggerdemo.dagger2.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Provides
    @Named("HomeFragmentString")
    static String provideHomeFragmentString(){
        return "HomeFragmentString";
    }
}
