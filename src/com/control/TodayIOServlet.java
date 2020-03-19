package com.control;

import com.dao.StudentDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/*
* 返回当天的学生进出信息
* 参数楼号Building从会话中提取
*/
@WebServlet("/TodayIO.do")
public class TodayIOServlet extends HttpServlet {
    public TodayIOServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Identity=(String)request.getSession().getAttribute("Identity");
        String Building=(String)request.getSession().getAttribute("Building");
        String sel=request.getParameter("sel");
        if(sel==null)
            sel="0";
        BuildingIO buildingIO=new BuildingIO();
        ArrayList<StudentDao.DailyStudent> List=new ArrayList<>();
        if(Identity.equals("1")) {
            List = buildingIO.DailyBuilding(Building);
            if(sel.equals("1")){
                try {
                    ArrayList<StudentDao.DailyStudent> dailyStudents = new ArrayList<StudentDao.DailyStudent>();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String today = df.format(new Date());
                    today = today.substring(0, 11);
                    today = today + "23:00:00";
                    Date time = df.parse(today);
                    for (StudentDao.DailyStudent dailyStudent : List) {
                        String IOtimeStr = dailyStudent.getTime();

                        Date IOtime = df.parse(IOtimeStr);
                        if (IOtime.after(time)) {
                            dailyStudents.add(dailyStudent);
                        }

                    }
                    List = dailyStudents;
                }catch (Exception e){e.printStackTrace();}
            }
        }
        else {
            //获取当时开门时间
            LocalDateTime dateTime=LocalDateTime.now();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time=dateTime.format(formatter);

            String year=time.substring(0,4);//本年
            String month=time.substring(5,7);//月份
            StudentDao studentDao=new StudentDao();
            String Build1="西";
            String Build2="东";
            String bu;
            BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
            for(int i=1;i<=15;++i){
                bu=Build1+Integer.toString(i);
                String tab=buildingToBuilding_daily.transform(bu);
                //List.addAll(buildingIO.DailyBuilding(bu));
                List.addAll(studentDao.GetMonth(tab,year,month));
            }
            for(int i=1;i<=15;++i){
                bu=Build2+Integer.toString(i);
                String tab=buildingToBuilding_daily.transform(bu);
                //List.addAll(buildingIO.DailyBuilding(bu));
                List.addAll(studentDao.GetMonth(tab,year,month));
            }
        }
        request.getSession().setAttribute("List",List);
        request.getSession().setAttribute("sel",sel);
        request.getSession().setAttribute("path","TodayIO.do");
        response.setCharacterEncoding("utf-8");
        if(Identity.equals("1"))
            response.sendRedirect("pages/forms2/student_flow.jsp");
        else
            response.sendRedirect("pages/forms/student_flow.jsp");
    }
}
