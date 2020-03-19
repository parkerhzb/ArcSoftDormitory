package com.control;

import com.dao.CustodianDao;
import com.model.Custodian;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*改变宿管管理的楼号
Id必填
 */
@WebServlet("/ModifyCustodian.do")
public class ModifyCustodianServlet extends HttpServlet {
    public ModifyCustodianServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Id=request.getParameter("Id");
        String Building = request.getParameter("Building");
        CustodianDao custodianDao=new CustodianDao();
        if(custodianDao.ModifyCus(Id, Building)){
            request.getSession().setAttribute("msg","宿管信息更新成功");//成功返回1
        }
        else {
            String mess="该宿管信息不存在";
            request.getSession().setAttribute("msg",mess);

        }
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/custodian_update.jsp");
    }
}
