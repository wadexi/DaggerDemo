package com.android.wadexi.basedemo.beans;

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
