package com.control;


import com.dao.StudentDao;
import com.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
返回所有学生信息
 */
@WebServlet("/AllStu.do")
public class AllStuServlet extends HttpServlet{
    public AllStuServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        StudentDao studentDao=new StudentDao();
        ArrayList<Student> allStudents=studentDao.Sel_AllStu();
        request.getSession().setAttribute("allStudents",allStudents);
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/student_select.jsp");
    }
}
