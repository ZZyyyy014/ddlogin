package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.*;
import com.tx.dllogin.model.DeptUser;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.model.User;
import com.tx.dllogin.services.UserService;
import com.tx.dllogin.shiro.ShiroMyUtills;
import com.tx.dllogin.shiro.realm.MyRealm;
import com.tx.dllogin.utill.*;
import com.tx.dllogin.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptUserMapper deptUserMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private FirmMapper firmMapper;

    @Autowired
    private ShopMapper shopMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)//有1个事务  当前只执行一次数据库操作  查询无所谓
    public CommonResult login(String userName, String passWrod, String captcha, HttpServletRequest request) {
        String attribute="";
        try {
            attribute = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        } catch (NullPointerException n) {
            return CommonResult.error("验证码失效,请点击验证码图片");
        }

        if (!attribute.equals(captcha)) {
            return CommonResult.error("验证码错误,请重新登录");
        }

        User userByName = userMapper.findUserByName(userName);
        if (userByName == null) {
            return CommonResult.error("该用户未注册");
        }
        //   getMD5String 获取密码加密
        /*
        String md5String = AesUtil.getMD5String(passWrod);
        if(userByName.getUserPasswrod()!=md5String){
            return CommonResult.error("密码错误");
        }  */
        //
        if (!userByName.getUserPasswrod().equals(passWrod)) {
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
        try {
            // 登录时候认证
            Subject subject = ShiroMyUtills.getSubject(new MyRealm());
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWrod);
            subject.login(usernamePasswordToken);
            log.info(userName + "----token认证成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return CommonResult.error("shiro加密,账号错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return CommonResult.error("shiro加密,密码错误");
        }
        //生成toekn
        String token = "";
        HashMap<String, String> map1 = new HashMap<>();
        //把当前用户账号 存入session
        request.getSession().setAttribute("userNameOne",userByName.getUserName());

        map1.put("userName", userByName.getUserName());
        map1.put("userId", userByName.getUserId());
        map1.put("level", userByName.getLevelss());
        token = JwtUtil.getToken(map1);
        HashMap<String, String> map = new HashMap<>();
        map.put("jwtToken", token);

        log.info(userName + "---登录成功生成token");
        return CommonResult.success("登录成功", map);
    }


    @Override //查询分页用户
    public CommonResult findAllLogin(Integer pageNum, Integer pageSize) {
        //设置开启分页参数    1.当前业数   2.每页多少
        PageHelper.startPage(pageNum, pageSize);

        //获取到账号  除了超级管理员 其他的 只能查看该用户 同一公司 同一部门
        String userNameOne = request.getSession().getAttribute("userNameOne").toString();
        UserListByName userListByName = userMapper.findUserListByName(userNameOne);
       //不是超级管理员     只能查询 到同一公司 同一部门
        List<UserFindAllVo> allUser = userMapper.findUserByUserList(userListByName.getLevelss()
                                                                   ,userListByName.getDeptId()
                                                                   ,userListByName.getFirmId());
       // List<UserFindAllVo> allUser = userMapper.findAllUser();

        PageInfo<UserFindAllVo> userFindAllVoPageInfo = new PageInfo<>(allUser);

        return CommonResult.success(userFindAllVoPageInfo);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public CommonResult deleteOneUser(String userId) {

        String userName = LogUtill.GetUserName(request);
        try {
            String roles = request.getSession().getAttribute("roles").toString();
            log.info("删除用户时候取出 当前操作用户账号:" + userName);
            //判断 是否时部门管理员
            if ("admin_min".equals(roles)) {
                //根据用户账号查询 部门id  公司id
                findDeptAndFirmVo deIdAndFirId = userMapper.findDeIdAndFirId(userName);
                List<findDeptAndFirmVo> deIdAndFirIdList = userMapper.findDeIdAndFirIdList(Arrays.asList(userId));
                for (findDeptAndFirmVo userFindAllVo : deIdAndFirIdList) {
                    if (!deIdAndFirId.getDeptId().equals(userFindAllVo.getDeptId()) &
                            !deIdAndFirId.getFirmId().equals(userFindAllVo.getFirmId())) {
                        return CommonResult.error("部门管理员无权限删除 不是同一公司同一部门下用户");
                    }
                }
            }

            User user = userMapper.selectByPrimaryKey(userId);
            //删除用户
            userMapper.deleteByPrimaryKey(userId);
            log.info("当前用户账号:{} 成功删除用户:{}", userName, user);
            //删除用户和部门关系
            deptUserMapper.deleteUserOneByUserId(userId);
            log.info("当前用户账号:{} 成功删除用户id绑定关系(公司部门):{}", userName, userId);
            return CommonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("账号{}删除失败", userName);
            return CommonResult.error("删除失败");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public CommonResult deleteListUser(List<String> userId) {
        String userName = LogUtill.GetUserName(request);
        try {
            String roles = request.getSession().getAttribute("roles").toString();
            //从token中取出 id 在取出frimId  deptId对比 是否时同一公司 同一部门不是就 报错
            //判断 是否时部门管理员
            if ("admin_min".equals(roles)) {
                //根据用户账号查询 部门id  公司id
                findDeptAndFirmVo deIdAndFirId = userMapper.findDeIdAndFirId(userName);
                List<findDeptAndFirmVo> deIdAndFirIdList = userMapper.findDeIdAndFirIdList(userId);
                for (findDeptAndFirmVo userFindAllVo : deIdAndFirIdList) {
                       //&  只要满足其中一个条件就行了
                    if (!deIdAndFirId.getDeptId().equals(userFindAllVo.getDeptId()) & !
                            deIdAndFirId.getFirmId().equals(userFindAllVo.getFirmId())) {
                        return CommonResult.error("部门管理员无权限删除 不是同一公司同一部门");
                    }
                }
            }


            //删除用户
            userMapper.deleteUserList(userId);
            log.info("当前用户:{} 成功删除用户集合:{}", userName, userId);
            //删除用户和部门关系
            deptUserMapper.deleteUserList(userId);
            log.info("当前用户:{} 成功删除用户集合绑定关系(公司部门):{}", userName, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error("批量删除失败");
        }
        return CommonResult.success();
    }


    @Transactional(propagation = Propagation.NESTED)
    @Override
    public CommonResult updateUserFindAllVo(UserFindAllVo userFindAllVo) {
        try {
            String roles = request.getSession().getAttribute("roles").toString();
            String userName = LogUtill.GetUserName(request);

            //判断 是否时部门管理员
            if ("admin_min".equals(roles)) {
             /*    //从token中取出 id 在取出frimId  deptId对比 是否时同一公司 同一部门不是就 报错
                String jwtToken = request.getHeader("jwtToken");
                //从jwtUtill 中取出 sing签名
                String Sing ="123456789asdzxc";
                DecodedJWT verify = JWT.require(Algorithm.HMAC256(Sing)).build().verify(jwtToken);
                //登录时候 传的map取出来。。。 用户账号
                String userName = verify.getClaim("userName").asString();*/
                //根据用户账号查询 部门id  公司id
                findDeptAndFirmVo deIdAndFirId = userMapper.findDeIdAndFirId(userName);
                if (!deIdAndFirId.getDeptId().equals(userFindAllVo.getDeptId()) && !
                        deIdAndFirId.getFirmId().equals(userFindAllVo.getSparessV1())) {
                    return CommonResult.error("部门管理员无权限删除 不是同一公司同一部门");
                }
            }

            //根据 部门名称查询 出部门id
            String deptIdByName = deptMapper.findDeptIdByName(userFindAllVo.getDeptName());
            //根据公司名称  查询公司Id
            String firmIdByName = firmMapper.findFirmIdByName(userFindAllVo.getFirmName());

            User user = new User();
            user.setUserId(userFindAllVo.getUserId());
            user.setDeptId(deptIdByName);
            user.setSparessV1(userFindAllVo.getUserRealName());
            userMapper.updateByPrimaryKeySelective(user);
            log.info("用户账号:{} 成功修改单个用户:{}", userName, user);
            deptUserMapper.updateUser(userFindAllVo.getUserId(), deptIdByName, firmIdByName);
            HashMap<String, Object> map = new HashMap<>();
            map.put("用户ID", userFindAllVo.getUserId());
            map.put("部门名称", deptIdByName);
            map.put("部门公司", firmIdByName);
            log.info("用户账号:{} 成功修改单个用户绑定关系:{}", userName, map);
        } catch (Exception e) {
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
        if (addUserVo.getDeptId() == null) {
            return CommonResult.error("请选择一个部门");
        }
        if (addUserVo.getFirmId() == null) {
            return CommonResult.error("请选择一个公司");
        }
        //先查数据库 是否存在该 用户名
        String exitByUserName = userMapper.findExitByUserName(addUserVo.getUserName());
        //我TMD傻了，...不能用1对比 应为有可能用户1234  他转为数字 坑。。。。
        if (exitByUserName != null) {
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
        user.setUserPasswrod(addUserVo.getUserPassWrod());

        String s = LogUtill.GetUserName(request);
        try {
            userMapper.insertSelective(user);
            log.info("账号:{} 添加用户成功：{}", s, user);
            //关联关系
            DeptUser deptUser = new DeptUser();
            //主键
            deptUser.setDeptUserId(GetUUIdUtil.getUUId());
            deptUser.setUserId(useruUuId);
            deptUser.setDeptId(addUserVo.getDeptId());
            //SparessV1 代表公司Id
            deptUser.setSparessV1(addUserVo.getFirmId());
            deptUserMapper.insertSelective(deptUser);
            log.info("账号:{} 添加用户关系成功：{}", s, deptUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("账号:{} 添加用户失败：{}", s, user);
            return CommonResult.error("添加失败");
        }
        return CommonResult.success("添加成功");
    }


    @Override
    public CommonResult findLogRoter() {
        HttpSession session = request.getSession();
        String roleId = session.getAttribute("roleId").toString();
        if (roleId == null) return CommonResult.error("登录超时,请重新登录");
        List<FindLogRoterVo> loginShiroParm = userMapper.findLoginShiroParm(roleId);
        return CommonResult.success(loginShiroParm);
    }


    @Override//京麦 免登陆 商户账号  密码+    类型1京东   2拼多多 3淘宝
    public CommonResult JMlogin(String shopName, String passWord) {
        //谁操作 登录  的 用户账号
        String userName = LogUtill.GetUserName(request);
        log.info("用户账号{} ---京麦免登陆商户账号{}-密码{}", userName, shopName, passWord);
        String ddUrl = "";
        if(shopName==null ||shopName.trim().equals("")||passWord==null||passWord.trim().equals("")){
           return CommonResult.error("请填写用户名或者密码");
        }
        try {
            //取京东 登录京麦
            ddUrl = AesUtil.loginDd(shopName, passWord);
            if (ddUrl=="") return  CommonResult.error("账号无权限或者密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户账号{} 登录京麦失败 账号{}--密码{}", userName,shopName,passWord);
            return CommonResult.error("登录京麦失败");
        }
        try {
            log.info("用户账号{} ---京麦免登陆商户账号成功", userName);
            //shopName   passWord
            Shop shopByNameAndPassWord = shopMapper.findShopByNameAndPassWord(shopName, passWord);
            String IP = IpUtill.getClientIpAddress(request);
            if (shopByNameAndPassWord == null ) {
                //添加进去
                Shop shop = new Shop();
                shop.setShopUserName(shopName);
                shop.setShopUserPasswrod(passWord);
                shop.setShopIp(IP);
                shop.setUserTypess("1");
                shop.setShopLoginDate(new Date());
                shop.setSparessV1(userName);
                shopMapper.insertSelective(shop);
                log.info("用户账号{} --添加店铺{}", userName, shop);
            } else {
                //店铺已经存在   修改
                shopByNameAndPassWord.setShopIp(IP);
                shopByNameAndPassWord.setShopLoginDate(new Date());
                shopMapper.updateByPrimaryKeySelective(shopByNameAndPassWord);
            }
            return CommonResult.success(ddUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户账号{} ---京麦免登陆商户账号失败", userName);
            return CommonResult.error("店铺登录失败");
        }
    }


}
