package com.xiaweizi.eshop.network;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 工程名：  EShop
 * 包名：    com.xiaweizi.eshop.network
 * 类名：    EShopClient
 * 创建者：  夏韦子
 * 创建日期： 2017/2/23
 * 创建时间： 16:22
 */

public class EShopClient {
    public static final String BASE_URL = "http://106.14.32.204/eshop/emobile/?url=";

    private static EShopClient instance;

    private final OkHttpClient mOkHttpClient;
    private final Gson mGson;

    public static EShopClient getInstance() {
        if (instance == null) {
            synchronized (EShopClient.class) {
                if (instance == null) {
                    instance = new EShopClient();
                }
            }
        }
        return instance;
    }

    private boolean mShowLog = false;

    private EShopClient() {
        mGson = new Gson();
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {

                    @Override
                    public void log(String message) {
                        if (mShowLog) System.out.println(message); // NOPMD
                    }
                }
        );
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .build();
    }

    // 构建一下商品分类的接口请求
    public Call getCategory() {
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/category")
                .build();
        return mOkHttpClient.newCall(request);
    }

}
