package com.control;

import com.dao.CustodianDao;
import com.dao.DeviceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifyDev.do")
public class ModifyDeviceServlet extends HttpServlet {
    public ModifyDeviceServlet(){
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
        String Building=request.getParameter("Building");
        String Flag=request.getParameter("Flag");
        CustodianDao custodianDao=new CustodianDao();
        DeviceDao deviceDao=new DeviceDao();
        if(deviceDao.ModifyDev(Id,Building,Flag))
        {
            request.getSession().setAttribute("msg","设备信息修改成功！");
            response.sendRedirect("pages/forms/device_update.jsp");

        }
        else {
            request.getSession().setAttribute("msg","设备信息修改失败！");
            response.sendRedirect("pages/forms/device_update.jsp");
        }

    }
}
