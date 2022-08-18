package com.tx.dllogin.shiro;

import com.tx.dllogin.shiro.realm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig  {





    @Bean//1创建拦截器
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给fiter 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置 系统受限资源
        //配置系统公共资源
        HashMap<String, String> map = new HashMap<>();
        //map.put("/index", "authc");//authc 请求 这个资源需要请求认证 和授权
        //设置默认 认证界面
   /*
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        */
        return shiroFilterFactoryBean;
    }



    @Bean(name="securityManager")
    public DefaultWebSecurityManager  securityManager(@Qualifier("myRealm")MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }


    //创建自定义realm
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        //设置密码加密规则
        return myRealm;
    }



}
