package com.tx.dllogin;

import com.tx.dllogin.dao.*;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.vo.UserFindAllVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DlloginApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptUserMapper deptUserMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShopMapper shopMapper;

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
    }


    @Test
    public void tset25() {
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
        Shop shopByNameAndPassWord = shopMapper.findShopByNameAndPassWord("1", "123");
        System.out.println("----"+shopByNameAndPassWord.toString());
    }


}
