package com.dao;

import com.control.BuildingToBuilding_Daily;
import com.model.Custodian;
import com.model.Visitor;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustodianDao extends ConnectionDao {
    /*
    添加学生信息
     */
    public boolean AddStu(String Id,String Name,String Gender,byte[] Face,String Tel,String Room){
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String tab=buildingToBuilding_daily.Room_transform(Room);//分表名称 例如：BuildingW13
        String face = byteToStringForDB(Face);
        String sql = "insert into student values ( '"+Id+"' , '"+Name+"' , '"+Gender+"' , 0x"+face+" , '"+Tel+"' , '"+Room+"' )"
                +"\n"
                +"insert into "+tab + " values ( '"+Id+"' , '"+Name+"' , 0x"+face+" , '"+Room+"' )";
        try{
            //System.out.println(sql);
            //insert stu的信息
            int rec = st.executeUpdate(sql);
            if(rec>0)
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
    删除学生信息
     */
    public boolean DelStu(String Id){
        BuildingToBuilding_Daily buildingToBuilding_daily=new BuildingToBuilding_Daily();
        String sql1 = "select * from student where Id = '"+Id+"'";
        String room ;
        String tab=new String();
        String sql3 = "delete from student  where Id = '"+Id+"'";
        try{
            ResultSet rec = st.executeQuery(sql1);
            while(rec.next()){
                room = rec.getString("Room");
                room=room.trim();
                tab=buildingToBuilding_daily.Room_transform(room);
            }
            String sql2 = "delete from "+tab+"  where Id = '"+Id+"'";
            int i=st.executeUpdate(sql2);
            //rec=st.executeQuery(sql2);
            int j=st.executeUpdate(sql3);
            if(i>0&&j>0)
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

    /*
    根据识别成功的特征值返回该学生的学号Id,姓名Name，寝室号Room
     */
    public class Stu{
        String Id;
        String Name;
        String Tel;
        String Room;
        public String getName(){
            return Name;
        }
        public String getTel(){
            return Tel;
        }
        public String getRoom(){
            return Room;
        }
    }
    public Stu ReturnStu(String tab_name,byte[] Feature){
        Stu stu=new Stu();
        //操作--》根据特征值返回该学生的学号Id,姓名Name，手机号Tel,寝室号Room
        String feature = byteToStringForDB(Feature);
        String id = "";
        String sql1 = "select Id  from "+tab_name+" where Face = "+feature ;

        try{
            ResultSet rec = st.executeQuery(sql1);
            while(rec.next()){
                id = rec.getString(1);
            }
            String sql2 = "select * from student  where Id = '"+id+"' " ;
            rec = st.executeQuery(sql2);
            while(rec.next()){
                stu.Id = rec.getString(1);
                stu.Name = rec.getString(2);
                stu.Room = rec.getString(6);
                stu.Tel = rec.getString(5) ;
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );
            }
            stu=null;
            return stu;
        }catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            stu=null;
            return stu;
        }
        return stu;
    }

    /*
    根据识别成功的特征值记录进出人信息
     */
    /////】/】/】/】/】/】/】/】/】/】/】/】/】】】/】/】/】/】/】/】/】///】【/【//】/【/】/【】/【/】/【/】/【/】/【】/【】/】/】/】/、/、/、//、
    public boolean CreateIO(String tab_name,String Id,String Name,String time,String Flag,String Tel,String Room) {
        //select 特征值为传入参数特征值
        //在每日表新增记录
        String sql2 = " insert into  "+tab_name+" values ('"+Id+"' , '"+Name+"' , '"+time+"' , '"+Flag+"' , '"+Tel+"' , '"+Room+"' )";
        try {
            int rec = st.executeUpdate(sql2);
            if(rec>0)
                return true;
            else
                return false;

        }
        catch (SQLException s) {
            if (s.getErrorCode() != 0) {
                System.out.println("SQL Error : " + s.toString());
                System.out.println("SQL Error : " + s.getErrorCode());
                System.out.println("SQL Error : " + s.getSQLState());
            }
            return false;
        }
        catch (Exception e) {
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }


    }

    /*
    跟据宿管Id返回宿管信息
     */
    public Custodian SelectCus(String Id){
        Custodian custodian=new Custodian();
        //查询返回操作
        String sql = "select * from Custodian where Id = "+Id ;
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                custodian.setId(rec.getString(1));
                custodian.setName(rec.getString(2)) ;
                custodian.setGender(rec.getString(3)) ;
                custodian.setBuilding(rec.getString(5)) ;
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return custodian;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return custodian;
        }
        return custodian;
    }

    /*
    根据宿管Id修改宿管管理的楼号
    参数：工号Id,新楼号Building
     */
    public boolean ModifyCus(String Id,String Building){
        String sql = "update Custodian set Building = '"+Building
                +"' where Id = "+Id;
        try{
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

    //宿管登录
    public boolean Login(String Id,String Key,String Identity){
        String sql = "select * from CustodianKey where Id = "+Id+" and ltype = "+Identity;
        String key = "";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                key = rec.getString("Ky");
            }
            key=key.trim();
            if(Key.equals(key))return true ;
            if(!Key.equals(key))return false ;
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return false;}
        return false;

    }

    //修改宿管的登陆密码
    public boolean  ModifyCusKey(String Id,String Key){
        String sql = "update CustodianKey set Ky = "+Key
                +" where Id = "+Id;
        try{
            int rec;
            rec= st.executeUpdate( sql );
            if(rec!=0)
                return true;
            else
                return false ;
        }catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return false;
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());return false;}

    }

    /*
        返回所有宿管信息（除了人脸信息）
         */
    public ArrayList<Custodian> Sel_AllCus(){
        ArrayList<Custodian> List=new ArrayList<>();
        String sql = "select * from Custodian ";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                Custodian custodian = new Custodian();
                custodian.setId(rec.getString("Id"));
                custodian.setName(rec.getString("Name"));
                custodian.setGender(rec.getString("Gender"));
                custodian.setBuilding(rec.getString("Building"));
                List.add(custodian);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return List;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return List;
        }
        return List;
    }

    /*
    初次添加访客
    输入参数：
    String Id;//访客的学号或者身份证
    String Name;
    String Building;//造访楼号
    String Intention;//来访意图
    String Main_id;//邀请者的学号或宿管号
     */
    public boolean AddVisitor1(String Id,String Name,String Building,String Intention,String Main_id){
        String sql = " insert into visitor "+" values( '"+Id+"' , '"+Name
                + "', null, '"+Building+"','"+Intention+"','"+Main_id+"')";

        try{
            int i = st.executeUpdate(sql);
            if(i>0)
                return true;
            else
                return false;
        }
        catch(SQLException s) {
            if (s.getErrorCode() != 0) {
                System.out.println("SQL Error : " + s.toString());
                System.out.println("SQL Error : " + s.getErrorCode());
                System.out.println("SQL Error : " + s.getSQLState());
            }
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }
    }

    /*
    扫码添加脸信息
    输入参数：
    Id
     byte[]Face
     */
    public boolean AddVisitor2(String Id,byte[]Face){
        String faceFeature = "";
        if(Face!=null)faceFeature = byteToStringForDB(Face);
        String sql="update Visitor set RFace=0x"+faceFeature+" where Id = '"+Id+"'";
        try{
            int i = st.executeUpdate(sql);
            if(i>0)
                return true;
            else
                return false;
        }
        catch(SQLException s) {
            if (s.getErrorCode() != 0) {
                System.out.println("SQL Error : " + s.toString());
                System.out.println("SQL Error : " + s.getErrorCode());
                System.out.println("SQL Error : " + s.getSQLState());
            }
            return false;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return false;
        }
    }

/*
返回访客的姓名 人脸特征值
 */
    public ArrayList<Visitor> GetVisFace(){
        ArrayList<Visitor>List=new ArrayList<>();
        try{
            ResultSet rec = st.executeQuery(    "select *  from  visitor"    );
            while(rec.next()){
                InputStream input = rec.getBinaryStream("RFace");
                if(input==null)
                    continue;
                byte aFaceFeature[] = new byte[1032];
                input.read(aFaceFeature);
                Visitor visitor = new Visitor();
                visitor.setFace(aFaceFeature);
                visitor.setBuilding(rec.getString("Building"));
                visitor.setName(rec.getString("Name"));
                visitor.setId(rec.getString("Id"));
                List.add(visitor);

            }

        }catch(SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
        }catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
        }

        return List;
    }

    /*根据ID获取访客name*/
    public String GetvisName(String Id){
        String name="";
        String sql="select * from visitor where Id = '"+Id +"'";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                name =  rec.getString("Name");
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return name;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return name;
        }
        return name;

    }
}
