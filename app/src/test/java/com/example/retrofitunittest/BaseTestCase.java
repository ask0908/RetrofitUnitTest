package com.example.retrofitunittest;

import org.junit.BeforeClass;

public class BaseTestCase {

    private final static TmdbApiClient mTmdbApiClient = new TmdbApiClient();

    // 단위 테스트 이후 인터셉터를 통해 레트로핏 API의 로그를 찍을 때 필요한 설정 메서드
    @BeforeClass
    public static void setUpOnce() {
        mTmdbApiClient.setIsDebug(ApplicationConstants.DEBUG);
    }

    /**
     * @return {@link TmdbApiClient} instance.
     * 유닛 테스트 시 API 메서드를 호출하기 위해 ApiClient 객체를 리턴하는 메서드
     */
    protected final TmdbApiClient getTmdbApiClient() {
        return mTmdbApiClient;
    }

}
