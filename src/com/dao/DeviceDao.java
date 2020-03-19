package com.dao;

import com.model.Device;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceDao extends ConnectionDao{
    /*
    查询，根据id返回Device，不存在返回null
     */
    public Device SelDevice(String Id){
        Device device=new Device();
        String sql = "select * from device where Id ='"+Id+"'" ;
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                device.setId(rec.getString(1));
                device.setBuilding(rec.getString(2));
                device.setStatus(rec.getString(4));
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return device;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return device;
        }
        return device;
    }
    /*
    添加设备信息
     */
    public boolean AddDevice(String Id,String Building,String Flag,String Status){
        String sql = "insert into device values ( '"+ Id+"' , '"+Building+"' , '"+Flag+"','"+Status+"' )";
        try{
            int rec = st.executeUpdate(sql);
            if(rec==0)
                return false;
            return true;
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }

    }

    /*
    删除设备信息
     */
    public boolean DelDev(String Id){
        //delete * from tab where Id
        String sql =  "delete from device where Id ='"+Id+"'" ;
        try{
            int i = st.executeUpdate(sql);
            if(i>0)
                return true;
            else
                return false;
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }
    }

    /*
    查询设备所有信息，返回list
     */
    public ArrayList<Device>GetDevice(){
        ArrayList<Device>DeviceList=new ArrayList<Device>();
        String sql = "select * from device ";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                Device device = new Device();
                device.setId(rec.getString(1));
                device.setBuilding(rec.getString(2));
                device.setFlag(rec.getString(3));
                device.setStatus(rec.getString(4));
                DeviceList.add(device);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return DeviceList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return DeviceList;
        }
        return DeviceList;
    }

    /*
    根据楼号西13返回该楼设备信息
     */
    public ArrayList<Device>GetBuildDev(String power){
        ArrayList<Device>DeviceList=new ArrayList<>();
        String sql = "select * from Device where Building='"+power+"'";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                Device device=new Device();
                device.setId(rec.getString(1));
                device.setBuilding(rec.getString(2));
                device.setFlag(rec.getString(3));
                device.setStatus(rec.getString(4));
                DeviceList.add(device);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return DeviceList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return DeviceList;
        }
        return DeviceList;
    }

    /*
    报修设备
    参数：设备号id，新状态status
     */
    public boolean Repair(String Id,String Status){
        //updata 数据库中设备状态信息，设置为报修状态
        String sql = "update device set status = '"+Status+"' where Id = '"+Id+"'";
        try{
            //操作
            int rec = st.executeUpdate(sql);
            if(rec!=0)
                return true;
            else
                return false ;
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }

    }

    /*
    根据ID修改设备的Building，Flag
     */
    public boolean ModifyDev(String Id,String Building,String Flag){
        String sql = "update device set Id = '"+Id+"',Building='"+Building+"' where Id = '"+Id+"'";
        try{
            //操作
            int n = st.executeUpdate(sql);
            if (n>0)
                return true;
            return false;
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }
    }

}
