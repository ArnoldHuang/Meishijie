package com.qf.util;

import com.google.gson.Gson;
import com.qf.model.RecmentEntity;

import org.json.JSONObject;

/**
 * 解析JSON工具类
 */
public class JsonUtil {

    /**
     * 解析推荐页数据
     * @param json
     * @return
     */
    public static RecmentEntity getRDataByJSON(String json){
        RecmentEntity recmentEntity = null;
        if(json != null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                int code = jsonObject.getInt("code");
                if(code == 1){//获取数据成功
                    jsonObject = jsonObject.getJSONObject("obj");
                    recmentEntity = new Gson().fromJson(jsonObject.toString(), RecmentEntity.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return recmentEntity;
    }
}
