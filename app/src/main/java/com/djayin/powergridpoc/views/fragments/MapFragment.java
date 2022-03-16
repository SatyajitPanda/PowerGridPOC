package com.djayin.powergridpoc.views.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djayin.powergridpoc.R;
import com.djayin.powergridpoc.databinding.FragmentMapBinding;
import com.djayin.powergridpoc.utilities.AppConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    private FragmentMapBinding binding;
    private GoogleMap myGoogleMap;
    private boolean isCameraSet = false;
    BottomSheetDialogFragment bottomSheetForm;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_map, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.onResume();
        binding.mapView.getMapAsync(this);

        bottomSheetForm = FormDialogFragment.newInstance();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myGoogleMap = googleMap;
        setUpGeoJSON();
    }

    void setUpGeoJSON(){
        if (myGoogleMap != null) {
            myGoogleMap.clear();
            myGoogleMap.getUiSettings().setZoomControlsEnabled(false);
            myGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            myGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                askPermission();
                //return;
            }
            myGoogleMap.setMyLocationEnabled(true);
           /* MapStyleOptions options = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json);
            myGoogleMap.setMapStyle(options);*/
            //myGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            try {
                GeoJsonLayer layer = new GeoJsonLayer(myGoogleMap, R.raw.sample_geo_json, getActivity());
                addGeoJsonLayerToMap(layer);
            } catch (IOException e) {
                // Log.e(mLogTag, "GeoJSON file could not be read");
            } catch (JSONException e) {
                // Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
            }

        }
    }

    private void addGeoJsonLayerToMap(GeoJsonLayer layer) {
        layer.addLayerToMap();
        if (myGoogleMap != null) {
            myGoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location arg0) {
                    myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(),
                            arg0.getLongitude())));
                    moveCamera(arg0.getLatitude(), arg0.getLongitude());

                }
            });
            myGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker m) {
                    bottomSheetForm.show(getActivity().getSupportFragmentManager(), bottomSheetForm.getTag());
                    return false;
                }
            });
            /*myGoogleMap.setOnMarkerClickListener(marker -> {
                bottomSheetForm.show(getActivity().getSupportFragmentManager(), bottomSheetForm.getTag());
                //bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                return true;
            });*/
        }

    }

    private void moveCamera(double lat, double lon){
        if(!isCameraSet) {
            myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 13));
            isCameraSet = true;
            bottomSheetForm.show(getActivity().getSupportFragmentManager(), bottomSheetForm.getTag());
        }
    }

    /*private void readFromGeoJson(){
        try {
            AssetManager manager = getAssets();
            InputStream strem;
            try {
                strem = getAssets().open("covd.txt");
                int size = strem.available();
                byte[] buffer = new byte[size];
                strem.read(buffer);
                strem.close();
                //String response = convertStreamToString(strem);
                String txt = new String(buffer, "UTF-8");
                JsonifyGoogleMaps.geoJsonifyMap(myGoogleMap, txt, jsonColors, getActivity());
                //String response = convertStreamToString(strem);
                GeoJsonify.geoJsonifyMap(myGoogleMap, txt, this.getJsonColors(), GoogleMapsActivity.this);
            } catch (Exception ex){

            }
            *//*Uri imageUri = Uri.fromFile(new File("file:///android_asset/ForCovid.geojson"));
            List<Uri> uris = new ArrayList<>();
            uris.add(imageUri);
            GeoJsonify.geoJsonifyMap(googleMap, uris, this.getJsonColors(), GoogleMapsActivity.this);*//*
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this.getContext(), R.string.geojson_opener_unable_to_read, Toast.LENGTH_SHORT).show();
        }
    }*/

    private void askPermission(){
        ActivityCompat.requestPermissions(getActivity(), AppConstants.ALL_PERMISSIONS, AppConstants.MY_PERMISSIONS_REQUEST);
    }
}