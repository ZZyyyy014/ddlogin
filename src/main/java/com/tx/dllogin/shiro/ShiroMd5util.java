package com.tx.dllogin.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroMd5util {

    //盐
    private static  final String salt="txDB";

    //返回加盐的密码
    public static String getPssWord(String password){
      //密码 +盐 +散列1024次
      Md5Hash md5Hash = new Md5Hash(password,salt,1024);
      return  md5Hash.toHex();
  }

}
