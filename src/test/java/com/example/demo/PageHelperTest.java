package com.example.demo;

import com.example.demo.dto.QuitManageSearch;
import com.example.demo.dto.StuBasicInfo;
import com.example.demo.service.SignUpInfoService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PageHelperTest {

    @Resource
    private SignUpInfoService signUpInfoService;

    @Test
    public void test() {
        QuitManageSearch quitManageSearch = new QuitManageSearch();
        quitManageSearch.setExamNum("202010");
        quitManageSearch.setPageNum(1);
        PageInfo<StuBasicInfo> stuBasicInfoPageInfos = signUpInfoService.getStuBasicInfoByQMS(quitManageSearch);

        assertNotNull(stuBasicInfoPageInfos);
        System.out.println(stuBasicInfoPageInfos);
    }
}
