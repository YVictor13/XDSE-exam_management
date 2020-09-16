package com.example.demo.service;

import com.example.demo.model.TimeManage;

import java.util.List;

/**
 * @author:guan
 * @2020/8/31 16:41
 * 文件信息：
 */
public interface TimeManageService {
    int updateTimeManage(TimeManage timeManage);
    List<TimeManage> queryAllTimeSet();
    TimeManage queryTimeById(Integer id);
}
