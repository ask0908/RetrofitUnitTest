package com.example.retrofitunittest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiClient {

    private Retrofit retrofit;
    private boolean isDebug;
    private final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    /**
     * Set the {@link Retrofit} log level.
     *
     * @param isDebug If true, the log level is set to
     *                {@link HttpLoggingInterceptor.Level#BODY}. Otherwise
     *                {@link HttpLoggingInterceptor.Level#NONE}.
     */
    public TmdbApiClient setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        if (retrofit != null) {
            httpLoggingInterceptor.
                    setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        }
        return this;
    }

    /**
     * OkHttpClient 구성
     *
     * @return OkHttpClient
     */
    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .connectTimeout(ApplicationConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS) // 60 * 1000
                .writeTimeout(ApplicationConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)     // 60 * 1000
                .readTimeout(ApplicationConstants.READ_TIMEOUT, TimeUnit.SECONDS)       // 60 * 1000
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    /**
     * Create a new {@link Retrofit.Builder}. Override this to e.g. set your own client or executor.
     * 새 {@link Retrofit.Builder}를 만듭니다. 예를 들어 이것을 재정의하십시오. 자신의 클라이언트 또는 실행자를 설정합니다.
     *
     * @return A {@link Retrofit.Builder} with no modifications.
     */
    private Retrofit.Builder newRestAdapterBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, API key changed), builds a new one.
     * 현재 {@link Retrofit} 인스턴스를 반환합니다. 존재하지 않는 경우(첫 번째 호출, API 키 변경됨) 새 항목을 빌드합니다.
     * <p/>
     * When building, sets the endpoint and a {@link HttpLoggingInterceptor} which adds the API key as query param.
     * 빌드 시 엔드포인트와 API 키를 쿼리 매개변수로 추가하는 {@link HttpLoggingInterceptor}를 설정합니다.
     */
    private Retrofit getRestAdapter() {

        Retrofit.Builder builder = null;
        if (retrofit == null) {

            // Create a new instance of the Rest Adapter class
            builder = newRestAdapterBuilder();

            builder.baseUrl(ApplicationConstants.END_POINT);
            builder.client(okHttpClient());
            builder.addConverterFactory(GsonConverterFactory.create());
        }

        if (isDebug) {
            if (builder != null) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }
        }
        if (builder != null) {
            retrofit = builder.build();
        }
        return retrofit;
    }

    /**
     * Create Movie service instance.
     *
     * @return Movie Service.
     */
    public MovieInterface movieInterface() {
        return getRestAdapter().create(MovieInterface.class);
    }

}
