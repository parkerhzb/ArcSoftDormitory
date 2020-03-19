package com.control;

public class BuildingToBuilding_Daily {
    public BuildingToBuilding_Daily(){
        super();
    }
    //西13-》BuildingW13Daily
    public String transform(String Building){
        String Building_Daily="Building";
        if(Building.substring(0,1).equals("西"))
            Building_Daily+="W";
        else
            Building_Daily+="E";
        Building_Daily+=Building.substring(1);
        Building_Daily+="Daily";
        return Building_Daily;
    }
    //西13-》BuildingW13
    public String B_transform(String Building){
        String Building_Daily="Building";
        if(Building.substring(0,1).equals("西"))
            Building_Daily+="W";
        else
            Building_Daily+="E";
        Building_Daily+=Building.substring(1);
        return Building_Daily;
    }
    //西13#206--》BuildingW13
    public String Room_transform(String Room){
        String Room_Building="Building";
        if(Room.substring(0,1).equals("西"))
            Room_Building+="W";
        else
            Room_Building+="E";
        if(Room.length()==7)
            Room_Building+=Room.substring(1,3);
        else
            Room_Building+=Room.substring(1,2);
        return Room_Building;
    }

}
