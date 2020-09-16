package com.example.demo;

import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.mapper.ExamInfoMapper;
import com.example.demo.mapper.SignUpInfoMapper;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.model.ExamInfo;
import com.example.demo.model.StudentInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    private SqlSession session;

    @BeforeEach
    public void initSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession(true); // 开启自动提交
    }

    @Test
    public void updatePicPathById() {

        StudentInfoMapper stuInfoMapper = session.getMapper(StudentInfoMapper.class);

        int ret = stuInfoMapper.updatePicPathById("20200001", "20200001.png");

        assertEquals(1, ret);
    }

    @Test
    public void getBasicInfoByECN() {
        SignUpInfoMapper signUpInfoMapper = session.getMapper(SignUpInfoMapper.class);

        StuBasicInfo stuBasicInfo = signUpInfoMapper.getStuBasicInfoByECN("010120100001");

        assertNotNull(stuBasicInfo);
        System.out.println(stuBasicInfo);
        assertNotNull(stuBasicInfo.getStudentId());
    }

    @Test
    public void getAllExamInfos() {
        ExamInfoMapper examInfoMapper = session.getMapper(ExamInfoMapper.class);

        List<ExamInfo> examInfos = examInfoMapper.selectAllExamInfo();

        assertNotNull(examInfos);

        System.out.println(examInfos);
    }

    @Test
    public void getStuBasicInfosByExamNum() {
        SignUpInfoMapper signUpInfoMapper = session.getMapper(SignUpInfoMapper.class);
        List<StuBasicInfo> stuBasicInfos = signUpInfoMapper.getStuBasicInfoByExamNum("202010");

        assertNotEquals(0, stuBasicInfos.size());
    }

    @Test
    public void getStuBasicInfoByQMS() {
        SignUpInfoMapper signUpInfoMapper = session.getMapper(SignUpInfoMapper.class);

        QuitManageSearch quitManageSearch = new QuitManageSearch();
        quitManageSearch.setExamNum("202010");
        quitManageSearch.setPageNum(1);
        quitManageSearch.setExamCardNum("");
        List<StuBasicInfo> stuBasicInfos = signUpInfoMapper.getStuBasicInfoByQMS(quitManageSearch);

        assertNotEquals(0, stuBasicInfos.size());
    }

    @Test
    public void getStuIdByECN() {
        SignUpInfoMapper signUpInfoMapper = session.getMapper(SignUpInfoMapper.class);

        List<String> ecnList = new ArrayList<>();
        ecnList.add("010120100001");
        ecnList.add("010120100002");

        List<String> result = signUpInfoMapper.getStuIdByECN(ecnList);

        assertNotEquals(0, result.size());
        System.out.println(result);
    }

    @Test
    public void quitStuByStuId(){
        StudentInfoMapper studentInfoMapper = session.getMapper(StudentInfoMapper.class);

        List<String> stuIdList = new ArrayList<>();
        stuIdList.add("20200001");
        stuIdList.add("20200002");

        Integer result = studentInfoMapper.quitStuById(stuIdList);

        assertEquals(stuIdList.size(), result);

        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey("20200001");
        assertEquals(5, studentInfo.getInfoState());

        // 创建session时开启自动提交，否则需要 session.commit()
    }
}
