package com.xiaweizi.eshop.network.core;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class UICallback implements Callback {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onUiFailure(call, e);
            }
        }, 2000);
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onUiResponse(call, response);
            }
        }, 2000);
    }

    public abstract void onUiFailure(Call call, IOException e);
    public abstract void onUiResponse(Call call, Response response);
}
