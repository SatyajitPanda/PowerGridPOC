
package com.djayin.powergridpoc.utilities.GeoJsonify;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonify {


    public static void geoJsonifyMap(GoogleMap map, String str, Context context) throws IOException, JSONException {
        JsonifyGoogleMaps.geoJsonifyMap(map, str, context);
    }


    private static List<Integer> generateColorsList(int color, int size) {
        List<Integer> jsonColors = new ArrayList<>();
        for (int i=0; i<size; i++){
            jsonColors.add(color);
        }

        return jsonColors;
    }
}
