package com.example.demo.mapper;

import com.example.demo.model.TimeManage;

import java.util.List;

public interface TimeManageMapper {
    int insert(TimeManage record);

    int insertSelective(TimeManage record);

    int updateTimeManage(TimeManage timeManage);

    List<TimeManage> queryAllTimeSet();

    TimeManage queryTimeById(Integer id);
}