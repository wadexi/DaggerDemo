package com.example.a073105.daggerdemo.modules;

import com.example.a073105.daggerdemo.beans.Bean1;
import com.example.a073105.daggerdemo.components.MainActivityComponent;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {
        MainActivityComponent.class
})
public class AppModules {

    @Provides
    static String provideString1(){
        return"1";
    }



}
