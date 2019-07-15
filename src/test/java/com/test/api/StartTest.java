package com.test.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.client.ApiTestExcelProvider;
import com.test.client.ReadExcel;
import com.test.client.restfulClient;
import com.test.utils.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StartTest {

    private Logger logger = Logger.getLogger(this.getClass());

    restfulClient client;
    JSONParser jParser = new JSONParser();
    HashMap<String, String> hashHead = new HashMap<>();


    @BeforeClass
    public void beforeClass(){
        ReadExcel readExcel = new ReadExcel();
        ApiTestExcelProvider.steps = readExcel.getStep();

        client = new restfulClient();

        //用哈希图准备请求头部信息
        hashHead.put("Accept","application/json, text/plain, */*");
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        hashHead.put("Authorization","Bearer null");
        hashHead.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        hashHead.put("Origin","http://test.pelly.cc");
    }


    @DataProvider(name = "dataRedy")
    public Iterator<Object[]> DataProvider() {
        Iterator<Object[]> step = new ApiTestExcelProvider();
        return step;
    }

    @Test(dataProvider="dataRedy")
    public void AutoTests(Map<Integer,String> testStep) throws NumberFormatException, IOException {
        //执行测试
        //logger.info("【用例描述】"+testStep.getTestCaseDesc());
        //logger.info("【步骤"+testStep.getStep()+"】:"+testStep.getStepDescription());
        logger.info("======"+JSON.toJSONString(testStep));

        String url = testStep.get(0);

        String param = testStep.get(1);

        String jsonPath = testStep.get(2);

        String expected = testStep.get(3);

        //用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String[] split = param.split(";");
        for (String s : split) {
            String[] split1 = s.split(":");

            params.add(new BasicNameValuePair(split1[0], split1[1]));

        }

        client.sendPost(url, params, hashHead);
        JSONObject bodyInJSON = client.getBodyInJSON();

        String[] jsonPathArry = jsonPath.split("\n");
        String[] expectedArry = expected.split("\n");

        for (int i = 0; i < jsonPathArry.length; i++) {
            String s = jsonPathArry[i];
            String s1 = expectedArry[i];

            String valueByJSONPath = jParser.findValueByJSONPath(bodyInJSON, s);
            Assert.assertEquals(valueByJSONPath,s1);
        }



        if (testStep.size() >= 5) {
            String headKey = testStep.get(4);
            String headValueJsonPath = testStep.get(5);
            String prefix = testStep.get(6);
            String headValue = jParser.findValueByJSONPath(bodyInJSON, headValueJsonPath);

            hashHead.put(headKey, String.format("%s%s", prefix, headValue));
        }

    }


}