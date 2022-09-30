package com.tx.dllogin.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public class LogUtill {


    public static  String GetUserName(HttpServletRequest request){
        //从jwtUtill 中取出 sing签名
        String jwtToken = request.getHeader("jwtToken");
        String Sing ="1234657498ghvjhjhbhjb";
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(Sing)).build().verify(jwtToken);
        //登录时候 传的map取出来。。。 用户账号
        String userName = verify.getClaim("userName").asString();
        return userName;
    }



}
