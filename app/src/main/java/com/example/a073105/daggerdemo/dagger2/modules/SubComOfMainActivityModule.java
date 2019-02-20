package com.example.a073105.daggerdemo.dagger2.modules;

import com.example.a073105.daggerdemo.fragments.contacts.ConstantFragment;
import com.example.a073105.daggerdemo.fragments.HomeFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(

)
public abstract class SubComOfMainActivityModule {


    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = ContactFragmentModule.class)
    abstract ConstantFragment contributeContactFragment();


}
