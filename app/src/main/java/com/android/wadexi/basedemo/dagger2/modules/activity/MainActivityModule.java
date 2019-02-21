package com.android.wadexi.basedemo.dagger2.modules.activity;

import com.android.wadexi.basedemo.dagger2.modules.fragment.ContactFragmentModule;
import com.android.wadexi.basedemo.dagger2.modules.fragment.HomeFragmentModule;
import com.android.wadexi.basedemo.architecture.ui.fragments.HomeFragment;
import com.android.wadexi.basedemo.architecture.ui.fragments.ConstantFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module()
public abstract class MainActivityModule {


//    private String title;
//
//    public MainActivityModule(String title) {
//        this.title = title;
//    }

    @Provides
    @Named("mainactivity_string")
    static String provideString(){
        return "mainactivity_title";
    }


    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = ContactFragmentModule.class)
    abstract ConstantFragment contributeContactFragment();



}
