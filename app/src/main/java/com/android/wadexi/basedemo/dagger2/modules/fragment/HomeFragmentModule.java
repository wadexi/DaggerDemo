package com.android.wadexi.basedemo.dagger2.modules.fragment;

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
