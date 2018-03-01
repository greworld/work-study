package com.greworld.edu.account;

import com.greworld.edu.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void testTransfer(){
        try {
            accountService.transfer("tom", "mic", 10099.37823D);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

