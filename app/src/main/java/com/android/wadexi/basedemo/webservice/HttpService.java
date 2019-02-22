package com.android.wadexi.basedemo.webservice;

import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.beans.ResponBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HttpService {


    @FormUrlEncoded
    @POST("cook/query.php")
    Call<ResponBean<CookBookBean>> getCookMenu(@Field("menu") String menu, @Field("rn") int maxNum,
                                             @Field("key") String key);
}
