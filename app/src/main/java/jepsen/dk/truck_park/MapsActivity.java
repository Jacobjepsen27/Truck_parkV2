package jepsen.dk.truck_park;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import jepsen.dk.truck_park.functionality.SingleTon;
import jepsen.dk.truck_park.functionality.Spot;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap gMap;
    private Dialog dialog;
    private Button findRoute;
    private ImageView closePopUp;
    private Spot spot; //Spottet der klikkes på

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            System.out.println("GRANTED");
            gMap.setMyLocationEnabled(true);

        } else {
            System.out.println("NOT GRANTED");
            finish();
        }

        //Tilføjer Markers til kort:
        int id = 0;
        for(int i=0; i< SingleTon.spotList.size(); i++){
            LatLng mark = new LatLng(Double.parseDouble(SingleTon.spotList.get(i).getLat()),Double.parseDouble(SingleTon.spotList.get(i).getLng()));
            gMap.addMarker(new MarkerOptions().position(mark).snippet(Integer.toString(id)));
            gMap.setOnMarkerClickListener(this);
            id++;
        }


        LatLng cPos = new LatLng(SingleTon.myLocation.getLocation().getLatitude(), SingleTon.myLocation.getLocation().getLongitude());
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cPos, 12));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int spotNumber = Integer.parseInt(marker.getSnippet());
        spot = SingleTon.spotList.get(spotNumber);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        if(dialog==null){
            dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        dialog.setContentView(R.layout.dialog_spot);

        findRoute = (Button) dialog.findViewById(R.id.findRoute_button);
        closePopUp = (ImageView) dialog.findViewById(R.id.close_imageView);
        closePopUp.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {dialog.hide();}});

        ImageView shower = (ImageView) dialog.findViewById(R.id.wc_imageView);
        ImageView food = (ImageView) dialog.findViewById(R.id.food_imageView);
        ImageView gas = (ImageView) dialog.findViewById(R.id.fuel_imageView);
        ImageView roadTrain = (ImageView) dialog.findViewById(R.id.roadTrain_img);

        //Kan bruges på et andet tidspunkt
//        if (spot.getShower()){
//            shower.setImageResource(R.drawable.wc_t_check);
//        } else {
//            shower.setImageResource(R.drawable.wc_t);
//        }
//        if (spot.getFood()){
//            food.setImageResource(R.drawable.food_t_check);
//        } else {
//            food.setImageResource(R.drawable.food_t);
//        }
//        if(spot.getGas()){
//            gas.setImageResource(R.drawable.fuel_t_check);
//        } else {
//            gas.setImageResource(R.drawable.fuel_t);
//        }
//        if(spot.getRoadTrain()){
//            roadTrain.setImageResource(R.drawable.roadtrain_txt_noback_check);
//        } else {
//            roadTrain.setImageResource(R.drawable.roadtrain_txt_noback);
//        }
        shower.setVisibility(View.INVISIBLE);
        food.setVisibility(View.INVISIBLE);
        gas.setVisibility(View.INVISIBLE);
        roadTrain.setVisibility(View.INVISIBLE);

        dialog.show();

        findRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?&daddr="
                                + spot.getLat() + ","
                                + spot.getLng()));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        return false;
    }
}
