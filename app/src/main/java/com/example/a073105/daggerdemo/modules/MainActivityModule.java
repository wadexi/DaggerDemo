package com.example.a073105.daggerdemo.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {

})
public class MainActivityModule {


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

}
