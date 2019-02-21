package com.example.a073105.daggerdemo.beans;

import com.example.a073105.daggerdemo.dagger2.annotation.ActivityScope;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Bean1 {

    private String id;

    @Inject
    public Bean1(String id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "Bean1{" +
//                "id='" + id + '\'' +
//                '}';
//    }
}
