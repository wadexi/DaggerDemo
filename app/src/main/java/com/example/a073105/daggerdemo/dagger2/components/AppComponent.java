package com.example.a073105.daggerdemo.dagger2.components;

import com.example.a073105.daggerdemo.application.MyApplication;
import com.example.a073105.daggerdemo.dagger2.modules.app.AppModules;
import com.example.a073105.daggerdemo.dagger2.modules.activity.SubComponentModule;
import com.example.a073105.daggerdemo.dagger2.modules.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
            AppModules.class,
            SubComponentModule.class,
            ViewModelModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }



}
