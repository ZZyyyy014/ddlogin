package com.tx.dllogin.utill;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.java_websocket.WebSocket;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class AesUtil {


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // public static final String key = "F50DBAB515286F4C88D44CADE0819334829C15F60D859F43";
    public static final String iv = "0102030405060708";

    /**
     * key 秘钥 16的倍数
     * iv 偏移量
     * 加密   生成passwrodtoen
     *
     * @param passWrod 明文 内容
     * @return
     */
    public static String getPasswordToken(String passWrod) {
        byte[] crypted = null;
        try {
            //秘钥
            byte[] decode = Hex.decodeHex("F50DBAB515286F4C88D44CADE0819334829C15F60D859F43");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skey = new SecretKeySpec(decode, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivspec);
            crypted = cipher.doFinal(passWrod.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Hex.encodeHex(crypted)).toUpperCase();
    }


    public static String getToknText(String text) {
        //String text="access_token=000000&app_key=7a48ab6d06a54864a5d925b52758e9fa&method=jd.pop.AuthorizeInfo.getImAuthorize&param_json=%7B%22authorizeRequest%22%3A%7B%22aid%22%3A%22wzVitjvm%22%2C%22appId%22%3A%22im.waiter%22%2C%22deviceId%22%3A%2258-11-22-45-4F-D6%22%2C%22ip%22%3A%22192.168.202.111%22%2C%22pin%22%3A%22%E6%A3%AE%E9%A9%AC%E8%90%8C2%22%2C%22platform%22%3A%22pc%22%2C%22version%22%3A%229.4.9.3%22%7D%7D%0A&sign=7B5BB18FDE66AEDC1E95853ECDFCF540&timestamp=2022-08-05+18%3A11%3A06&v=5.0";
        String text1[] = text.split("&");
        String key = "f9d9f45588444dde81b1d1f9ad99f11e";
        String access_token = null;
        String app_key = null;
        String method = null;
        String param_json = null;
        String timestamp = null;
        String v = null;
        String sign = null;
        for (String text2 : text1) {
            String text3[] = text2.split("=");
            switch (text3[0]) {
                case "access_token":
                    access_token = text3[1];
                case "app_key":
                    app_key = text3[1];
                case "method":
                    method = text3[1];
                case "param_json":
                    param_json = URLDecoder.decode(text3[1]);
                case "sign":
                    sign = text3[1];
                case "timestamp":
                    timestamp = URLDecoder.decode(text3[1]);
                case "v":
                    v = text3[1];
                    break;
            }


        }
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String signText = key + "access_token" + access_token + "app_key" + app_key + "method" + method + "param_json" + param_json + "timestamp" + timestamp + "v" + v + key;
       // System.out.println(signText);
        //System.out.println(getMD5String(signText));
        sign = getMD5String(signText);
       // System.out.println("sign---"+sign.toUpperCase());
        String PostData = "access_token=" + access_token + "&app_key=" + app_key + "&method=" + method + "&param_json=" + URLEncoder.encode(param_json) + "&sign=" + sign.toUpperCase() + "&timestamp=" + URLEncoder.encode(timestamp) + "&v=" + v;
       // System.out.println(PostData);
        return PostData;
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAid (String token, String pin) throws Exception {

            String body1 = "{\"token\":\"" + token + "\",\"ip\":\"127.0.0.1\",\"pin\":\"" + pin + "\",\"art\":\"imee\",\"appId\":\"im.waiter\",\"os\":\"windows\",\"aidClientType\":\"pc\",\"aidClientVersion\":\"9.4.9.3\"}";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.m.jd.com/");
            //赋值参数
            ArrayList<BasicNameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("appid", "feijing"));
            list.add(new BasicNameValuePair("functionId", "getAidInfo4B"));
            list.add(new BasicNameValuePair("body", body1));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            //设置请求头
            httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("cookie", "ddtarget=1");
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36 DD_PC");
            httpPost.setHeader("origin", "https://dongdong.jd.com");
            CloseableHttpResponse execute = client.execute(httpPost);
            // 获取实体内容
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            JSONObject JSONText = JSONObject.parseObject(s);
            String o = JSONText.getJSONObject("body").get("aid").toString();
            return o;

    }


    //dd 登录
    public static String loginDd (String loginName,String passwrod) throws Exception {
            TrustManager trustManager = new X509ExtendedTrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            //passwrodtoken  上面方法生成的
            String passwrodtoken = getPasswordToken(passwrod);
            String GUID = UUID.randomUUID().toString();
            String ddAid = AesUtil.getAid(passwrodtoken, loginName);
            String url = "wss://ws2-dd.jd.com/?pin=" + URLEncoder.encode(loginName) + "&clientType=pc&appId=im.waiter&art=imee&aid=" + ddAid + "&_wid_=" + GUID;
            URI uri = new URI(url);
            AesWebsocket aesWebsocket = new AesWebsocket(uri);
            //连接
            aesWebsocket.connect();
            //等待服务器响应
            while (!aesWebsocket.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                //等待0.5秒
                // System.out.println("还未连接上");
                Thread.sleep(500);
            }
           // aesWebsocket.send("{\"body\":{\"clientKind\":\"waiter\",\"clientLocalIp\":\"192.168.202.111\",\"clientType\":\"POP\",\"clientVersion\":\"9.4.9.3\",\"dvc\":\"FBC17678-E294-4DAD-86AD-E817F5A0F2C0\",\"os\":\"Microsoft Windows 10 null\",\"presence\":\"chat\",\"token\":\"3BDF34D9BAABBD44BF92078CD0E9BBC3\",\"versionCheck\":1},\"from\":{\"app\":\"im.waiter\",\"art\":\"customerGroupMsg\",\"clientType\":\"pc\",\"pin\":\"森马萌2\"},\"id\":\"" + UUID.randomUUID().toString() + "\",\"to\":{\"app\":\"im.waiter\",\"pin\":\"@im.jd.com\"},\"type\":\"auth\",\"ver\":\"4.4\"}\n");
            //aesWebsocket.send("{\"aid\":\"" + ddAid + "\",\"body\":{\"mac\":\"FBC17678-E294-4DAD-86AD-E817F5A0F2C0\",\"pin\":\"森马萌2\"},\"from\":{\"app\":\"im.waiter\",\"art\":\"customerGroupMsg\",\"clientType\":\"pc\",\"pin\":\"森马萌2\"},\"id\":\"" + UUID.randomUUID().toString() + "\",\"timestamp\":" + System.currentTimeMillis() + ",\"to\":{\"app\":\"im.waiter\",\"pin\":\"@im.jd.com\"},\"type\":\"pop_security_type_get\",\"ver\":\"4.4\"}");
            aesWebsocket.send("{\"body\":{\"clientKind\":\"waiter\",\"clientLocalIp\":\"192.168.202.111\",\"clientType\":\"POP\",\"clientVersion\":\"9.4.9.3\",\"dvc\":\"FBC17678-E294-4DAD-86AD-E817F5A0F2C0\",\"os\":\"Microsoft Windows 10 null\",\"presence\":\"chat\",\"token\":\""+passwrodtoken+"\",\"versionCheck\":1},\"from\":{\"app\":\"im.waiter\",\"art\":\"customerGroupMsg\",\"clientType\":\"pc\",\"pin\":\""+loginName+"\"},\"id\":\"" + UUID.randomUUID().toString() + "\",\"to\":{\"app\":\"im.waiter\",\"pin\":\"@im.jd.com\"},\"type\":\"auth\",\"ver\":\"4.4\"}\n");
            aesWebsocket.send("{\"aid\":\"" + ddAid + "\",\"body\":{\"mac\":\"FBC17678-E294-4DAD-86AD-E817F5A0F2C0\",\"pin\":\""+loginName+"\"},\"from\":{\"app\":\"im.waiter\",\"art\":\"customerGroupMsg\",\"clientType\":\"pc\",\"pin\":\""+loginName+"\"},\"id\":\"" + UUID.randomUUID().toString() + "\",\"timestamp\":" + System.currentTimeMillis() + ",\"to\":{\"app\":\"im.waiter\",\"pin\":\"@im.jd.com\"},\"type\":\"pop_security_type_get\",\"ver\":\"4.4\"}");

           System.out.println(aesWebsocket.getExcptMessage());
            while (aesWebsocket.getExcptMessage() == null && !("PHONE_ALTERNATIVE").equals(aesWebsocket.getExcptMessage())) {
                Thread.sleep(1000);
            }
            //关闭连接
            aesWebsocket.close();
            String headerToken = getToknText("access_token=000000&app_key=7a48ab6d06a54864a5d925b52758e9fa&method=jd.pop.AuthorizeInfo.getImAuthorize&param_json=%7B%22authorizeRequest%22%3A%7B%22aid%22%3A%22" + ddAid + "%22%2C%22appId%22%3A%22im.waiter%22%2C%22deviceId%22%3A%2258-11-22-45-4F-D6%22%2C%22ip%22%3A%22192.168.202.111%22%2C%22pin%22%3A%22" + loginName + "%22%2C%22platform%22%3A%22pc%22%2C%22version%22%3A%229.4.9.3%22%7D%7D%0A&sign=7B5BB18FDE66AEDC1E95853ECDFCF540&timestamp=2022-08-05+18%3A11%3A06&v=5.0");
            //System.out.println(headerToken);
            String[] split = headerToken.split("&");
            //发送请求客户端
            CloseableHttpClient client = HttpClients.createDefault();
            //发送个post请求
            HttpPost httpPost = new HttpPost("http://vp.jd.com/im");
            //赋值参数
            ArrayList<BasicNameValuePair> list = new ArrayList<>();
            for (String text2 : split) {
                String text3[] = text2.split("=");
               list.add(new BasicNameValuePair(text3[0],text3[1]));
            }
            String SS="";
           for (int i = 0; i < list.size(); i++) {
               SS=SS+list.get(i)+"&";
            }
            httpPost.setEntity(new StringEntity(SS));
           //设置请求头
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            CloseableHttpResponse execute = client.execute(httpPost);
            // 获取实体内容
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            JSONObject JSONText = JSONObject.parseObject(s);
           // 返回字符串 例如{"result":{"success":true,"class":"com.jd.pop.jm.center.domain.im.AuthorizeResponse","token":"40f83a3c-47e6-4394-a107-104a6a6f8a7c_1651160216752-jd"},"uid":"b0d143e3-a75a-4440-a769-486974b3addd","code":"0","ip":"大猫","type":"json"}
            /* //返回的连接串*/
            //
            //access_token
            //pin
            //timestamp
            JSONObject jsonObject = JSONText.getJSONObject("result");
           // String jsonObject = JSONText.getJSONObject("result").getJSONObject("token").toString();
            Map jsonObject1 = (Map)JSONObject.parseObject(jsonObject + "");
            String token = (String) jsonObject1.get("token");


            String headerToken1 = getToknText("access_token="+token+"&app_key=7a48ab6d06a54864a5d925b52758e9fa&method=jd.pop.ImPluginCenter.doPluginInfo&param_json=%7B%22doPluginRequest%22%3A%7B%22ip%22%3A%22192.168.202.111%22%2C%22pin%22%3A%22"+loginName+"%22%2C%22platform%22%3A%22pc-win%22%2C%22pluginCode%22%3A%22b1f95038414941c5b18c07ab1a262d4b%22%2C%22source%22%3A1%2C%22versionCode%22%3A%2206114d771d2c41d6b02a8ef3347a1a0c%22%7D%7D%0A&sign=A88237E8BF56CB2E0B34F577759AA7A1&timestamp=2022-08-05+18%3A12%3A58&v=5.0");
            //发送个post请求
            String[] split1 = headerToken1.split("&");
            ArrayList<BasicNameValuePair> list1 = new ArrayList<>();
            for (String text2 : split1) {
                String text3[] = text2.split("=");
                list1.add(new BasicNameValuePair(text3[0],text3[1]));
            }
            String SSs="";
            for (int i = 0; i < list1.size(); i++) {
                SSs=SSs+list1.get(i)+"&";
            }
            httpPost.setEntity(new StringEntity(SSs));
            CloseableHttpResponse execute1 = client.execute(httpPost);
            // 获取实体内容
            HttpEntity entity1 = execute1.getEntity();
            String s1 = EntityUtils.toString(entity1, "utf-8");

            JSONObject JSONText1 = JSONObject.parseObject(s1);
            JSONObject jsonObject12 = JSONText1.getJSONObject("result");
            Map jsonObject2 = (Map)JSONObject.parseObject(jsonObject12 + "");
            String resurl = (String) jsonObject2.get("pluginOpenUrl");
        System.out.println(resurl);
            return resurl;
    }


}