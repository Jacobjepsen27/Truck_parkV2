package jepsen.dk.truck_park.functionality;

import android.app.Application;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacobs on 23-01-2016.
 */
public class SingleTon extends Application{
    private static SingleTon ourInstance = new SingleTon();
    public static ArrayList<Spot> spotList = new ArrayList<Spot>();
    public static ArrayList<Double> latList = new ArrayList<Double>();
    public static ArrayList<Double> lngList = new ArrayList<Double>();

    public static SingleTon getInstance() {
        return ourInstance;
    }

   public void onCreate(){
       super.onCreate();
       Parse.initialize(this);
       loadSpots();
   }

    private void loadSpots(){
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Spots");
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(int i=0; i<objects.size(); i++){
                        spotList.add(new Spot(
                                objects.get(i).getString("desc"),
                                objects.get(i).getString("lat"),
                                objects.get(i).getString("lng"),
                                objects.get(i).getBoolean("shower"),
                                objects.get(i).getBoolean("food"),
                                objects.get(i).getBoolean("gas"),
                                objects.get(i).getBoolean("roadtrain")
                        ));
                    }
                    System.out.println("Antal Spots Loadet fra DB: "+spotList.size());
                }
            }
        });
    }
}
