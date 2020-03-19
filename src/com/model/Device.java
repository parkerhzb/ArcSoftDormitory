package com.model;

public class Device {
    private String Id;//设备号
    private String Building;//楼号
    private String Flag;//进/出门摄像头标志 Flag=0进  Flag=1出
    private String Status;//状态 1工作  2损坏

    public String getId(){
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getFlag(){
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public Device(){
        super();
    }
    public Device(String Id,String Building,String Flag,String status)
    {
        super();
        this.Id=Id;
        this.Building=Building;
        this.Flag=Flag;
        this.Status=status;
    }
}

