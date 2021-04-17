package com.saybiu.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.service.SmsSimulationService;

import java.util.Map;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/16 9:59 下午 （日期和时间）
 */

public class SmsSimulationServiceImpl implements SmsSimulationService {
    @Override
    public String getSmsCode() {
        String a="122134124124";
        String smsResponse="{\n" +
                "    \"Response\":{\n" +
                "        \"SendStatusSet\":{\n" +
                "            \"SerialNo\":\"5000:1045710669157053657849499619\",\n" +
                "            \"PhoneNumber\":\"+8618511122233\",\n" +
                "            \"Fee\":1,\n" +
                "            \"SessionContext\":\"test\",\n" +
                "            \"Code\":\"Ok\",\n" +
                "            \"Message\":\"send success\",\n" +
                "            \"IsoCode\":\"CN\"\n" +
                "        },\n" +
                "        \"RequestId\":\"a0aabda6-cf91-4f3e-a81f-9198114a2279\"\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(smsResponse).getJSONObject("Response").getJSONObject("SendStatusSet");
        jsonObject.put("Message",(int)((Math.random()*9+1)*100000));

        return jsonObject.toJSONString();
    }
}
