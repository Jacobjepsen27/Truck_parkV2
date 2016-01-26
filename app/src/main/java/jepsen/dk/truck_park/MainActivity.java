package jepsen.dk.truck_park;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jepsen.dk.truck_park.functionality.SingleTon;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.gmap);
        btn.setOnClickListener(this);

//            @Override
//            public void onClick(View v) {
//                ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                if (cm.getActiveNetworkInfo() == null) {
//                    Log.d("Error", "Ingen internetforbindelse på søge tryk.");
//                    Toast.makeText(getBaseContext(), "You have no internet connection!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                progressDialog = ProgressDialog.show(getBaseContext(), "Building Map", "Finding spots...", true);
//                Intent gm = new Intent(this, MapsActivity.class);
//                startActivity(gm);
//                new AsyncTask<Void, Void, String>() {
//                    @Override
//                    protected String doInBackground(Void... params) {
//                        for(int i=0; i<SingleTon.spotList.size(); i++){
////                            SingleTon.latlngList.add(SingleTon.spotList.get(i).);
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(String msg){
//                        Intent gm = new Intent(getBaseContext(), MapsActivity.class);
//                        startActivity(gm);
//                    }
//                }.execute();
            }
//        })
//test commit
    //Andet test commit

    @Override
    public void onClick(View v) {
        if(v==btn){
            ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm.getActiveNetworkInfo() == null) {
                    Log.d("Error", "Ingen internetforbindelse på søge tryk.");
                    Toast.makeText(getBaseContext(), "You have no internet connection!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent gm = new Intent(this, MapsActivity.class);
                startActivity(gm);
        }
    }
}
