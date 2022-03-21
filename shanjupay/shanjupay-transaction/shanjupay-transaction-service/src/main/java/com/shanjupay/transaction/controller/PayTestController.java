package com.shanjupay.transaction.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝接口对接测试类
 * @author Administrator
 * @version 1.0
 **/

@Slf4j
@Controller
//@RestController//请求方法响应统一json格式
public class PayTestController {

    //应用id
    String APP_ID = "2021000119640290";
    //应用私钥
    String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMm7eYGGwn9ZzC9xX4vpqwp7EpXwlpdA724RDez4g0uBD08eDYjSvWTg/DWKr08MsKQBQMK6lHTSDBnVF3MO6jpa3bkE9IsF81S7etR6P4w0mRiucxOPVgjt7WNWrWfK/S4JqH+lcwXhmaubjHVnvewi02Fbh8PlBgVAgP5qcc0T4oTziXcOvA9kZvoHNCvMNirdJHgF+NAPUwugNyO+azYgGX+ES/2IbEUN8tYGtj5GozwT3Adl2GOTTq8GNukqqhUAbD5qEyP10ELWsDF++OYnkWim8xmQVBq4GIqqmS9abJCkh+4LDC1YCoBYZ2tEvwy4MZHqRtjKXhUSXXcF/XAgMBAAECggEAd38rU76FxJwPYv58lM7gRnvtq3xSUmXJBfWW0dVMK4m3F4StEz0+4T2sVdd4SvyNgiOdJg7BKZCZ4xIhFwvl8/wtOml1iBAkifXUnxZAe3z0fBWxj/qZRzuElEYHi0uiXbR2gFO4X6nJVt658+dDFLivlcR16llHE+a2h1T99xVw3h2GDE3BWU9F+7d8nmP5ljShVYF+C4D8gcNUhSNOd8rxyOlJVWL1PFbDuVAnNEJs/2socl5SX51z/amtYEouBIZhSGMzYWMR4QMcThK+baDyFRMtcocK/2mnWsmeGA8jxI9y0Lt97bE+hnp7u/JiSbDQaMv8Oftc1vP0nj8FWQKBgQDlK3XDQKleaDSCC6g/AlBKJ3wqoDwtCH+kmaHomYDd3p19WKzrSC6i4oDP+kJ5/Bkek4awvy1gLWTNJZ08U2sl9LUhwh85PgCVymu52bvi40XW8yjpT1HecK24fW12oNZsR5+YYssk4r4EjQGvlU1Vm+6xkmsYtbK23ERIuRnEzQKBgQCdEfHU7v751AbZIQk9tPEWB0vRsx783p/mBYe4VitBQmkSEj7loOHDthMJTWF6hl//jaP8Y6f5K1PAj8GpT2Xext3RSjbgZbnK1VUHjX+25le1/VFcKPnW6FseawkEjV/pvHW1R5rnqZ7Gm7KP44CNp7JTQisyTUwWkJhETx7XMwKBgQCFRgYBW/xjc9R9gIwCwLSyu03BeFgmlFevAM2g+Ixx+0G5oLVHSz4b6f+hc9fvGwX8uyzMYxHfaebCS0qqx5R7iVlBXaRM5NrwQbzuY0qaAzQdcmHrdP6Wjw7oSME9CQJKDEkrFOEw/VAmU16Unw5PaBSjXhQ8VYauQ2DOrh7nTQKBgAX0qTvOuCJW9M+E3inV05JnQmu4JAOomV2KdiP6OHeoAf3qbTId0JGwNLaUlY6ky70P7NjkGnlrOCjECq7V0OIKnlySONR3Zv0W67t2lRIlV8wRx+T7tMV+V/4mRthCWBGv0LLH/UqtdtTHEJU+JiC47B0WFDc7ERiF8RAeA+hbAoGAAfORxvL6QMkUM42O2oQH5h3eBJWyMH9XiFLU17xYqZeJiE5sGy8Hxag+gjRxDidB6BwFrdbnsHuakaYujnTFghyfEOsUAkW/twhy78vxkMFprzhfe2dhrf1eCSMLNyLArnIxCtJVVyKZmYvSNyHR/b5fe3e3+Ac88ZMQaMr5ROs=";
   //支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmZm4u0y8zW/ayDvEcUugVbKaymYFvbluPJGjXvxlk8nivdXT7DCScMzDdbdvv8gVF1F1/JeZD/uNCmjq0xOkQZ2OZCZZW+//GheHiB7MUtQLiGG0weL9uzg8QpriVG8gEgjUr5zZ6YoduuS8/3Cuzz4nlTrcl3fqu6dIUNba3xkh7aVz4GvWwkLA6CgOQmgUtoIhlq+ktg/l6ult2yJZV2QQLxuKZ8lOPzXn9GfcYZB+SZFsoqPC0l6e9129MJacfwXqy73sb8uWoA/esVStRbIZuZKxN846gY/veZKMJawtG+rbvrLCm/3thPpLHFLdchujftDotKmj21Poag6zgQIDAQAB";
    String CHARSET = "utf-8";
    //支付宝接口的网关地址，正式"https://openapi.alipay.com/gateway.do"
    String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    //签名算法类型
    String sign_type = "RSA2";

    @GetMapping("/alipaytest")
    public void alipaytest(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws ServletException, IOException {
        //构造sdk的客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, sign_type); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150420010101010\"," +
                " \"total_amount\":\"10.0\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            //请求支付宝下单接口,发起http请求
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
