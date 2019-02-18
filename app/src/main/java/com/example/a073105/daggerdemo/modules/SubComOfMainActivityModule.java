package com.example.a073105.daggerdemo.modules;

import com.example.a073105.daggerdemo.fragments.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SubComOfMainActivityModule {


    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragment();


}
