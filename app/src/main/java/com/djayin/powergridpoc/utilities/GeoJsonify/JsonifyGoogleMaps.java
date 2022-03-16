package com.djayin.powergridpoc.utilities.GeoJsonify;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.djayin.powergridpoc.R;
import com.djayin.powergridpoc.utilities.AppUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

class JsonifyGoogleMaps {
    static void geoJsonifyMap(GoogleMap map, String str, Context context) throws IOException, JSONException {
        GeoJsonLayer layer = null;

        layer = new GeoJsonLayer(map, new JSONObject(str));
        if (layer != null) {
            //layer.getDefaultPolygonStyle().setStrokeColor(Color.parseColor("#3bffba"));
            //layer.getDefaultPolygonStyle().setFillColor(Color.BLUE);
            layer.addLayerToMap();
        }
        if (layer != null) {
            try {
                //map.moveCamera(CameraUpdateFactory.newLatLngBounds(getLayerBoundingBox(layer), 0));
                getLayerBoundingBox(layer);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.i("geojson-viewer", "No coordinates available to center the camera.");
            }
        }
    }

    private static LatLngBounds getLayerBoundingBox(GeoJsonLayer layer){
        LatLngBounds.Builder builder = LatLngBounds.builder();
        int x = 1;
        for (GeoJsonFeature feature : layer.getFeatures()) {

            if (feature.hasGeometry()) {
                if (feature.getGeometry().getGeometryType().contains("Point")) {
                    //double magnitude = Double.parseDouble(feature.getProperty("mag"));

                    // Get the icon for the feature
                    /*BitmapDescriptor pointIcon = BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.fromResource(R.drawable.user_icon_resized));*/

                    // Create a new point style
                    GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();

                    // Set options for the point style
                    pointStyle.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.electric_pole_marker_resized));
                    /*pointStyle.setTitle("Magnitude of " + magnitude);
                    pointStyle.setSnippet("Earthquake occured " + feature.getProperty("place"));*/

                    // Assign the point style to the feature
                    feature.setPointStyle(pointStyle);
                }

                Geometry geometry = feature.getGeometry();
                if (geometry instanceof GeoJsonPolygon) {
                    List<? extends List<LatLng>> lists =
                            ((GeoJsonPolygon) geometry).getCoordinates();
                    AppUtils.geoLatLon = lists.get(0).get(0);
                    for (List<LatLng> list : lists) {
                        for (LatLng latLng : list) {
                            builder.include(latLng);
                        }
                    }
                }
                x++;
            }
        }

        return builder.build();
    }
}
