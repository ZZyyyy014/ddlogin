package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.DeptUserMapper;
import com.tx.dllogin.dao.UserMapper;
import com.tx.dllogin.model.DeptUser;
import com.tx.dllogin.model.User;
import com.tx.dllogin.services.UserService;
import com.tx.dllogin.utill.GetUUIdUtil;
import com.tx.dllogin.utill.IpUtill;
import com.tx.dllogin.utill.JwtUtil;
import com.tx.dllogin.vo.AddUserVo;
import com.tx.dllogin.vo.UserFindAllVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptUserMapper deptUserMapper;





    @Override
    @Transactional(propagation = Propagation.REQUIRED )//有1个事务  当前只执行一次数据库操作  查询无所谓
    public CommonResult login(String userName, String passWrod,String captcha, HttpServletRequest request)  {
        String attribute =request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
       if (!attribute.equals(captcha)){
           return CommonResult.error("验证码错误,请重新登录");
       }
        User userByName = userMapper.findUserByName(userName);
        if (userByName==null){
             return CommonResult.error("该用户未注册");
        }
        //   getMD5String 获取密码加密
        /*
        String md5String = AesUtil.getMD5String(passWrod);
        if(userByName.getUserPasswrod()!=md5String){
            return CommonResult.error("密码错误");
        }  */
     //
        if(!userByName.getUserPasswrod().equals(passWrod)){
            return CommonResult.error("密码错误");
        }
        //修改用户登录时间
        User user = new User();
        user.setUserId(userByName.getUserId());
        user.setLoginDate(new Date());
        //获取目前登录IP地址
        try {
            String clientIpAddress = IpUtill.getClientIpAddress(request);
            user.setIp(clientIpAddress);
        } catch (Exception e) {
            return CommonResult.error("获取当前IP失败,请重新登录");
        }
        userMapper.updateByPrimaryKeySelective(user);
        //生成toekn
        String token ="";
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("userName", userByName.getUserName());
        map1.put("userId", userByName.getUserId());
        map1.put("level",userByName.getLevelss());
        token = JwtUtil.getToken(map1);
        HashMap<String, String> map = new HashMap<>();
        map.put("jwtToken", token);
        return CommonResult.success("登录成功",map);
    }

    @Override
    public CommonResult findAllLogin(Integer pageNum,Integer pageSize) {
         //设置开启分页参数    1.当前业数   2.每页多少
        PageHelper.startPage(pageNum, pageSize);
        List<UserFindAllVo> allUser = userMapper.findAllUser();
        PageInfo<UserFindAllVo> userFindAllVoPageInfo = new PageInfo<>(allUser);
        return CommonResult.success(userFindAllVoPageInfo);
    }

    @Transactional(propagation = Propagation.NESTED )
    @Override
    public CommonResult deleteOneUser(String userId) {
      try {
          //删除用户
          userMapper.deleteByPrimaryKey(userId);
          //删除用户和部门关系
          deptUserMapper.deleteUserOneByUserId(userId);
      }catch (Exception e){
          return CommonResult.error("删除失败");
      }
        return CommonResult.success();
    }

    @Transactional(propagation = Propagation.NESTED )
    @Override
    public CommonResult deleteListUser(List<String> userId) {
         try{
             //删除用户
             userMapper.deleteUserList(userId);
             //删除用户和部门关系
             deptUserMapper.deleteUserList(userId);
         }catch (Exception e){
             e.printStackTrace();
             return CommonResult.error("批量删除失败");
         }
         return CommonResult.success();
    }


    @Transactional(propagation = Propagation.NESTED )
    @Override
    public CommonResult updateUserFindAllVo(UserFindAllVo userFindAllVo) {
        try {
            User user = new User();
            user.setUserId(userFindAllVo.getUserId());
            user.setDeptId(userFindAllVo.getDeptId());

            user.setSparessV1(userFindAllVo.getUserRealName());

            userMapper.updateByPrimaryKeySelective(user);
            deptUserMapper.updateUser(userFindAllVo.getUserId(), userFindAllVo.getDeptId(), userFindAllVo.getSparessV1());
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.error("修改用户信息失败");
        }
        return CommonResult.success();
    }

    //把前面事务当做子事务， 父事务成功后 才所有释放 否则 rollback
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public CommonResult AddUser(AddUserVo addUserVo) {
        //先判断 从前端传来的 deptId  和firmId  是否为null ""
        if(addUserVo.getDeptId()==null){
              return CommonResult.error("请选择一个部门");
        }
        if(addUserVo.getFirmId()==null){
            return CommonResult.error("请选择一个公司");
        }
        //先查数据库 是否存在该 用户名
        Integer exitByUserName = userMapper.findExitByUserName(addUserVo.getUserName());
        //我TMD傻了，...不能用1对比 应为有可能用户1234  他转为数字 坑。。。。
        if(exitByUserName!=null){
            return CommonResult.error("该用户账号已经存在");
        }
        User user = new User();
        String useruUuId = GetUUIdUtil.getUUId();
        user.setUserId(useruUuId);
        user.setUserName(addUserVo.getUserName());
        user.setSparessV1(addUserVo.getSparessV1());
        user.setDeptId(addUserVo.getDeptId());
        //密码md5加密后
          //String md5String = AesUtil.getMD5String(addUserVo.getUserPassWrod());
        //不知道用不用 MD5
        user.setUserPasswrod(addUserVo.getUserPassWrod());
     try {
         userMapper.insertSelective(user);
         //关联关系
         DeptUser deptUser = new DeptUser();
         //主键
         deptUser.setDeptUserId(GetUUIdUtil.getUUId());
         deptUser.setUserId(useruUuId);
         deptUser.setDeptId(addUserVo.getDeptId());
         deptUser.setSparessV1(addUserVo.getSparessV1());
         deptUserMapper.insertSelective(deptUser);
     }catch (Exception e){
         e.printStackTrace();
         return CommonResult.error("添加失败");
     }
        return CommonResult.success("添加成功");
    }


}
