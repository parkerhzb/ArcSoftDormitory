package com.control;

import com.dao.CustodianDao;
import com.dao.StudentDao;
import com.model.Custodian;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
    public LoginServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        String Id=request.getParameter("Id");
        String Pass=(String)request.getParameter("Password");
        String Identity=request.getParameter("Identity");//1宿管 2管理员
        if(Identity.equals("宿管"))
            Identity="1";
        else if(Identity.equals("系统管理员"))
            Identity="2";
        else {
            request.getSession().setAttribute("msg","请选择身份！");
            response.setCharacterEncoding("utf-8");
            response.sendRedirect("index.jsp");
            return;
        }

        CustodianDao custodianDao=new CustodianDao();
        boolean flag=custodianDao.Login(Id,Pass,Identity);

        response.setCharacterEncoding("utf-8");
        if(flag){
            String building=new CustodianDao().SelectCus(Id).getBuilding();
            request.getSession().setAttribute("Building",building);
            request.getSession().setAttribute("Identity",Identity);
            if(Identity.equals("1"))
                response.sendRedirect("main2.jsp");
            else
                response.sendRedirect("main.jsp");
        }
        else{
            request.getSession().setAttribute("msg","登录失败！");
            response.sendRedirect("index.jsp");
        }
    }
}
