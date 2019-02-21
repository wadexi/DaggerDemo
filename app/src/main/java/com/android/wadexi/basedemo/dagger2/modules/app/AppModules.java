package com.android.wadexi.basedemo.dagger2.modules.app;

import android.app.Application;

import com.android.wadexi.basedemo.BuildConfig;
import com.android.wadexi.basedemo.application.MyApplication;
import com.android.wadexi.basedemo.webservice.HttpService;
import com.android.wadexi.basedemo.webservice.okhttp.LoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(
        includes = AndroidSupportInjectionModule.class
)
public abstract class AppModules {

    private static final long DEFAULT_TIMEOUT = 10;

    @Binds
//    @Singleton
    // Singleton annotation isn't necessary (in this case since Application instance is unique)
    // but is here for convention.
    abstract Application application(MyApplication app);

    @Provides
    static String provideString1(){
        return"1";
    }

    @Provides
    @Named("juHeAppKey")
    @Singleton
    static String provideJuHeAppKey(MyApplication application){
        String juHeAppKey = "";
        try {
            Properties prop = new Properties();
            InputStream open = application.getAssets().open("setttings.properties");
            prop.load(open);
            juHeAppKey = prop.getProperty("juHeAppKey");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return juHeAppKey;
    }


    @Provides
    @Singleton
    static Retrofit provideRetrofit(final Application application){

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //add log record
//        if (BuildConfig.DEBUG) {
//            //打印网络请求日志
//            LoggingInterceptor interceptor = new LoggingInterceptor.Builder()
//                    .loggable(BuildConfig.DEBUG)
//                    .setLevel(Level.BASIC)
//                    .log(Platform.INFO)
//                    .request("Request")
//                    .response("Response")
//                    .addHeader("version", BuildConfig.VERSION_NAME)
//                    .addQueryParam("query", "0")
//                    /*.enableMock(true, 1000L, new BufferListener() {
//                        @Override
//                        public String getJsonResponse(Request request) throws IOException {
//                            String segment = request.url().pathSegments().get(0);
//                            return Okio.buffer(Okio.source(application.getAssets().open(String.format("mock/%s.json", segment)))).readUtf8();
//                        }
//                    })*/.build();
//
//            httpClientBuilder.addInterceptor(interceptor);
//        }

        httpClientBuilder.addInterceptor(new LoggingInterceptor());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        builder.addInterceptor(new EncryptInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
//                .addConverterFactory(LenientGsonConverterFactory.create())
                .build();

        return retrofit;


    }

    @Provides
    @Singleton
    static HttpService provideHttpService( Retrofit retrofit){
        return retrofit.create(HttpService.class);
    }









    //    @Singleton
//    @Provides
//    @Named("SettingActivity_title")
//    static String provideSettingActivityTitle(){
//        return String.valueOf(new Random(System.currentTimeMillis()).nextInt());
//    }
}
