package com.greworld.edu.account.service;

import com.greworld.edu.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
@Service
public class AccountOutService {
    @Autowired
    AccountDao accountDao;

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public int transferForOut(final String out,Double money) throws Exception{
        int outCount = accountDao.upateForOut(out, money);
//		if(outCount == 0){
//			throw new Exception("转出失败");
//		}
        return outCount;
    }

}
