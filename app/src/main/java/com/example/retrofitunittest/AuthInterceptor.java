package com.example.retrofitunittest;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class add information (API Key) to {@link okhttp3.OkHttpClient} which is passed in {@link TmdbApiClient#getRestAdapter()} which is required when making a request.
 * 이 클래스는 요청 시 필요한 정보(API Key)를 {@link TmdbApiClient#getRestAdapter()}로 전달되는 {@link okhttp3.OkHttpClient}에 추가합니다.
 *
 * @author Thomas Kioko
 */
public class AuthInterceptor implements Interceptor {

    /**
     * Default constructor.
     */
    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", "88b7a3f84f260070a5ab73f2af65ffe1")   // API key를 안전하게 보관하는 방법?
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
