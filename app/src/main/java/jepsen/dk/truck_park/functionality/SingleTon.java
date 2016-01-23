package jepsen.dk.truck_park.functionality;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Jacobs on 23-01-2016.
 */
public class SingleTon extends Application{
    private static SingleTon ourInstance = new SingleTon();

    public static SingleTon getInstance() {
        return ourInstance;
    }

   public void onCreate(){
       super.onCreate();
       Parse.initialize(this);
       loadSpots();
   }

    private void loadSpots(){
        ParseObject testObject = new ParseObject("TestObject");

    }
}
