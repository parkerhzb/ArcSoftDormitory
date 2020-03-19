/*
connetClass 类中包含了一些功能如下：
1-与数据库建立连接
2-向数据库中插入组元
3-在数据库中删除组元
4-在数据库中查找组元
--byJzl
 */
package com.dao;

import com.model.Student;

import  java.util.*;
import java.io.InputStream ;
import java.sql.*;

public class ConnectionDao {
    Connection conn;
    Statement st ;


    public int connect() {  // return 1 ：连接成功         return  0  ：   连接失败
        String data1 = "jdbc:sqlserver://localhost:1433;DatabaseName=HR_Mis ";
        String driverName1 = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;

        try
        {
            conn = DriverManager.getConnection(data1,"sa","111111");
            st = conn.createStatement()  ;
            Class.forName(driverName1);
        }            //错误提示。。
        catch (SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
            return 0;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return 0;
        }
        return 1;
    }



    public int disConnect(){
        try{st.close();}
        catch (SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
            return 0;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return 0;
        }
        return 1;
    }

/***

    public int insert(String buildingName,String Id,String Name,String RFace,String Room){
        try{
            ResultSet rec = st.executeQuery(
                    "insert into "+buildingName+
                            " values( '"+Id+"','"+Name+"',0x"+RFace+",'"+Room+"') "
            );
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return 0;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return 0;}
        return 1;
    }
***//*
    public int addBuilding(String buildingName){
        try{
            ResultSet rec = st.executeQuery(
                    "create table "+buildingName+

                            " (  "+
                            "Id char(20) primary key,"+
                            "Name char(20),"+
                            "RFace binary(1032),"+
                            "Rome char(20)"+
                            " )"
            );
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}return 0;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return 0;}
        return 1;
    }




    public int delBuilding(String buildingName){
        try{
            ResultSet rec = st.executeQuery(
                    "drop table "+buildingName
            );
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}return 0;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return 0;}
        return 1;
    }



    public Student searchStu(String buildingName , String Id){
        Student student =new Student();
        try{
            ResultSet rec = st.executeQuery(
                    "select  * "+
                            " from  "  +buildingName     +
                            " where Id = "+ Id
            );
            while(rec.next()){
                student.setId(rec.getString(1));
                student.setName(rec.getString(2));
                student.setRoom(rec.getString(4));
            }
        }catch(SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());}
        return student;
    }
*********/

    public String byteToStringForDB(byte[] byt){
        String str ="";
        int byteSize = byt.length;
        for(int i=0;i<byteSize;i++){

            String temp = Integer.toHexString(byt[i]);
            int size = temp.length();

            if(size==1)str = str+"0"+Integer.toHexString(byt[i]);
            if(size==2)str=str+Integer.toHexString(byt[i]);
            if(size>2){
                String str_tmp = Integer.toHexString(byt[i]);
                str=str+str_tmp.substring(str_tmp.length()-2, str_tmp.length());}
        }
        return str;
    }



    public ArrayList<StudentDao.id_fa> getFaceFeatureList(String buildingName , int columnNum ){

        ArrayList <StudentDao.id_fa>faceFeatureList =new  ArrayList<>() ;
        //Byte[] aFaceFeature = new Byte[1032];
        try{
            ResultSet rec = st.executeQuery(    "select * "+" from  "  +buildingName  );
            while(rec.next()){
                StudentDao.id_fa SS= new StudentDao().new id_fa();
                InputStream input = rec.getBinaryStream("RFace");
                byte aFaceFeature[] = new byte[1032];
                input.read(aFaceFeature);
                SS.fa=aFaceFeature;
                SS.id=rec.getString("Id");
                faceFeatureList.add(SS);
                //System.out.print(rec.getString(columnNum));
                //System.out.println();
                //System.out.println("内容为：" + aFaceFeature[0]) ;
            }

        }catch(SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());}

        return faceFeatureList;
    }

/*
    public void showList(String buildingName){
        try{
            ResultSet rec = st.executeQuery(
                    "select * "+
                            "from  "  +buildingName
            );
            while(rec.next()){
                System.out.print(rec.getString(1)+" \t");
                System.out.print(rec.getString(2)+" \t");
                System.out.print(rec.getString(3)+" \t");
                System.out.print(rec.getString(4)+" \t");
                System.out.println();
            }
        }
        catch(SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());}
    }//debug辅助功能
*****/

    public ConnectionDao(){connect();
    }



    public boolean addIOTable(String buildingName){
        try{
            int rec = st.executeUpdate("create table "+buildingName+ " (  "+ "Id varchar(20),"+ "Name varchar(20),"+
                    "time datetime,flag varchar(20),tel varchar(20),Room varchar(20), primary key(Id,Time) )");
            return true ;
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}return false;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return false;}

    }
    // #########################################################################################
    public int delIOTable(String buildingName){
        try{
            ResultSet rec = st.executeQuery(
                    "drop table "+buildingName
            );
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}return 0;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return 0;}
        return 1;
    }

}
