package com.android.wadexi.basedemo.webservice.okhttp;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class EncryptInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //这个是请求的url，也就是咱们前面配置的baseUrl
        String url = request.url().toString();
        //这个是请求方法
        String method = request.method();
        long t1 = System.nanoTime();
//        request = encrypt(request);//模拟的加密方法
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
//        response = decrypt(response);
        return response;
    }

    //加密
    private Request encrypt(Request request) throws IOException {
        //获取请求body，只有@Body 参数的requestBody 才不会为 null
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            okio.Buffer buffer = new okio.Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }

            String string = buffer.readString(charset);
            //模拟加密的方法，这里调用大家自己的加密方法就可以了
            String encryptStr = encrypt(string);
            RequestBody body = MultipartBody.create(contentType, encryptStr);
            request = request.newBuilder()
                    .post(body)
                    .build();

        }
        return request;
    }

    //模拟加密的方法
    private String encrypt(String string) {
        return "我是密文：" + string;
    }

    private Response decrypt(Response response) throws IOException {
        if (response.isSuccessful()) {
            //the response data
            ResponseBody body = response.body();
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String string = buffer.clone().readString(charset);
            //解密方法，需要自己去实现
            String bodyString = decrypt(string);
            ResponseBody responseBody = ResponseBody.create(contentType, bodyString);
            response = response.newBuilder().body(responseBody).build();
        }
        return response;
    }


    //模拟解密的方法
    private String decrypt(String string) {
        if (string != null && string.length() != 0) {
            string.replace("我是密文：", "");
        }
        return string;
    }


}
