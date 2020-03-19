package com.control;

import com.dao.CustodianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifyPass.do")
public class ModifyPassServlet {
    public ModifyPassServlet(){
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
        String OldPass=request.getParameter("OldPass");
        String NewPass=request.getParameter("OldPass");
        String Identity=request.getParameter("Identity");
        CustodianDao custodianDao=new CustodianDao();
        if(custodianDao.Login(Id,OldPass,Identity)){
            if(custodianDao.ModifyCusKey(Id,NewPass))
                request.getSession().setAttribute("msg","密码修改成功");
            else
                request.getSession().setAttribute("msg","密码修改失败");
        }
        else
            request.getSession().setAttribute("msg","原密码错误");
    }
}
