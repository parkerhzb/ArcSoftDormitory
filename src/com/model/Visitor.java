package com.model;

public class Visitor {
    private String Id;//学号或者身份证
    private String Name;
    private byte[] Face;
    private String Building;//造访楼号
    private String Intention;//来访意图
    private String Main_id;//邀请者的学号或宿管号

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public byte[] getFace() {
        return Face;
    }

    public String getBuilding() {
        return Building;
    }

    public String getIntention() {
        return Intention;
    }

    public String getMain_id() {
        return Main_id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFace(byte[] RFace) {
        this.Face = RFace;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public void setIntention(String intention) {
        Intention = intention;
    }
    public void setMain_id(String main_id){
        Main_id=main_id;

    }
    public Visitor()
    {
        super();
    }
    public Visitor(String id,String name,byte[]face,String building,String intention,String main_id){
        Id=id;
        Name=name;
        Face=face;
        Building=building;
        Intention=intention;
        Main_id=main_id;
    }
}
