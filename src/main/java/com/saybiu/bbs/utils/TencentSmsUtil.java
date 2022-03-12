package com.saybiu.bbs.utils;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.validation.annotation.Validated;


import java.util.Random;

public class TencentSmsUtil {
    final static String secretId = "AKIDeHZu27dM7j7h7KNaw51jx2xiVovqYwlS";
    final static String secretKey = "ly2uYxUr3EwqX0FsLg0Rq35tYLtDG3dy";

    public static String sendSms(@Validated SendSmsRequest req){
        try {
            Credential cred = new Credential(secretId, secretKey);
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            SmsClient client = new SmsClient(cred, "",clientProfile);
            SendSmsResponse res = client.SendSms(req);
            // 输出json格式的字符串回包
            return SendSmsResponse.toJsonString(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static JSONObject complete(String phone)
    {
        SendSmsRequest req = new SendSmsRequest();
        String appid = "1400512413";
        req.setSmsSdkAppid(appid);
        req.setSign("商院缤纷生活");
        req.setTemplateID("931534");
        String[] phones=new String[200];
        phone="+86"+phone;
        phones[0]=phone;
        req.setPhoneNumberSet(phones);
        Random random=new Random();
        String dynamicCode = "";
        for (int i=0;i<6;i++)
        {
            int num = random.nextInt(10);
            dynamicCode += num;
        }
        String[] templateParams = {dynamicCode};
        req.setTemplateParamSet(templateParams);
        JSONObject jsonObject = JSONObject.parseObject(TencentSmsUtil.sendSms(req));
        jsonObject.put("dynamicCode",dynamicCode);
        if(jsonObject.isEmpty())
        {
            return null;
        }
        else
        {
            return jsonObject;
        }
    }
}

