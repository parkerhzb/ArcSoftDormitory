package com.dao;


import java.sql.SQLException;
import java.sql.ResultSet;

public class AdminDao extends ConnectionDao {
    /*
    添加宿管信息
     */
    public boolean AddCustodian(String Id,String Name,String Gender,byte[] Face,String Building){
        String face = "0";
        if(Face!=null)
            face = byteToStringForDB(Face);
        String sql = "insert into Custodian values ( '"+Id+"' , '"+Name+"' , '"+Gender+"' , 0x"+face+" , '"+Building+"' )";
        try{
            int rec = st.executeUpdate(sql);
            if(rec==0)
                return false;
        }catch(SQLException s){
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

        return true;
    }

    /*删除宿管信息*/
    public boolean DelCus(String Id){

        String sql = "delete Custodian where Id = "+Id;
        try{
            //删除操作
            int i = st.executeUpdate(sql);
            if (i>0)
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
}
