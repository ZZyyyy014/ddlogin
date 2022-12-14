package com.tx.dllogin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.findBean.DdUrlBean;
import com.tx.dllogin.services.UserService;
import com.tx.dllogin.vo.AddUserVo;
import com.tx.dllogin.vo.UserFindAllVo;
import com.tx.dllogin.vo.Uservo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


@Api(tags = "用户模块操作")
@RestController
@CrossOrigin("*")
public class UserController  extends BaseController {

    @Autowired
    private  HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private Producer producer;



    @ApiOperation("产生验证码")
    //验证码生产
    @GetMapping("/user/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // captcha框架 Producer内置类    生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session  KAPTCHA_SESSION_KEY
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }




    @ApiOperation("京麦免登陆")
    @PostMapping("/user/ddUrl")  //userTypess类型  1京东 2拼多多 3淘宝  默认为1
    public  CommonResult  ddUrl(@RequestBody DdUrlBean ddUrlBean){
        //返回的免密 登录
        CommonResult commonResult = userService.JMlogin(ddUrlBean.getUserName(),ddUrlBean.getPassword());
        return commonResult;
    }






    @ApiOperation("dd后台登录")
    @PostMapping("/user/ddlogin")
    public CommonResult ddlogin(@RequestBody Uservo user){
        //返回的登录
        CommonResult login = userService.login(user.getUserName(),
                           user.getPassWrod(),
                          user.getCaptcha()
                          ,request);
        return login;
    }



    @GetMapping("/user/findAllLogin")
    @RequiresPermissions(value = {"user:find"} ,logical = Logical.OR)
    public CommonResult  findAllLogin( Integer pageNum,Integer pageSize){
        CommonResult allLogin = userService.findAllLogin(pageNum,pageSize);


        return allLogin;
    }

    @GetMapping("/user/deleteOneUser")
    @RequiresPermissions(value = {"user:delete"} ,logical = Logical.OR)
    public CommonResult  deleteOneUser(@RequestParam ("userId") String userId){
        CommonResult allLogin = userService.deleteOneUser(userId);
        return allLogin;
    }


    @GetMapping("/user/deleteListUser")
    @RequiresPermissions(value = {"user:delete"} ,logical = Logical.OR)
    public CommonResult  deleteListUser(@RequestParam ("userId") List<String> userId){
        CommonResult allLogin = userService.deleteListUser(userId);
        return allLogin;
    }

     @PostMapping("/user/updateUserFindAllVo")
     @RequiresPermissions(value = {"user:update"} ,logical = Logical.OR)
    public CommonResult  updateUserFindAllVo(@RequestBody UserFindAllVo userFindAllVo){
        CommonResult allLogin = userService.updateUserFindAllVo(userFindAllVo);
        return allLogin;
    }

    @PostMapping("/user/AddUser")
    @RequiresPermissions(value = {"user:create"} ,logical = Logical.OR)
    public CommonResult AddUser(@RequestBody AddUserVo addUserVo){
        CommonResult commonResult = userService.AddUser(addUserVo);
        return commonResult;
    }

    @GetMapping("/user/findLogRoter")
    @ApiOperation("登录后查询导航栏有哪些资源")
    @RequiresPermissions(value = {"user:find"})
    public CommonResult findLogRoter(){
        return userService.findLogRoter();
    }


}
