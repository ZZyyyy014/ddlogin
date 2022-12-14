package com.tx.dllogin.utill;


import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;


public class AesWebsocket extends WebSocketClient {






    //用来接收数据
    private  String excptMessage="";

    public String getExcptMessage() {
        return excptMessage;
    }

    public void setExcptMessage(String excptMessage) {
        this.excptMessage = excptMessage;
    }

    public AesWebsocket(URI serverUri){
        super(serverUri);
        excptMessage=null;
    }


    @Override
    public void onError(Exception e){}

    @Override //连接成功调用该方法
    public void onOpen(ServerHandshake serverHandshake) {
    }


    @Override //收到来自服务器的消息  调用该方法
    public void onMessage(String m) {
        JSONObject body1 = JSONObject.parseObject(m).getJSONObject("body");
        JSONObject body = JSONObject.parseObject(m);
        String type = body.getString("type");
        if(type.equals("pop_security_type_get")){
             if(body1.getBoolean("success")){
                 this.excptMessage="PHONE_ALTERNATIVE";
             }
         }
    }

    @Override  //断开时调用该方法
    public void onClose(int i,String s,boolean a) {

    }


    public void sedMessage(String excptMessage){
        this.sedMessage( excptMessage);
    }

}
