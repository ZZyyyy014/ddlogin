package com.tx.dllogin;

import com.tx.dllogin.dao.DeptMapper;
import com.tx.dllogin.dao.DeptUserMapper;
import com.tx.dllogin.dao.OrderMapper;
import com.tx.dllogin.dao.UserMapper;
import com.tx.dllogin.model.Order;
import com.tx.dllogin.utill.AesUtil;
import com.tx.dllogin.vo.UserFindAllVo;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DlloginApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptUserMapper deptUserMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void tsets() {
        List<UserFindAllVo> allUser = userMapper.findAllUser();
        for (int i = 0; i < allUser.size(); i++) {
            System.out.println(allUser.get(i).getLoginDate());
        }
    }


    @Test
    public void test2() {
        String iv = "0102030405060708";
        byte[] crypted = null;
        try {
            byte[] decode = Hex.decodeHex("F50DBAB515286F4C88D44CADE0819334829C15F60D859F43");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skey = new SecretKeySpec(decode, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivspec);
            crypted = cipher.doFinal("123456".getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        System.out.println(new String(Hex.encodeHex(crypted)).toUpperCase());

    }


    @Test
    public void tset24() {
        String appid = "feijing&functionId=getAidInfo4B&body={token:3BDF34D9BAABBD44BF92078CD0E9BBC3,ip:127.0.0.1,pin:森马萌2,art:imee,appId:im.waiter,os:windows,aidClientType:pc,aidClientVersion:9.4.9.3}";
        Object aid = AesUtil.getAid("3BDF34D9BAABBD44BF92078CD0E9BBC3", "森马萌2");
        System.out.println(aid);
    }


    @Test
    public void tset25() {
        String s = AesUtil.loginDd("森马萌2", "QWEQWE123");
        System.out.println(s);
    }

    @Test
    public void tset26() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        userMapper.deleteUserList(list);
    }


    @Test
    public void tset27() {
        List<Order> likeAllOrder = orderMapper.findLikeAllOrder(null,
                null,
                "",
                null,
                null, null,
                null);
        for (Order order : likeAllOrder) {
            System.out.println(order);
        }



    }


}
