package com.novanto.covid;

import android.location.Location;
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

    private static final String TAG = MainActivity.class.getSimpleName();


    private ArrayList<String> country = new ArrayList<>();
    ArrayList<String> cases = new ArrayList<>();
    private ArrayList<LatLng> coordinate = new ArrayList<>();

    private ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng jakarta = new LatLng(-6.200000,106.816666);
    LatLng aceh = new LatLng(5.548290,95.323753);
    LatLng bandung = new LatLng(-7.025253,107.519760);
    LatLng riau = new LatLng(1.694394,101.445007);
    LatLng bali = new LatLng(-8.650000,115.216667);
    LatLng surabaya = new LatLng(-7.250445,112.768845);
    LatLng balikpapan = new LatLng(-1.269160,116.825264);
    LatLng manado = new LatLng(1.474830,124.842079);

    String[] cityName = {"Jakarta","Aceh","Manado","Balikpapan","Bali","Bandung","Riau","Surabaya"};




    public Maps(){

    }


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.maps_frag, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        addCityToMarker();

        return v;
    }

    private void addCityToMarker(){
        arrayList.add(jakarta);
        arrayList.add(aceh);
        arrayList.add(manado);
        arrayList.add(balikpapan);
        arrayList.add(bali);
        arrayList.add(bandung);
        arrayList.add(riau);
        arrayList.add(surabaya);
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
//                        Log.d(TAG, "Country: " + count);
                        JSONObject lat = jsonObject.getJSONObject("countryInfo");
                        int x = lat.getInt("lat");
                        int y = lat.getInt("long");
//                        Log.d(TAG, "lat: " + x + " long: " + y);
                        LatLng place = new LatLng(x,y);
                        coordinate.add(place);
                        country.add(count);

//                        Log.d(TAG, "Coordinate size: " + coordinate.size());
                    }

                    if(mapFragment == null){
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        mapFragment = SupportMapFragment.newInstance();
                        ft.replace(R.id.map,mapFragment).commit();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
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

        getListMap();

        for (int i=0;i<arrayList.size();i++){
                mapAPI.addMarker(new MarkerOptions().position(arrayList.get(i)).title(cityName[i]));
                mapAPI.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                mapAPI.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }

//        LatLng bekauheni = new LatLng(-6.175110,106.865036);

//        mapAPI.addMarker(new MarkerOptions().position(bekauheni).title("Total Kasus : 9542"));
//
//        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(bekauheni));

    }


}
