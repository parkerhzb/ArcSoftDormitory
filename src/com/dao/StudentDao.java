package com.dao;

import com.control.BuildingToBuilding_Daily;
import com.model.Student;

import javax.persistence.Id;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao extends ConnectionDao{
    /*
    根据楼号返回特征值
     */
    public class id_fa{
        String id;
        byte []fa;
        public String getId(){
            return id;
        }

        public byte[] getFa() {
            return fa;
        }
    }
    public ArrayList<id_fa>GetFeature(String Building){
        ArrayList<id_fa> FeatureList=new ArrayList<id_fa>();
        //查找操作
        FeatureList = getFaceFeatureList(Building,3);
        return FeatureList;
    }

    /*
    根据楼号Building与日期Data返回寝室楼每日人流表
     */
    //每日人流表信息类
    public class DailyStudent{
        String Id;
        String Name;
        String Time;//当日进出时间
        String Flag;//标记进还是出
        String Tel;
        String Room;

        public String getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }

        public String getTime() {
            return Time;
        }
        public String getFlag(){
            return Flag;
        }

        public String getTel() {
            return Tel;
        }

        public String getRoom() {
            return Room;
        }
    }
    public ArrayList<DailyStudent>GetDaily(String Building,String Data){
        ArrayList<DailyStudent> DailyList=new ArrayList<DailyStudent>();
        String sql = "select * from "+Building  +" where Time >= '"+Data+" 00:00:00' and Time <= '" +Data +" 23:59:59'";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                DailyStudent student = new DailyStudent() ;
                student.Id = rec.getString("Id");
                student.Name = rec.getString("Name");
                student.Time = rec.getString("Time");
                student.Flag = rec.getString("Flag");
                student.Tel = rec.getString("Tel");
                student.Room = rec.getString("Room");
                DailyList.add(student);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return DailyList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return DailyList;
        }
        return DailyList;
    }

    public ArrayList<DailyStudent>GetMonth(String Building,String year,String month){
        ArrayList<DailyStudent> DailyList=new ArrayList<DailyStudent>();
        String sql = "select * from "+Building  +" where DATENAME (yyyy,Time)="+year+"  and  DATENAME (mm,Time)= " +month;
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                DailyStudent student = new DailyStudent() ;
                student.Id = rec.getString("Id");
                student.Name = rec.getString("Name");
                student.Time = rec.getString("Time");
                student.Flag = rec.getString("Flag");
                student.Tel = rec.getString("Tel");
                student.Room = rec.getString("Room");
                DailyList.add(student);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return DailyList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return DailyList;
        }

        return DailyList;

    }
    /*
    根据学生Id，楼号Building查询该学生出入寝室的记录
     */
    public ArrayList<String>GetStudnetIO(String Id,String Building){
        ArrayList<String>StudentIOList=new ArrayList<String>();
        //查询操作
        //返回时间列表
        /*例如：
        2018-10-15-18：30-出
        2018-10-16-09：13-入
         */
        String sql = "select * from "+Building  +" where Id = "+Id ;
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                String str="";
                str = str+ rec.getString("Time")+"-"+rec.getString("Flag");
                StudentIOList.add(str);

            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return StudentIOList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return StudentIOList;
        }
        return StudentIOList;
    }
    /*
    根据学生Id，楼号Building，与限定时间Data1-Data2时间内查询该学生出入寝室的记录
     */
    public ArrayList<String>GetStuTimeIO(String Id,String Building,String Data1,String Data2){
        ArrayList<String>StuTimeIOList=new ArrayList<String>();
        String sql = "select * from "+Building  +" where Id = "+Id +" and "+"Data >"+Data1+" and "+"Data< " +Data2;
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                String tmp="";
                if(rec.getString(4).equals("1"))tmp+=" 出 ";
                if(rec.getString(4).equals("0"))tmp+=" 进 ";
                String str= rec.getString(3)+tmp;
                StuTimeIOList.add(str);
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return StuTimeIOList;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return StuTimeIOList;
        }
        return StuTimeIOList;
    }

//    /*
//    根据学生Id,寝室Room，查询该学生是否存在该寝室里，若存在返回true 否则返回false
//    */
//    public boolean ExitStu(String Id,String Room){
//        if(/*存在*/)
//            return true;
//        else
//            return false;
//    }

    /*
    跟据学生Id返回学生信息
     */
    public Student SelectStu(String Id){
        String sql = "select *  from student where  Id  ='"+Id+"'"  ;

        Student student=new Student();
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                student.setId(rec.getString("Id"));
                student.setName(rec.getString("Name"));
                student.setGender(rec.getString("Gender"));
                student.setTel(rec.getString("Tel"));
                student.setRoom(rec.getString("Room"));
            }
        }
        catch(SQLException s){
            if(s.getErrorCode()!=0){
                System.out.println("SQL Error : " + s.toString() );
                System.out.println("SQL Error : " + s.getErrorCode() );
                System.out.println("SQL Error : " + s.getSQLState() );}
            return null;
        }
        catch (Exception e){
            System.out.println("Error :" + e.toString() + e.getMessage());
            return null;
        }
        return student;
    }
    /*
    根据学生Id修改学生基本信息
    参数：学号Id,学生类student
     */
    public boolean ModifyStu(String tab,String Id, Student student){
        //String face = byteToStringForDB(student.getFace());
        String sql ="update student set Tel = '"+student.getTel()+"'"+",Room = '"+student.getRoom()
                +"' where Id = '"+Id+"'";
        String sql1="update "+tab+" set Room='"+student.getRoom()+"' where Id='"+Id+"'";
        try{
            //操作
            int rec = st.executeUpdate(sql);
            if(rec>0){
                st.executeUpdate(sql1);
                return true;
            }
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
    迁移学生宿舍信息
    根据Id和原来的宿舍OldRoom,将学生搬至新的宿舍楼
     */
    public boolean MoveRoom(String Id,String OldRoom,Student student){
        //String face = byteToStringForDB(student.getFace());
        BuildingToBuilding_Daily b=new BuildingToBuilding_Daily();
        String sql = " insert into "+b.Room_transform(student.getRoom().trim())+" values( '"+student.getId()+"' , '"+student.getName()
                + ", (select RFace from "+OldRoom+" where Id = "+Id+" ) ,"+student.getRoom()+"' )\n"+"delete from  "+OldRoom+" where Id = "+Id+"\n";
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



    //根据楼号返回学生所有信息（除了人脸信息）
    //在总表查找，以Building为限定条件
    public ArrayList<Student> Sel_Stu(String Building){
        ArrayList<Student>StuList=new ArrayList<>();
        //操作
        Building=Building.trim();
        try{
            ResultSet rec = st.executeQuery(
                    "select * from  student where Room LIKE '"+Building+"%'"
            );//'"+Building+"'='西13'
            while(rec.next()){
                Student student = new Student();
                student.setId(rec.getString("Id"));
                student.setName(rec.getString("Name"));
                student.setGender(rec.getString("Gender"));
                student.setTel(rec.getString("Tel"));
                student.setRoom(rec.getString("Room"));
                StuList.add(student);

            }

        }catch(SQLException s){
            System.out.println("SQL Error : " + s.toString() );
            System.out.println("SQL Error : " + s.getErrorCode() );
            System.out.println("SQL Error : " + s.getSQLState() );
        }catch (Exception e){System.out.println("Error :" + e.toString() + e.getMessage());}

        return StuList;
    }
    /*
        返回所有学生信息（除了人脸信息）
         */
    public ArrayList<Student> Sel_AllStu(){

        ArrayList<Student> List=new ArrayList<>();
        String sql = "select * from Student ";
        try{
            ResultSet rec = st.executeQuery(sql);
            while(rec.next()){
                Student student = new Student();
                student.setId(rec.getString("Id"));
                student.setName(rec.getString("Name"));
                student.setGender(rec.getString("Gender"));
                student.setTel(rec.getString("Tel"));
                student.setRoom(rec.getString("Room"));
                List.add(student);
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
}
