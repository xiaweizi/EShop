package com.xiaweizi.eshop.network;


import com.google.gson.Gson;
import com.xiaweizi.eshop.network.entity.CategoryRsp;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Response;

import static org.junit.Assert.assertTrue;


public class EShopClientTest {

    @Test
    public void getCategory() throws Exception {
        Call category = EShopClient.getInstance().getCategory();
        Response execute = category.execute();
        String string = execute.body().string();
        CategoryRsp categoryRsp = new Gson().fromJson(string, CategoryRsp.class);
        assertTrue(1==2);
    }

}