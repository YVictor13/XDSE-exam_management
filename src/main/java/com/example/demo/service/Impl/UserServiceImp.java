package com.example.demo.service.Impl;



import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.Account;
import com.example.demo.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@MapperScan("/com.example.demo.mapper")
public class UserServiceImp implements UserService {
    @Autowired(required = false)
    private AccountMapper accountMapper;
    /*
        用户登录
        0:输入不能为空
        1：登录成功
        2:密码不正确
     */
    @Override
    public int Login(Account account) {

        //        输入为空
        if(account==null||account.getAccount().equals("")||account.getPassword().equals("")){
            return -1;
        }
        //如果输入用户存在，则直接登录
        Account account1 = accountMapper.selectByPrimaryKey(account.getAccount());
        if (account1 != null) {
            if(account.getPassword().equals(account1.getPassword())){
                return account1.getRole();
            }
            return 3;
        }
        return 3;
    }


    @Override
    public Account getAccountById(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public String queryStudentByAccount(String account) {
        return accountMapper.queryStudentByAccount(account);
    }

//    @Override
//    public List<InfoDto> listKeyInfoApply(String account) {
//
//
//        return null;
//    }
}
