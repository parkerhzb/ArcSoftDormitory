package com.control;

import com.dao.CustodianDao;
import com.dao.StudentDao;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
根据传入的Buling(西13）返回该楼学生信息（除人脸信息）
 */
@WebServlet("/SelStudent.do")
public class SelStudentServlet extends HttpServlet {
    public SelStudentServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String Building=(String)request.getSession().getAttribute("Building");
        String Identity=(String)request.getSession().getAttribute("Identity");

        StudentDao studentDao=new StudentDao();
        ArrayList<Student>Students=new ArrayList<>();

        response.setCharacterEncoding("utf-8");
        Students = studentDao.Sel_Stu(Building);
        request.getSession().setAttribute("Students",Students);
        response.sendRedirect("pages/forms2/student_select.jsp");
    }
}
