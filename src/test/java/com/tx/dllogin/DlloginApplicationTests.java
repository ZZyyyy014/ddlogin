package com.tx.dllogin;

import com.tx.dllogin.dao.*;
import com.tx.dllogin.services.OrderService;
import com.tx.dllogin.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private OrderService orderService;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserService userService;

    @Test
    public void tset24() {


    }


}
