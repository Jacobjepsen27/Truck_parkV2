package jepsen.dk.truck_park.functionality;

import java.io.Serializable;

/**
 * Created by Jacobs on 24-01-2016.
 */
public class Spot implements Serializable{

    private String desc, lat, lng;
    private Boolean shower, food, gas, roadTrain;

    public Spot(String desc, String lat, String lng, boolean shower, boolean food, boolean gas, boolean roadTrain){
        this.desc=desc;
        this.lat=lat;
        this.lng=lng;
        this.shower=shower;
        this.food=food;
        this.gas=gas;
        this.roadTrain=roadTrain;
    }

    public String getDesc(){ return desc;}
    public String getLat(){ return lat;}
    public String getLng(){ return lng;}

}
