package com.example.a073105.daggerdemo.dagger2.components;

import android.app.Application;

import com.example.a073105.daggerdemo.application.MyApplication;
import com.example.a073105.daggerdemo.beans.Bean1;
import com.example.a073105.daggerdemo.dagger2.modules.AppModules;
import com.example.a073105.daggerdemo.dagger2.modules.SubComponentModule;
import com.example.a073105.daggerdemo.dagger2.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.internal.Preconditions;

@Singleton
@Component(modules = {
            AndroidSupportInjectionModule.class,
            AppModules.class,
            SubComponentModule.class,
            ViewModelModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {


    @Component.Builder
    interface Builder /*extends AndroidInjector.Builder<MyApplication>*/{

        @BindsInstance
        Builder application(MyApplication application);

        AppComponent build();

//        Builder application(Application application);

//        AppComponent build();
//        void application(MyApplication application){
//            seedInstance(application);
//        }

//        @Override
//        public AppComponent build() {
//            Preconditions.checkBuilderRequirement(seedInstance, MyApplication.class);
//            return new DaggerAppComponent(this);
//        }



//        @Override
//        public AndroidInjector<MyApplication> build() {
//           application();
//            return new ;
//        }
    }



//    Bean1 getBean1();

}
