package com.android.wadexi.basedemo.dagger2.modules.fragment;

import com.android.wadexi.basedemo.architecture.ui.fragments.ConstantFragment;
import com.android.wadexi.basedemo.architecture.ui.fragments.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(

)
public abstract class SubComOfMainActivityModule {


    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = ContactFragmentModule.class)
    abstract ConstantFragment contributeContactFragment();


}
