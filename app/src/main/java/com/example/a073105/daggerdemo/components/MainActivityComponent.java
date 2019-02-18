package com.example.a073105.daggerdemo.components;

import com.example.a073105.daggerdemo.activities.MainActivity;
import com.example.a073105.daggerdemo.modules.MainActivityModule;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {
        MainActivityModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

    }


    @Named("mainactivity_string")
    String getMainActivityString();


}
