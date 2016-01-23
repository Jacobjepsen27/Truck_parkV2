package jepsen.dk.truck_park;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.gmap);
        btn.setOnClickListener(this);
    }
//test commit
    //Andet test commit

    @Override
    public void onClick(View v) {
        if(v==btn){
            Intent gm = new Intent(this, MapsActivity.class);
            startActivity(gm);
        }
    }
}
