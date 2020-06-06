package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private EditText mTraziLokaciju;
    private ImageView mGps;
    private static final String TAG = "MapActivity";
    private static String textDuzinaForma = null ;
    private static String textMamacForma = null ;
    private static String textRibaForma = null;
    private static String textTezinaForma = null;
    private static String textDatumForma = null;
    private static String textPZForma = null;
    private static String loc = null;


    public static void setForm(
            String textDuzina,
            String textMamac,
            String textRiba,
            String textTezina,
            String textDatum,
            String textPZ
    )  {

        textDuzinaForma = textDuzina;
        textRibaForma = textRiba;
        textMamacForma = textMamac;
        textTezinaForma = textTezina;
        textDatumForma = textDatum;
        textPZForma = textPZ;

    }

    public void cistiFormu(){
        textDuzinaForma = null;
        textMamacForma = null;
        textRibaForma = null;
        textTezinaForma = null;
        textDatumForma = null;
        textPZForma = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        mTraziLokaciju = findViewById(R.id.location);
        mGps = findViewById(R.id.gps);
        init();
    }

    private void init(){
        Log.d(TAG,"Init: initializing");

        mTraziLokaciju.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        ||  actionId == EditorInfo.IME_ACTION_DONE
                        ||  event.getAction() == KeyEvent.ACTION_DOWN
                        ||  event.getAction() == KeyEvent.KEYCODE_ENTER
                ) {
                    try {
                        geoLocation();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }

        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat =  	45.227165;
                double lng =  	13.594740;

                goToLocation(lat,lng,15);
            }
        });


    }


    private void geoLocation() throws IOException  {

        String location = mTraziLokaciju.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<Address> list = new ArrayList<>();

        try {
            list = gc.getFromLocationName(location,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(list.size() > 0){
            Address address = list.get(0);
            Toast.makeText(this,address.toString(),Toast.LENGTH_SHORT).show();

            Log.d(TAG,"geoLocation: naso samje"+ address.toString());

            double lat = address.getLatitude();
            double lng = address.getLongitude();

            goToLocation(lat,lng,15);
        }


    }


    public void goToLocation(double lat, double lng, float zoom) {

        LatLng ll = new LatLng(lat, lng);

        mMap.addMarker(new MarkerOptions().position(ll).draggable(true));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(mMap != null){
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.activity_info, null);

                    TextView textRiba =      v.findViewById(R.id.textRiba);
                    TextView textDuzina =     v.findViewById(R.id.textDuzina);
                    TextView textMamac =     v.findViewById(R.id.textMamac);
                    TextView textTezina =     v.findViewById(R.id.textTezina);
                    TextView textDatum =     v.findViewById(R.id.textDatum);
                    TextView textPZ =     v.findViewById(R.id.textPZ);

                    textDuzina.setText("     Duzina: "+String.valueOf(textDuzinaForma));
                    textMamac.setText("     Mamac: "+String.valueOf(textMamacForma));
                    textRiba.setText("     Riba: "+String.valueOf(textRibaForma));
                    textTezina.setText("     Tezina: "+String.valueOf(textTezinaForma) );
                    textDatum.setText("     Datum: "+String.valueOf(textDatumForma));
                    textPZ.setText("     Riba je: "+String.valueOf(textPZForma));

                    return v;
                }
            });
        }

    }



    public void openActivityFormaRiba(View view)  {
        cistiFormu();
        Intent intent = new Intent(this, RibaForma.class);
        startActivity(intent);

    }

    public void openActivityLogin(View view)  {
        cistiFormu();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}