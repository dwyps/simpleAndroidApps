package com.frangrgec.hikerwatchdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    LocationManager locationManager;
    LocationListener locationListener;
    LatLng currentLocation;
    TextView latitudeTextView;
    TextView longitudeTextView;
    TextView accuracyTextView;
    TextView altitudeTextView;
    TextView addressTextView;

    //After permission granted gets the location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED)
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeTextView=findViewById(R.id.latitudeTextView);
        longitudeTextView=findViewById(R.id.longitudeTextView);
        altitudeTextView=findViewById(R.id.altitudeTextView);
        accuracyTextView=findViewById(R.id.accuracyTextView);
        addressTextView=findViewById(R.id.addressTextView);

        locationManager= (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //Gets your current location and displays the info
        locationListener= new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                Geocoder geocoder= new Geocoder(getApplicationContext(), Locale.getDefault());

                latitudeTextView.setText("Latitude: " + String.format("%.5f",location.getLatitude()));
                longitudeTextView.setText("Longitude: " + String.format("%.5f",location.getLongitude()));
                accuracyTextView.setText("Accuracy: " + location.getAccuracy());
                altitudeTextView.setText("Altitude: " + location.getAltitude());

                try
                {
                    List<Address> listAddress= geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    if(listAddress!=null&&listAddress.size()>0)
                    {
                        addressTextView.setText("Address: \n" + listAddress.get(0).getAddressLine(0));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    addressTextView.setText("Address: \n Could not find the Address!");

                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onProviderDisabled(String provider)
            {

            }
        };

        //Checks GPS permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
