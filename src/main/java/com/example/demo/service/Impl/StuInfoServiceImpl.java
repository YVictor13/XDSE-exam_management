package com.example.demo.service.Impl;

import com.example.demo.dto.ImportGradeDto;
import com.example.demo.dto.KeyInfoChangeApply;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.StuInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:guan
 * @2020/8/31 11:37
 * 文件信息：
 */
@Service
public class StuInfoServiceImpl implements StuInfoService {
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Resource
    private SignUpInfoMapper signUpInfoMapper;

    @Resource
    private ExamInfoMapper examInfoMapper;

    @Resource
    private ConstVariableMapper constVariableMapper;

    @Resource
    private GradeMapper gradeMapper;

    @Resource
    private MyOperatieMapper myOperatieMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private InfoChangeApplyMapper infoChangeApplyMapper;

    @Override
    public List<StudentInfo> countStuInfoByDis(String cityCode) {
        return studentInfoMapper.countStuInfoByDis(cityCode);
    }

    @Override
    public StudentInfo queryUnCriticalInfo(String studentId) {
        return studentInfoMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public Integer updateUnCriticalInfo(StudentInfo studentInfo) {
        return studentInfoMapper.updateUnCriticalInfo(studentInfo);
    }
    //设置当前考次与更新考生当前考次
    @Override
    public int setCurrExamNum(ExamInfo examInfo, Account account) {
        examInfoMapper.insert(examInfo);
        ConstVariable constVariable = new ConstVariable();
        constVariable.setName("cur_exam_num");
        constVariable.setValue(examInfo.getExamNum());
        constVariableMapper.updateByPrimaryKey(constVariable);
        myOperatieMapper.updateSignInfoExamNum(examInfo.getExamNum(),account.getAccount());
        return 0;
    }


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean ImportGrade(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<Grade> gradeList = new ArrayList<Grade>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            System.out.println("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }

        Grade grade;
        assert sheet != null;
        String examNum = null ;

        for(int r = 1; r<=sheet.getLastRowNum(); r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;
            }

            grade = new Grade("0","0","0",1,1,0);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            examNum = row.getCell(0).getStringCellValue();
            if(examNum==null||examNum.isEmpty()){
                System.out.println("导入失败，第"+(r+1)+"行，考次没有填写");
                return false;
            }else{
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String examCardNum = row.getCell(1).getStringCellValue();

                if(examCardNum==null||examCardNum.isEmpty()){
                    System.out.println("导入失败，第"+(r+1)+"行，准考证号没有填写");
                    return false;
                }else{

                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String courseCode = row.getCell(2).getStringCellValue();
                    if(courseCode==null||courseCode.isEmpty()){
                        System.out.println("导入失败，第"+(r+1)+"行，课程编号没有填写");
                        return false;
                    }else{
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        String gradeNum = row.getCell(3).getStringCellValue();
                        if(gradeNum==null||gradeNum.isEmpty()){
                            System.out.println("导入失败，第"+(r+1)+"行，成绩没有填写");
                            return false;
                        }else{

                            int num = Integer.parseInt(gradeNum);
                            //0--注销  1--正常
                            if(num<60){
                                grade.setValid(1);
                            }else{
                                grade.setValid(0);
                            }
                            System.out.println(examNum);
                            grade.setExamCardNum(examCardNum);
                            grade.setCourseCode(courseCode);
                            grade.setGrade(gradeNum);
                            grade.setGradeType(6);
                            grade.setPutinState(0);
                            gradeList.add(grade);
                        }

                    }
                }
            }
        }

        for(Grade g:gradeList){
            if(signUpInfoMapper.selectByPrimaryKey(g.getExamCardNum())==null){
                System.out.println("考生不存在");
                return false;
            }else if(courseMapper.selectByPrimaryKey(g.getCourseCode())==null){
                System.out.println("课程不存在");
                return false;
            }
            gradeMapper.insert(g);
//            myOperatieMapper.insertGrade(g.getExamCardNum(),g.getCourseCode(),g.getGrade(),g.getGradeType(),g.getValid(),g.getPutinState());
        }
        return true;

    }
    //查询考生的所有成绩
    @Override
    public List<ImportGradeDto> selectAllGrade(String studentId) {
        List<Grade> list =  myOperatieMapper.selectAllGrade();
        List<ImportGradeDto> ls = new ArrayList<>();
        List<SignUpInfo> signUpInfoList =myOperatieMapper.selectSignUpInfoById(studentId);
        for(SignUpInfo signUpInfo:signUpInfoList){
            SignUpInfo Info = myOperatieMapper.selectExamNumByExamNum(signUpInfo.getExamCardNum());
            ImportGradeDto importGradeDto;
            for(Grade grade:list){
                if(grade.getExamCardNum().equals(signUpInfo.getExamCardNum())){
                    importGradeDto = new ImportGradeDto();
                    importGradeDto.setExamNum(Info.getExamNum());
                    Course course = myOperatieMapper.selectByCourseCode(grade.getCourseCode());
                    if(course==null){
                        System.out.println("该课程不存在！！！");
                        return null;
                    }
                    importGradeDto.setCourseName(course.getCourseName());
                    importGradeDto.setCourseCode(grade.getCourseCode());
                    importGradeDto.setGrade(grade.getGrade());
                    ls.add(importGradeDto);
                }
            }
        }
        return ls;
    }

    //检查当前考次是否重复设置
    @Override
    public boolean HasExamInfo(String examNum) {
        ExamInfo examInfo=  examInfoMapper.selectByPrimaryKey(examNum);
        if(examInfo==null){
            return true;
        }
        return false;
    }



    @Override
    //查询所有的申请记录
    public List<InfoChangeApply> selectAllApply() {
        return myOperatieMapper.selectAll();
    }

    @Override
    //添加数据到用户信息修改中
    public String KeyModify(KeyInfoChangeApply KeyInfo,Account account) {
        InfoChangeApply apply =new InfoChangeApply();
        //设置当前的准考证号
        apply.setExamCardNum(account.getAccount());
        //0 状态设置为进入市级申请状态
        apply.setChangeState(0);
        // 0--当前申请为正常
        apply.setResultState(0);

        //设置ID
        int id = (int)(Math.random()*100000);
        apply.setId(Integer.toString(id));
        // 获取用户的信息与修改信息
        SignUpInfo signUpInfo = signUpInfoMapper.selectByPrimaryKey(account.getAccount());
        if(signUpInfo==null){
            return null;
        }

        StudentInfo studentInfo  = studentInfoMapper.selectByPrimaryKey(signUpInfo.getStudentId());
        if(studentInfo==null)
            return null;

        //修改个人名字
        if(!KeyInfo.getName().equals("")){
            apply.setNewValue(KeyInfo.getName());
            apply.setOldValue(studentInfo.getName());
            apply.setFieldName("姓名");
            apply.setChangeReason(KeyInfo.getChangeReason());
            infoChangeApplyMapper.insertSelective(apply);
            return "姓名";
        }
        //修改个人身份证号
        if(!KeyInfo.getIdCardNum().equals("")){
            apply.setNewValue(KeyInfo.getIdCardNum());
            apply.setOldValue(studentInfo.getIdCardNum());
            apply.setFieldName("身份证号");
            apply.setChangeReason(KeyInfo.getChangeReason());
            infoChangeApplyMapper.insertSelective(apply);
            return "身份证号";
        }
        //设置个人民族
        if(!Integer.toString(KeyInfo.getNation()).equals("")){
            apply.setNewValue(Integer.toString(KeyInfo.getNation()));
            apply.setOldValue(Integer.toString(studentInfo.getNation()));
            apply.setFieldName("民族");
            apply.setChangeReason(KeyInfo.getChangeReason());
            infoChangeApplyMapper.insertSelective(apply);
            return "民族";
        }

        //设置拟报专业代码
        if(!KeyInfo.getMajorNum().equals("")){
            apply.setNewValue(KeyInfo.getMajorNum());
            apply.setOldValue(signUpInfo.getMajorNum());
            apply.setFieldName("拟报专业");
            apply.setChangeReason(KeyInfo.getChangeReason());
            infoChangeApplyMapper.insertSelective(apply);
            return "拟报专业";
        }
        //        修改性别

        if(! Integer.toString(KeyInfo.getSex()).equals("")){
            apply.setNewValue(Integer.toString(KeyInfo.getSex()));
            apply.setOldValue(Integer.toString(studentInfo.getSex()));
            apply.setFieldName("性别");
            apply.setChangeReason(KeyInfo.getChangeReason());
            infoChangeApplyMapper.insertSelective(apply);
            return "性别";
        }
        return null;
    }

    @Override
    public int ECNModify(String changeReason,Account account) {
        InfoChangeApply apply =new InfoChangeApply();

        //设置ID
        int id = (int)(Math.random()*100000);
        apply.setId(Integer.toString(id));

        //自动生成准考证号
        String  ExamCardNum = "0101101";
        int code = (int)(Math.random()*10000);
        StringBuilder buf = new StringBuilder();
        buf.append(ExamCardNum);
        buf.append(code);
        apply.setNewValue(buf.toString());
        //添加用户当前的准考证号
        apply.setExamCardNum(account.getAccount());
        apply.setOldValue(account.getAccount());
        apply.setFieldName("准考证号");
        apply.setChangeReason(changeReason);
        //0 状态设置为进入市级申请状态
        apply.setChangeState(0);
        // 0--当前申请为正常
        apply.setResultState(0);
        return infoChangeApplyMapper.insertSelective(apply);
    }

    @Override
    public boolean HasModifyApply(Account account) {
        List<InfoChangeApply> InfoList=  myOperatieMapper.selectAll();
        if(InfoList==null||InfoList.isEmpty()){
            return false;
        }
        return true;
    }

    //获取修改后的数据
    @Override
    public InfoChangeApply FindChangeApplyByAccount(Account account,String fieldName) {
        InfoChangeApply infoChangeApply = myOperatieMapper.selectByAccount(account.getAccount(),fieldName);
        return infoChangeApply;
    }

    //获取用户当前关键信息
    @Override
    public KeyInfoChangeApply FindKeyInfoByAccount(Account account) {

        SignUpInfo signUpInfo = signUpInfoMapper.selectByPrimaryKey(account.getAccount());
        if(signUpInfo==null){
            return null;
        }

        StudentInfo studentInfo  = studentInfoMapper.selectByPrimaryKey(signUpInfo.getStudentId());
        if(studentInfo==null)
            return null;

        KeyInfoChangeApply keyInfoChangeApply =new KeyInfoChangeApply();
        keyInfoChangeApply.setIdCardNum(studentInfo.getIdCardNum());
        keyInfoChangeApply.setImgPath(studentInfo.getPhotoPath());
        keyInfoChangeApply.setMajorNum(signUpInfo.getMajorNum());
        keyInfoChangeApply.setName(studentInfo.getName());
        keyInfoChangeApply.setNation(studentInfo.getNation());
        return keyInfoChangeApply;
    }
    //获取用户的学生ID
    @Override
    public String FindIdByAccount(Account account) {
        return signUpInfoMapper.selectByPrimaryKey(account.getAccount()).getStudentId();
    }
    @Override
    public Integer updatePicPathById(String stuId, String filename) {
        int ret = studentInfoMapper.updatePicPathById(stuId, filename);
        if(ret!=1)
            return 1; // 不成功返回1
        return 0; // 成功返回0
    }

    @Override
    public List<String> queryAllCity() {
        return studentInfoMapper.queryAllCity();
    }
}
