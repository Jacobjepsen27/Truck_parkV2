package jepsen.dk.truck_park;

import android.app.Application;

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

   }
}
