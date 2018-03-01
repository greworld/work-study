package com.greworld.edu.account.service;

import com.greworld.edu.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
@Service
public class AccountInService {
    @Autowired
    private AccountDao accountDao;

    public int transferForIn(final String in,Double money) throws Exception{
        int inCount = accountDao.upateForIn(in, money);

//		if(inCount == 0){
//			throw new Exception("转入失败");
//		}
        return inCount;
    }

}
