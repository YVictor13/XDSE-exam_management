package com.example.demo.service.Impl;

import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/9 10:10
 * 文件信息：
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Override
    public List<Course> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }
}
