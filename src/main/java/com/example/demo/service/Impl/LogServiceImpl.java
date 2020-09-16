package com.example.demo.service.Impl;

import com.example.demo.mapper.MyOperatieMapper;
import com.example.demo.model.InfoChangeLog;
import com.example.demo.model.InfoOperateLog;
import com.example.demo.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    private MyOperatieMapper myOperatieMapper;

    @Override
    public List<InfoOperateLog> selectAllInfoOperateLog() {
        return myOperatieMapper.selectAllInfoOperateLog();
    }

    @Override
    public List<InfoChangeLog> selectAllInfoChangeLog() {
        return myOperatieMapper.selectAllInfoChangeLog();
    }

    @Override
    public List<InfoOperateLog> selectInfoByNDOperateLog(String name, String date) throws ParseException {
        List<InfoOperateLog> infoOperateLogList = selectAllInfoOperateLog();
        List<InfoOperateLog> list = new ArrayList<>();
        int index = 1;
        if (!name.equals("") && date.equals("")) {
            for (InfoOperateLog infoOperateLog : infoOperateLogList) {
                if (infoOperateLog.getOperatorName().equals(name)) {
                    InfoOperateLog info = new InfoOperateLog();
                    info.setId(index++);
                    info.setOperatorName(infoOperateLog.getOperatorName());
                    info.setOperateContent(infoOperateLog.getOperateContent());
                    info.setOperateDate(infoOperateLog.getOperateDate());
                    list.add(info);
                }
            }
        } else if (name.equals("") && !date.equals("")) {
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for (InfoOperateLog infoOperateLog : infoOperateLogList) {
                if (infoOperateLog.getOperateDate().equals(newDate)) {
                    InfoOperateLog info = new InfoOperateLog();
                    info.setId(index++);
                    info.setOperatorName(infoOperateLog.getOperatorName());
                    info.setOperateContent(infoOperateLog.getOperateContent());
                    info.setOperateDate(infoOperateLog.getOperateDate());
                    list.add(info);
                }
            }
        } else {
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for (InfoOperateLog infoOperateLog : infoOperateLogList) {
                if (infoOperateLog.getOperateDate().equals(newDate) && infoOperateLog.getOperatorName().equals(name)) {
                    InfoOperateLog info = new InfoOperateLog();
                    info.setId(index++);
                    info.setOperatorName(infoOperateLog.getOperatorName());
                    info.setOperateContent(infoOperateLog.getOperateContent());
                    info.setOperateDate(infoOperateLog.getOperateDate());
                    list.add(info);
                }
            }
        }

        return list;
    }

    @Override
    public List<InfoChangeLog> selectByEINDInfoChangeLog(String examCardNum, String idCard,String date) throws ParseException {
        List<InfoChangeLog>   infoChangeLogList= selectAllInfoChangeLog();
        List<InfoChangeLog> list = new ArrayList<>();
        int index =1;
        if(!examCardNum.equals("")&&idCard.equals("")&&date.equals("")){
            for(InfoChangeLog infoChangeLog:infoChangeLogList){
                if(infoChangeLog.getExamCardNum().equals(examCardNum)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }
        }else if(examCardNum.equals("")&&!idCard.equals("")&&date.equals("")){
            for(InfoChangeLog infoChangeLog:infoChangeLogList){
                if(infoChangeLog.getIdCardNum().equals(idCard)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }

        }else if(examCardNum.equals("")&&idCard.equals("")&&!date.equals("")){
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-d").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for(InfoChangeLog infoChangeLog:infoChangeLogList){

                if(infoChangeLog.getChangeDate().equals(newDate)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }

        }else if(!examCardNum.equals("")&&!idCard.equals("")&&date.equals("")){
            for(InfoChangeLog infoChangeLog:infoChangeLogList){
                if(infoChangeLog.getExamCardNum().equals(examCardNum)&&infoChangeLog.getIdCardNum().equals(idCard)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }

        }else if(!examCardNum.equals("")&&idCard.equals("")&&!date.equals("")){
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for(InfoChangeLog infoChangeLog:infoChangeLogList){

                if(infoChangeLog.getExamCardNum().equals(examCardNum)&&infoChangeLog.getChangeDate().equals(newDate)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }

        }else if(examCardNum.equals("")&&!idCard.equals("")&&!date.equals("")){
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for(InfoChangeLog infoChangeLog:infoChangeLogList){

                if(infoChangeLog.getIdCardNum().equals(idCard)&&infoChangeLog.getChangeDate().equals(newDate)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }
        }else{
            Date orderDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String newDate = new SimpleDateFormat("yyyyMMdd").format(orderDateStart);
            for(InfoChangeLog infoChangeLog:infoChangeLogList){
                if(infoChangeLog.getExamCardNum().equals(examCardNum)&&infoChangeLog.getIdCardNum().equals(idCard)&&infoChangeLog.getChangeDate().equals(newDate)){
                    InfoChangeLog info = new InfoChangeLog();
                    info.setId(index++);
                    info.setExamCardNum(infoChangeLog.getExamCardNum());
                    info.setIdCardNum(infoChangeLog.getIdCardNum());
                    info.setIdCardType(infoChangeLog.getIdCardType());
                    info.setChangeContent(infoChangeLog.getChangeContent());
                    info.setChangeDate(infoChangeLog.getChangeDate());
                    list.add(info);
                }
            }
        }
        return list;
    }
}