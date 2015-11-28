package com.qf.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Volley数据请求工具类
 */
public class VolleyUtil {

    public static RequestQueue requestQueue;

    public static void initVolley(Context context){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    /**
     * 字符串请求
     * @param url
     * @param onRequest
     */
    public static void requestString(final String url, final OnRequest onRequest){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //下载成功
                onRequest.response(url, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //下载失败
                onRequest.errorResponse(url, error);
            }
        });

        requestQueue.add(stringRequest);
    }

    public interface OnRequest{
        void response(String url, String response);
        void errorResponse(String url, VolleyError error);
    }
}
