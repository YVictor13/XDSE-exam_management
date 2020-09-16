package com.example.demo.service;


//import com.example.demo.dto.InfoDto;
import com.example.demo.model.Account;
import java.util.List;

public interface UserService {

    //用户登录
    int Login(Account account);

    // 通过账户获取账户信息
    Account getAccountById(String id);

    //    关键信息修改审核(利用准考证号获取数据)
//    List<InfoDto> listKeyInfoApply(String account);

//    根据考生帐号查询studentId
    String queryStudentByAccount(String account);

}
