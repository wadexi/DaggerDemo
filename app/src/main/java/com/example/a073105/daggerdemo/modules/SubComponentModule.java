package com.example.a073105.daggerdemo.modules;

import com.example.a073105.daggerdemo.activities.MainActivity;
import com.example.a073105.daggerdemo.activities.SettingActivity;
import com.example.a073105.daggerdemo.components.MainActivityComponent;
import com.example.a073105.daggerdemo.components.SettingActivityModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class SubComponentModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?>
                                bindMainActivityInjectorFactory(MainActivityComponent.Builder builder);


    @ContributesAndroidInjector(modules = SettingActivityModule.class)
    abstract SettingActivity contributeSettingActivity();
}
