package com.novanto.covid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Maps extends Fragment implements OnMapReadyCallback {

    private GoogleMap mapAPI;
    private SupportMapFragment mapFragment;

    private ProgressBar progressBar;
    private static final String TAG = MainActivity.class.getSimpleName();


    private ArrayList<String> country = new ArrayList<>();
    ArrayList<String> cases = new ArrayList<>();
    private ArrayList<LatLng> coordinate = new ArrayList<>();

    public Maps(){

    }


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.maps_frag, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        getListMap();

        if(mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    private void getListMap() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://corona.lmao.ninja/v2/countries?yesterday&sort";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Parsing JSON
                String result = new String(responseBody);

                try {
                    JSONArray jsonArray = new JSONArray(result);

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String count = jsonObject.getString("country");
                        Log.d(TAG, "Country: " + count);
                        JSONObject lat = jsonObject.getJSONObject("countryInfo");
                        int x = lat.getInt("lat");
                        int y = lat.getInt("long");
                        Log.d(TAG, "lat: " + x + " long: " + y);
                        LatLng place = new LatLng(x,y);
                        coordinate.add(place);
                        country.add(count);

                        Log.d(TAG, "Coordinate size: " + coordinate.size());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbiden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapAPI = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Log.d(TAG, "onMapReady: " + "Ready to show " + coordinate.size() + " country");

//        for (int i=0;i<coordinate.size();i++){
//                mapAPI.addMarker(new MarkerOptions().position(coordinate.get(i)).title(country.get(i)));
//                mapAPI.moveCamera(CameraUpdateFactory.newLatLng(coordinate.get(i)));
//        }

        LatLng bekauheni = new LatLng(-6.175110,106.865036);

        mapAPI.addMarker(new MarkerOptions().position(bekauheni).title("Total Kasus : 9542"));

        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(bekauheni));

    }
}
