package com.tx.dllogin.config;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.utill.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JwtIterceptceptor implements HandlerInterceptor {


    //拦截器
    // 预先处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断如果请求的类是swagger的控制器，直接通行。
        if (handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")) {
            return true;
        }

        //获取请求头的 jwtToken
        String jwtToken = request.getHeader("jwtToken");
        HashMap<String, Object> map = new HashMap<>();
        try {
            JwtUtil.verify(jwtToken);//通过表示 token有用
            return true; //请求放行
        } catch (NullPointerException n) {
            CommonResult.error("无效token");
        } catch (SignatureVerificationException e) {
            CommonResult.error("无效签名");
        } catch (TokenExpiredException e) {
            CommonResult.error("token过期");
        } catch (AlgorithmMismatchException e) {
            CommonResult.error("token算法不一致");
        } catch (Exception e) {
            CommonResult.error("token无效");
        }
        // 前面验证通过 直接下一步 不会走到这  //10001  token无效
        map.put("state", 10001);
        //将将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }


}
