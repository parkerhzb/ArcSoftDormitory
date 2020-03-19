package com.model;

public class Student {
    private String Id;// 学号
    private String Name;// 姓名
    private String Gender;// 性别
    private byte[] Face;// 人脸信息
    private String Tel;//联系电话
    private String Room;//宿舍，格式:西13#206

    public String getId(){
        return Id;
    }

    public void setId(String Id){
        this.Id=Id;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name=Name;
    }

    public String getGender(){
        return Gender;
    }

    public void setGender(String Gender){
        this.Gender=Gender;
    }

    public byte[] getFace() {
        return Face;
    }

    public void setFace(byte[] Face) {
        this.Face = Face;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public Student(){
        super();
    }

    public Student(String Id,String Name,String Gender,byte[] Face,String Tel,String Room){
        super();
        this.Id=Id;
        this.Name=Name;
        this.Gender=Gender;
        this.Face=Face;
        this.Tel=Tel;
        this.Room=Room;
    }
}
