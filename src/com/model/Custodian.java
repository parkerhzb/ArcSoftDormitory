package com.model;

public class Custodian {
    private String Id;// 职工号
    private String Name;// 姓名
    private String Gender;// 性别
    private byte[] Face;// 人脸信息
    private String Building;//管理楼号 格式：西13

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public byte[] getFace() {
        return Face;
    }

    public void setFace(byte[] face) {
        Face = face;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        this.Building = building;
    }
    public Custodian(){
        super();
    }
    public Custodian(String Id,String Name,String Gender,byte[] Face,String Building){
        super();
        this.Id=Id;
        this.Name=Name;
        this.Gender=Gender;
        this.Face=Face;
        this.Building=Building;
    }
}
