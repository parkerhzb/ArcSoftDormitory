package com.control;

import com.dao.StudentDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import java.text.SimpleDateFormat;
//import java.util.Date;

/*
修改学生信息功能
输入学生Id，Name,Room必填
*/
@WebServlet("/ModifyStudent.do")
public class ModifyStudentServlet extends HttpServlet{
    public ModifyStudentServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String Identity=(String)request.getSession().getAttribute("Identity");
        StudentDao studentDao=new StudentDao();
        Student student = new Student();

        String Id=request.getParameter("Id");
        if(Id==null){
            request.getSession().setAttribute("msg","学号不能为空！");
            if(Identity.equals("1"))
                response.sendRedirect("pages/forms2/student_update.jsp");
            else
                response.sendRedirect("pages/forms/student_update.jsp");
            return;
        }


        String Name = request.getParameter("Name");
        //String Gender=request.getParameter("Gender");
        String Tel = request.getParameter("Tel");
        String Room = request.getParameter("Room");
        student = studentDao.SelectStu(Id);

        /////judge power that belonged to the recent custodian
        if(Identity.equals("1")){
            String power=(String)request.getSession().getAttribute("Building");
            if(student.getRoom().length()==7){
                if(!student.getRoom().substring(0,3).equals(power)){
                    request.getSession().setAttribute("msg","权限不足，只能添加本寝室楼学生信息！");
                    response.sendRedirect("pages/forms2/student_update.jsp");
                    return;
                }
            }
            else{
                if(!student.getRoom().substring(0,2).equals(power)){
                    request.getSession().setAttribute("msg","权限不足，只能添加本寝室楼学生信息！");
                    response.sendRedirect("pages/forms2/student_update.jsp");
                    return;
                }
            }
        }


        if (!Tel.equals(null))
            student.setTel(Tel);

        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab=buildingToBuilding_daily.Room_transform(Room);
        if (!Room.substring(0,Room.indexOf("#")).equals(student.getRoom().substring(0,Room.indexOf("#")).trim())) {
            String OldRoom = student.getRoom();//记录原宿舍
            student.setRoom(Room);//更新宿舍
            if(studentDao.MoveRoom(Id, OldRoom, student)){
                studentDao.ModifyStu(tab,Id, student);
                String mess="更换宿舍成功";
                request.getSession().setAttribute("msg",mess);
            }
            else{
                String mess="该学生信息不存在";
                request.getSession().setAttribute("msg",mess);
            }
        }
        else {
            student.setRoom(Room);
            if(studentDao.ModifyStu(tab,Id, student)){
                String mess="学生信息修改成功";
                request.getSession().setAttribute("msg",mess);
            }
            else{
                String mess="该学生信息不存在";
                request.getSession().setAttribute("msg",mess);
            }
        }
        if(Identity.equals("1"))
            response.sendRedirect("pages/forms2/student_update.jsp");
        else
            response.sendRedirect("pages/forms/student_update.jsp");

    }

}
