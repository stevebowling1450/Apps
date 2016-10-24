package com.stveo.stevebowling.elevenmapper;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int street_level= 10;
    int building =15;
    LatLng Banner = new LatLng(37.52917112, -82.66207811);
    Place currentPlace=null;

    //Google Directions key
    String serverKey="AIzaSyBpCZKQj8Kb1u6VZhkAVArE4Es_qWO_0D4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)  getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(MapsActivity.this, "Place "+ place.getAddress(),Toast.LENGTH_LONG).show();
                currentPlace = place;
                mMap.addMarker(new MarkerOptions().position(currentPlace.getLatLng()).title("Marker in Steve's House").icon
                        (BitmapDescriptorFactory.fromResource(R.mipmap.superman)).snippet("Home"));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPlace.getLatLng(), building));
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(MapsActivity.this, "Error"+ status,Toast.LENGTH_LONG).show();
                Log.d("ERROR","error"+status);

            }
        });
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
        mMap = googleMap;
        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);
        UiSettings UiSettings= mMap.getUiSettings();
        UiSettings.setZoomControlsEnabled(true);
        UiSettings.setCompassEnabled(true);
        UiSettings.setTiltGesturesEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
           @Override
            public boolean onMarkerClick(Marker marker){
                Toast .makeText(MapsActivity.this, "Custom Superman Marker", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(MapsActivity.this, "Everywhere you go, there you are", Toast.LENGTH_LONG).show();
                if (currentPlace != null) {
                    GoogleDirection.withServerKey(serverKey)
                            .from(currentPlace.getLatLng())
                            .to(Banner)
                            .execute(new DirectionCallback() {
                                @Override
                                public void onDirectionSuccess(Direction direction, String rawBody) {
                                    String status = direction.getStatus();
                                    if (status.equals(RequestResult.OK)) {
                                        Route route = direction.getRouteList().get(0);
                                        Leg leg = route.getLegList().get(0);
                                        List<Step> stepList = leg.getStepList();


                                        String strDir = null;

                                        for (int i = 0; i< stepList.size(); i++){
                                            Log.d("ELEVENMAPPER--", stepList.get(i).getHtmlInstruction());
                                            strDir += stepList.get(i).getHtmlInstruction();

                                        }
                                        Intent intent=new Intent(MapsActivity.this, DirectionsActivity.class);
                                        intent.putExtra("Directions ", strDir);
                                        startActivity(intent);


                                    }else if (status.equals(RequestResult.NOT_FOUND)){
                                        Toast.makeText(MapsActivity.this, "Error You lost Fool", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(MapsActivity.this, "Error, Go home your Drunk", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        @Override
                        public void onDirectionFailure(Throwable t) {
                            Toast.makeText(MapsActivity.this,"You lost fool!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        mMap.addMarker(new MarkerOptions().position(Banner).title("Marker in Steve's House").icon
                (BitmapDescriptorFactory.fromResource(R.mipmap.superman)).snippet("Home"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Banner, building));

        GoogleMap.InfoWindowAdapter infoWinAdapter=new GoogleMap.InfoWindowAdapter(){
            @Override
            public View getInfoWindow(Marker marker){
                return null;
            }

            @Override
            public View getInfoContents (Marker marker){
                View v = getLayoutInflater().inflate(R.layout.layout_info_window, null);
                TextView tvAddress= (TextView)v.findViewById(R.id.tv_address);
                        tvAddress.setText(getAddress());
                return v;
            }
        };
        mMap.setInfoWindowAdapter(infoWinAdapter);
    }
    public String getAddress(){
        String retVal = "unknown";
        retVal="My Address;";

        Geocoder geocoder;
        List<Address> addresses=null;

        geocoder=new Geocoder(this, Locale.getDefault());

        if (currentPlace==null){
        try {
            addresses= geocoder.getFromLocation(Banner.latitude, Banner.longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address= addresses.get(0).getAddressLine(0);
        String city= addresses.get(0).getLocality();
        String state= addresses.get(0).getAdminArea();
        String knownName= addresses.get(0).getFeatureName();

        retVal=knownName+"\n"+city+", "+state;}

        else {
            retVal = (currentPlace.getAddress().toString());
            retVal = retVal.replace(",", "\n");
        }

        return retVal;
    }
}
