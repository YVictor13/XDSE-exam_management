package com.example.demo.service;


import com.example.demo.model.InfoChangeLog;
import com.example.demo.model.InfoOperateLog;

import java.text.ParseException;
import java.util.List;

public interface LogService {


    //查询所有的日志操作记录
    List<InfoOperateLog> selectAllInfoOperateLog();


    //查询所有的业务修改操作记录
    List<InfoChangeLog> selectAllInfoChangeLog();


    //通过名字、日期来查询操作日志
    List<InfoOperateLog> selectInfoByNDOperateLog(String name, String date) throws ParseException;


    //通过准考证号、身份证号、名字、日期查找修改日志
    List<InfoChangeLog> selectByEINDInfoChangeLog(String examCardNum, String idCard, String date) throws ParseException;



}
