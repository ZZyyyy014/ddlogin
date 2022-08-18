package com.tx.dllogin.shiro;

import com.tx.dllogin.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

public class ShiroMyUtills {

    public static Subject  getSubject(MyRealm realm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();

/*
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       //设置用md5 算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
       */
        defaultWebSecurityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();
         return subject;
    }


}
