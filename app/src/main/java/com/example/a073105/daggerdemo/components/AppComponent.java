package com.example.a073105.daggerdemo.components;

import com.example.a073105.daggerdemo.application.MyApplication;
import com.example.a073105.daggerdemo.beans.Bean1;
import com.example.a073105.daggerdemo.modules.AppModules;
import com.example.a073105.daggerdemo.modules.SubComponentModule;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
            AndroidSupportInjectionModule.class,
            AppModules.class,
            SubComponentModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {


//    @Component.Builder
//    abstract class Builder extends AndroidInjector.Builder<MyApplication>{
//
//    }



    Bean1 getBean1();

}
