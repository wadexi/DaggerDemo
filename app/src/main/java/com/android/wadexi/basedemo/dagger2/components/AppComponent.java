package com.android.wadexi.basedemo.dagger2.components;

import com.android.wadexi.basedemo.application.MyApplication;
import com.android.wadexi.basedemo.dagger2.modules.app.AppModules;
import com.android.wadexi.basedemo.dagger2.modules.activity.SubComponentModule;
import com.android.wadexi.basedemo.dagger2.modules.dbroom.RoomModule;
import com.android.wadexi.basedemo.dagger2.modules.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
            AppModules.class,
            SubComponentModule.class,
            ViewModelModule.class,
            RoomModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }



}
