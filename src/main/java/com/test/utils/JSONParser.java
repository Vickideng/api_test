package com.test.utils;

import com.alibaba.fastjson.JSONObject;

public class JSONParser {
    JSONObject internalJSON;

    public String getName(JSONObject jo){
        String Name="";
        try{
            internalJSON= jo.getJSONObject("Data");

            Name=internalJSON.getString("Name");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  Name;
    }
}
