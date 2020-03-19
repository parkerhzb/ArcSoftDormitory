package com.control;

import com.dao.DeviceDao;
import com.model.Device;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//import java.text.SimpleDateFormat;
//import java.util.Date;

/*
查询所有设备信息
 */
@WebServlet("/AllDev.do")
public class AllDevServlet extends HttpServlet{
    public AllDevServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DeviceDao deviceDao=new DeviceDao();
        ArrayList<Device>DeviceList=new ArrayList<Device>();
        ArrayList<Device>DeviceList1=new ArrayList<Device>();
        //////判断权限
        String Identity=(String)request.getSession().getAttribute("Identity");
        String power=(String)request.getSession().getAttribute("Building");
        if(Identity.equals("1")){
            BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
            //String tab=buildingToBuilding_daily.B_transform(power);
            DeviceList1=deviceDao.GetBuildDev(power);
            request.getSession().setAttribute("DeviceList",DeviceList1);
            response.setCharacterEncoding("utf-8");
            response.sendRedirect("pages/forms2/checkDevice.jsp");
            return;
        }

        DeviceList=deviceDao.GetDevice();
        request.getSession().setAttribute("DeviceList",DeviceList);
        response.setCharacterEncoding("utf-8");
        response.sendRedirect("pages/forms/checkDevice.jsp");
    }

}
