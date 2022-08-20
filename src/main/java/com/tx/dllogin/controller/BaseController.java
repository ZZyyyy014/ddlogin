package com.tx.dllogin.controller;

import com.alibaba.fastjson.JSONArray;
import com.tx.dllogin.common.CommonResult;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class BaseController {




    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class })
    public Object authorizationException(HttpServletRequest request, HttpServletResponse response) {
        return CommonResult.error("无此功能权限");
 }

    @ExceptionHandler({ AuthorizationException.class })
    public Object authorizationExceptions(HttpServletRequest request, HttpServletResponse response) {
        return CommonResult.error("请重新登录");
    }



    /**
     * 输出JSON
     */
    private void writeJson(Map<String, Object> resp, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSONArray.toJSONString(resp));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
