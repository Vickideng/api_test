package com.test.client;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class testGetAPI {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        //get请求的URL
        String url = "https://apitest.pelly.cc/api/Product/GetProductByID?productID=1453";
        //httpClient是用来发送http请求的HttpClient实例
        CloseableHttpClient httpClient;
        //httpGet是get请求的一个实例
        HttpGet httpGet;
        //httpResponse用来存储我们接收到的反馈
        CloseableHttpResponse httpResponse;
        //responseBody用来存储反馈的主体信息
        HttpEntity responseBody;
        //responseCode用来存储反馈的状态码
        int responseCode;
        //responseHeader用来存储反馈的头部信息
        Header[] responseHeader;

        //创建一个httpClient的实例
        httpClient = HttpClients.createDefault();
        //创建一个httpGet请求实例
        httpGet = new HttpGet(url);
        //使用httpClient实例发送刚创建的get请求，并用httpResponse将反馈接收
        httpResponse = httpClient.execute(httpGet);

        //从反馈中提取出状态码
        responseCode = httpResponse.getStatusLine().getStatusCode();
        //从反馈中提取出反馈主体
        responseBody = httpResponse.getEntity();
        //从反馈中提取出所有头部信息
        responseHeader = httpResponse.getAllHeaders();

        //用EntityUtils工具类将反馈主体处理为字符串形式
        String responseBodyString = EntityUtils.toString(responseBody,"utf-8");

        //用哈希图将反馈头信息以键值对形式保存
        HashMap<String,String> hashMap = new HashMap<String,String>();
        for(Header header:responseHeader){
            hashMap.put(header.getName(), header.getValue());
        }


        System.out.println("This is the response code:" + responseCode);
        System.out.println("This is the response body:" + responseBodyString);
        System.out.println("This is the response header in hash" + hashMap);

    }

}