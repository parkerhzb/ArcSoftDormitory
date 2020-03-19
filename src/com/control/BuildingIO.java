package com.control;

import com.dao.StudentDao;


import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
查询寝室楼每日的人流记录表信息
输入参数：日期data与楼号building
 */

public class BuildingIO {
    public BuildingIO(){
        super();
    }

    /*
    public class DailyStudent{
        String Id;
        String Name;
        String Time;//当日进出时间
        String Flag;//标记进还是出
        String Tel;
        String Room;
    }
     */

    //获取当天的Building楼的人流表
    public ArrayList<StudentDao.DailyStudent> DailyBuilding(String Building){
        //转换Building to Building_Daily
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab_name=buildingToBuilding_daily.transform(Building);

        StudentDao studentDao = new StudentDao();
        //获得Date日人流表
        ArrayList<StudentDao.DailyStudent> DailyList = new ArrayList<StudentDao.DailyStudent>();

        //获取当时开门时间
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time=dateTime.format(formatter);

        DailyList = studentDao.GetDaily(tab_name, time.substring(0,10));
        return DailyList;

    }

    //获取Date天的Building楼的人流表
    public ArrayList<StudentDao.DailyStudent> DailyBuilding(String Date,String Building){
        //转换Building to Building_Daily
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab_name=buildingToBuilding_daily.transform(Building);

        StudentDao studentDao = new StudentDao();
        //获得Date日人流表
        ArrayList<StudentDao.DailyStudent> DailyList = new ArrayList<StudentDao.DailyStudent>();

        DailyList = studentDao.GetDaily(tab_name, Date);
        return DailyList;

    }

    //获取当天的每小时的Building楼的人流数
    public int[][] EachHourBuilding(String Building){
        //转换Building to Building_Daily
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab_name=buildingToBuilding_daily.transform(Building);

        StudentDao studentDao = new StudentDao();
        //获得Date日人流表
        ArrayList<StudentDao.DailyStudent> DailyList = new ArrayList<StudentDao.DailyStudent>();

        //获取当时开门时间
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time=dateTime.format(formatter);

        DailyList = studentDao.GetDaily(tab_name, time.substring(0,10));
        int[][] num=new int[24][2];//每小时进入/c出门人数
        //num[][0]进 num[][1]出
        for(int i=0;i<24;++i){
            for(int j=0;j<2;++j) {
                num[i][j] = 0;
            }
        }
        int in=0;int out=0;
        for(int i=0;i<DailyList.size();++i){
            if(DailyList.get(i).getFlag().equals(0)) {
                switch (DailyList.get(i).getTime().substring(11, 13)) {
                    case "00":num[0][0]++;break;
                    case "01":num[1][0]++;break;
                    case "02":num[2][0]++;break;
                    case "03":num[3][0]++;break;
                    case "04":num[4][0]++;break;
                    case "05":num[5][0]++;break;
                    case "06":num[6][0]++;break;
                    case "07":num[7][0]++;break;
                    case "08":num[8][0]++;break;
                    case "09":num[9][0]++;break;
                    case "10":num[10][0]++;break;
                    case "11":num[11][0]++;break;
                    case "12":num[12][0]++;break;
                    case "13":num[13][0]++;break;
                    case "14":num[14][0]++;break;
                    case "15":num[15][0]++;break;
                    case "16":num[16][0]++;break;
                    case "17":num[17][0]++;break;
                    case "18":num[18][0]++;break;
                    case "19":num[19][0]++;break;
                    case "20":num[20][0]++;break;
                    case "21":num[21][0]++;break;
                    case "22":num[22][0]++;break;
                    case "23":num[23][0]++;break;
                }
            }
            else {
                switch (DailyList.get(i).getTime().substring(11, 13)) {
                    case "00":
                        num[0][1]++;
                        break;
                    case "01":
                        num[1][1]++;
                        break;
                    case "02":
                        num[2][1]++;
                        break;
                    case "03":
                        num[3][1]++;
                        break;
                    case "04":
                        num[4][1]++;
                        break;
                    case "05":
                        num[5][1]++;
                        break;
                    case "06":
                        num[6][1]++;
                        break;
                    case "07":
                        num[7][1]++;
                        break;
                    case "08":
                        num[8][1]++;
                        break;
                    case "09":
                        num[9][1]++;
                        break;
                    case "10":
                        num[10][1]++;
                        break;
                    case "11":
                        num[11][1]++;
                        break;
                    case "12":
                        num[12][1]++;
                        break;
                    case "13":
                        num[13][1]++;
                        break;
                    case "14":
                        num[14][1]++;
                        break;
                    case "15":
                        num[15][1]++;
                        break;
                    case "16":
                        num[16][1]++;
                        break;
                    case "17":
                        num[17][1]++;
                        break;
                    case "18":
                        num[18][1]++;
                        break;
                    case "19":
                        num[19][1]++;
                        break;
                    case "20":
                        num[20][1]++;
                        break;
                    case "21":
                        num[21][1]++;
                        break;
                    case "22":
                        num[22][1]++;
                        break;
                    case "23":
                        num[23][1]++;
                        break;
                }
            }
        }
        return num;
    }

    //获取当年的每个月Building楼的人流数
    public int[][] EachMonthBuilding(String Building){
        //转换Building to Building_Daily
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab_name=buildingToBuilding_daily.transform(Building);

        StudentDao studentDao = new StudentDao();
        //获得Date日人流表
        ArrayList<StudentDao.DailyStudent> DailyList = new ArrayList<StudentDao.DailyStudent>();

        //获取当时开门时间
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time=dateTime.format(formatter);

        String year=time.substring(0,4);//本年
        String month=new String();//月份

        int[][]num=new int[12][2];//num[][0]进 num[][1]出
        for(int i=0;i<12;++i){
            for(int j=0;j<2;++j){
                num[i][j]=0;
            }
        }

        for (int i=0;i<Integer.parseInt(time.substring(5,7));++i) {
            month=String.valueOf(i+1);
            DailyList = studentDao.GetMonth(tab_name, year, month);
            for(int j=0;j<DailyList.size();++j){
                if(DailyList.get(j).getFlag().equals(0)){
                    num[i][0]++;
                }
                else
                    num[i][1]++;
            }
        }
        return num;

    }


}
