package com.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 *  解析所有请求返回值
 * @author dengke
 * @date 2019-07-12
 */
public class JSONParser {
    JSONObject internalJSON;

    /** 获取商品名称*/
    public String getProductName(JSONObject jo){
        String Name="";
        try{
            internalJSON= jo.getJSONObject("Data");

            Name=internalJSON.getString("Name");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  Name;
    }

    /**
     * 根据jsonpath获取值
     * @param object
     * @param path
     * @return
     */

    public String findValueByJSONPath(JSONObject object, String path) {
        return String.valueOf(JSONPath.eval(object, path));
    }

}
