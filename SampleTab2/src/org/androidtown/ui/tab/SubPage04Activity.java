package org.androidtown.ui.tab;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Map activity to show current location
 *
 * @author Mike
 */
public class SubPage04Activity extends MapActivity {

	MapView mapView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage04);

   	 	mapView = (MapView) findViewById (R.id.mapview);
   	 	mapView.setBuiltInZoomControls(true);

   	 	startLocationService();

    }

    protected boolean isRouteDisplayed() {
		return false;
	}

    private void startLocationService() {

    	// get manager instance
    	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// set listener
    	GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime,
					minDistance,
					gpsListener);

		Toast.makeText(getApplicationContext(), "Location Service started.\nyou can test using DDMS.", Toast.LENGTH_SHORT).show();
    }


	private class GPSListener implements LocationListener {

	    public void onLocationChanged(Location location) {
			//capture location data sent by current provider
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSLocationService", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

			showCurrentLocation(latitude, longitude);

		}

	    public void onProviderDisabled(String provider) {
	    }

	    public void onProviderEnabled(String provider) {
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }

	}


	private void showCurrentLocation(Double latitude, Double longitude) {
		double intLatitude = latitude.doubleValue() * 1000000;
		double intLongitude = longitude.doubleValue() * 1000000;

		// new GeoPoint to be placed on the MapView
		GeoPoint geoPt = new GeoPoint((int) intLatitude, (int) intLongitude);

		MapController controller = mapView.getController();
		controller.animateTo(geoPt);

		int maxZoomlevel = mapView.getMaxZoomLevel();
		int zoomLevel = (int) ((maxZoomlevel + 1)/1.15);
		controller.setZoom(17);
		controller.setCenter(geoPt);

		mapView.setSatellite(true);
		mapView.setTraffic(false);

	}


}