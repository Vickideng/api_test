package com.test.api;

import com.alibaba.fastjson.JSONObject;
import com.test.client.restfulClient;
import com.test.utils.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testPost {

    restfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String name;
    String url = "https://apitest.pelly.cc/api/Member/Login";
    String postBody;

    @Test
    public void testPostRequest() {
    //断言反馈中城市信息是否正确
        Assert.assertEquals(name, "形象");
    //断言反馈的状态码是否正确
        Assert.assertEquals(responseCode, 200);
    }
    @BeforeClass
    public void beforeClass() throws ClientProtocolException, IOException {
        client = new restfulClient();

        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userName", "18512391083"));
        params.add(new BasicNameValuePair("userPwd", "123456"));

     //用哈希图准备请求头部信息
        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Accept","application/json, text/plain, */*");
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        hashHead.put("Authorization","Bearer null");
        hashHead.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        hashHead.put("Origin","http://test.pelly.cc");

    //传参发送post请求并接收反馈
        client.sendPost(url,params,hashHead);

        responseBody = client.getBodyInJSON();
        responseCode = client.getCodeInNumber();

        System.out.println(responseBody);
        jParser = new JSONParser();
        name = jParser.getProductName(responseBody);
    }


}