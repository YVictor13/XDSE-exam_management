package com.example.demo.service.Impl;

import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.mapper.SignUpInfoMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.model.SignUpInfo;
import com.example.demo.service.SignUpInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:guan
 * @2020/9/2 9:20
 * 文件信息：
 */
@Service
public class SignUpInfoServiceImpl implements SignUpInfoService {
    @Resource
    private SignUpInfoMapper signUpInfoMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Override
    public List<String> queryExamCardNum(String studentId) {
        return signUpInfoMapper.queryExamCardNumById(studentId);
    }

    @Override
    public String getStuIdByECN(String ecn) {

        SignUpInfo signUpInfo = signUpInfoMapper.selectByPrimaryKey(ecn);
        if(signUpInfo!=null){
            return signUpInfo.getStudentId();
        }
        return "";
    }

    @Override
    public StuBasicInfo getStuBasicInfoByECN(String ecn) {
        return signUpInfoMapper.getStuBasicInfoByECN(ecn);
    }

    @Override
    public PageInfo<StuBasicInfo> getStuBasicInfoByQMS(QuitManageSearch quitManageSearch) {
        int pageNum = quitManageSearch.getPageNum();
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        List<StuBasicInfo> stuBasicInfos = signUpInfoMapper.getStuBasicInfoByQMS(quitManageSearch);
        return new PageInfo<>(stuBasicInfos);
    }

    @Override
    public void quitStu(List<String> ecnList) {
        List<String> stuIdList = signUpInfoMapper.getStuIdByECN(ecnList);
        Integer result = studentInfoMapper.quitStuById(stuIdList);

        assert result==stuIdList.size();
    }
}
