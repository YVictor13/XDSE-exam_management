package com.example.demo.service.Impl;

import com.example.demo.mapper.TimeManageMapper;
import com.example.demo.model.TimeManage;
import com.example.demo.service.TimeManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/8/31 16:41
 * 文件信息：
 */
@Service
public class TimeManageServiceImpl implements TimeManageService {
    @Resource
    private TimeManageMapper timeManageMapper;
    @Override
    public int updateTimeManage(TimeManage timeManage) {
        return timeManageMapper.updateTimeManage(timeManage);
    }

    @Override
    public List<TimeManage> queryAllTimeSet() {
        return timeManageMapper.queryAllTimeSet();
    }

    @Override
    public TimeManage queryTimeById(Integer id) {
        return timeManageMapper.queryTimeById(id);
    }
}
