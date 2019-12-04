package com.example.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap = googleMap;

        LatLng digitalHouse = new LatLng(-23.6024956, -46.6774455);
        mMap.addMarker(new MarkerOptions().position(digitalHouse).title("Marker in Digital House"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(digitalHouse, 15f));

        setmMapClick();
        cleanMap();
    }

//    Marca um ponto com um clique longo
    private void setmMapClick(){
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

//    Remove um ponto marcado
    private void cleanMap(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.normal_map){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return true;

        } else if (item.getItemId() == R.id.hybrid_map){
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            return true;

        } else if (item.getItemId() == R.id.satellite_map){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;

        } else if (item.getItemId() == R.id.terrain_map) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
