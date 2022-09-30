package com.tx.dllogin.shiro.realm;


import com.tx.dllogin.dao.UserMapper;
import com.tx.dllogin.vo.FIndShiroUserVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Lazy
    private HttpServletRequest request;

    @Override //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息（用户名）
        String primaryPrincipal = principalCollection.getPrimaryPrincipal().toString();
        if(primaryPrincipal==null) return new  SimpleAuthorizationInfo();
        //查数据库  返回用户信息+权限信息+资源信息 需要什么 那什么
        FIndShiroUserVo shiroParms = userMapper.findShiroParms(primaryPrincipal);
        HttpSession session = request.getSession();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        session.setAttribute("roleId",shiroParms.getRoleId());
        session.setAttribute("roles",shiroParms.getRoleName());
        //添加角色 如果多角色
        simpleAuthorizationInfo.addRole(shiroParms.getRoleName());
        //赋值资源权限   例如 user:create
        simpleAuthorizationInfo.addStringPermissions(shiroParms.getParmsList());
        session.setAttribute("permissions",shiroParms.getParmsList());
        return simpleAuthorizationInfo;
    }


    @Override  //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token.getPrincipal()==null)return null;
        //用户名
        String principal = token.getPrincipal().toString();
        //根据名称在数据库中 查询密码
        String byUserName = userMapper.findPassWrodByUserName(principal);
       //返回对象 参数1代表返回数据的正确用户名称  参数2 返回数据库中正确密码   参数3 注册的盐txDB     参数4 Realm 名称
    /*      SimpleAuthenticationInfo simpleAuthenticationInfo1 =
                  new SimpleAuthenticationInfo(principal,
                          byUserName,
                          ByteSource.Util.bytes("txDB"),
                          this.getName());*/
           //应为密码未用 MD5加密 1024次 +salt   所以不用
        SimpleAuthenticationInfo simpleAuthenticationInfo1 =
                new SimpleAuthenticationInfo(principal,byUserName,this.getName());
        return simpleAuthenticationInfo1;
    }




/*
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        //设置密码加密规则
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
*/


}
