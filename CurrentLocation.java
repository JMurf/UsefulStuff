package com.app.danny.barcodescanner;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;




import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by danny on 3/13/18.
 */

public class CurrentLocation implements LocationListener {

    LocationManager lm;
    Location location;
    Context ctx;

    public CurrentLocation(Context ctx){
        this.ctx = ctx;
    }

    public double[] getCurrentLocation(){
        try{
            lm = (LocationManager)ctx.getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_LOW);
            // String provider = lm.getBestProvider(criteria, true);

            location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            // Toast.makeText(ctx,"LOCATION :"+location,Toast.LENGTH_SHORT).show();

            if (location == null) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                onLocationChanged(location);
                return new double[]{location.getLatitude(),location.getLongitude()};
            }

        }catch (SecurityException e){
            Log.d("SECURITY EXCEPTION: ",e.getMessage());
        }
        return new double[]{0.0,0.0};
    }



    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //api 23 and greater, check user permission
    /* private void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(LoadingScreen.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            ActivityCompat.requestPermissions(LoadingScreen.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE);
        }
        // private static int REQUEST_CODE = 9000;
    }*/

}


