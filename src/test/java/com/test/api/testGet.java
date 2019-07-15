package com.test.api;

import com.alibaba.fastjson.JSONObject;
import com.test.client.restfulClient;
import com.test.utils.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.http.ParseException;
import java.io.IOException;

public class testGet {

    restfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String Name;
    String url = "https://apitest.pelly.cc/api/Product/GetProductByID?productID=1453";
    @Test
    public void TestGetRequest() {

        Assert.assertEquals(Name, "形象");

        Assert.assertEquals(responseCode, 200);
    }
    @BeforeClass
    public void beforeClass() throws ParseException, IOException {
        //发送请求，获取反馈
        client = new restfulClient();
        client.getResponse(url);
        responseBody = client.getBodyInJSON();
        responseCode = client.getCodeInNumber();

        //调用JSONParser获取反馈中的商品信息
        jParser = new JSONParser();
        Name = jParser.getProductName(responseBody);
    }
}