package com.tx.dllogin.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Calendar;
import java.util.Map;

@Data
public class JwtUtil {

    //秘钥
  //private static  final String Sing ="1234657498ghvjhjhbhjb";

    //生成token  header.payload.sing
  public  static  String  getToken(Map<String ,String>map){
      String Sing ="1234657498ghvjhjhbhjb";

      //创建JWT构造对象
      JWTCreator.Builder builder = JWT.create();
      Calendar instance = Calendar.getInstance();
      //当前时间 7天后过期
      instance.add(Calendar.DATE,7);
      //payload   用户属性
      map.forEach((k,v)->{
          //注入属性 k,v
          builder.withClaim(k,v);
      });
                    //设置过期时间
      String s = builder.withExpiresAt(instance.getTime())
                 //使用算法+秘钥
                .sign(Algorithm.HMAC256(Sing)).toString();
      return s;
  }
  //验证token +获取token信息
  public  static  DecodedJWT verify(String token){
      String   Sing ="1234657498ghvjhjhbhjb";

      // 获取 验证信息  如果顺利就 代表token 正确
      DecodedJWT verify = JWT.require(Algorithm.HMAC256(Sing)).build().verify(token);
       //verify.getClaim("").asString();  获取值方法  key  as是什么类型就as什么类型   如asint
      return verify;
  }

}
