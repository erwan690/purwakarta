package purwakarta.kota.kuliner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import purwakarta.kota.kuliner.adapter.WarterAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.GPSTracker;
import purwakarta.kota.kuliner.helper.RecyclerViewClickListener;
import purwakarta.kota.kuliner.helper.RecyclerViewTouchListener;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.DetailWarung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WarterActivity extends AppCompatActivity {

    private Context mContext;
    private GPSTracker gps;
    private RecyclerView mRecyclerView;
    private WarterAdapter mAdapter;
    private String lati, longi;
    private double latitude, longitude, haversine, distance;
    private SessionManager session;
    private List<DetailWarung> detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warter);
        setTitle("Lokasi Terdekat");
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_warter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        session = new SessionManager(this);


        mContext = this;

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(WarterActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            //Toast.makeText(mContext,"You need have granted permission",Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext, WarterActivity.this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                lati = String.valueOf(latitude);
                longi = String.valueOf(longitude);

                session.setLokasi(lati, longi);

                // \n is for new line
                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                getWarungna();
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    // contacts-related task you need to do.

                    gps = new GPSTracker(mContext, WarterActivity.this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        lati = String.valueOf(latitude);
                        longi = String.valueOf(longitude);

                        session.setLokasi(lati, longi);

                        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                        getWarungna();
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    //Toast.makeText(mContext, "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void getWarungna() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<DetailWarung>> mService = apiService.getAllWarung();
        mService.enqueue(new Callback<List<DetailWarung>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<DetailWarung>> call, Response<List<DetailWarung>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<DetailWarung> severWarung = response.body();
                    if (severWarung != null) {
                        for (int l = 0; l < severWarung.size(); l++) {
                            String latwr = severWarung.get(l).getLatitude();
                            String lotwr = severWarung.get(l).getLongitude();
                            Double latwrdb = Double.parseDouble(latwr);
                            Double lotwrdb = Double.parseDouble(lotwr);
                            Double jarak = fungsiHaversine(latitude, longitude, latwrdb, lotwrdb);
                            severWarung.get(l).setDistance(jarak);

                        }
                        Collections.sort(severWarung, new Comparator<DetailWarung>() {

                            @Override
                            public int compare(DetailWarung o1, DetailWarung o2) {
                                return Double.compare(o1.getDistance(), o2.getDistance());
                            }

                        });
                        detail = severWarung;
                        mAdapter = new WarterAdapter(WarterActivity.this, severWarung);
                        mRecyclerView.setAdapter(mAdapter);
                        initClick();

                    } else {
                        Toast.makeText(WarterActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(WarterActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<DetailWarung>> call, Throwable t) {
                call.cancel();
                Toast.makeText(WarterActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }

    private Double fungsiHaversine(Double latitude, Double longitude, Double latwrdb, Double lotwrdb) {
        Double dLat, dLon, rLat1, rLat2, c;
        int earth = 6371;
        dLat = (latitude - latwrdb)* Math.PI / 180;
        dLon = (longitude - lotwrdb)* Math.PI / 180;
        rLat1 = latitude * Math.PI / 180;
        rLat2 = latwrdb * Math.PI / 180;

        haversine = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(rLat1)
                * Math.cos(rLat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        c = 2 * Math.asin(Math.sqrt(haversine));
        distance = earth * c;

        distance = Math.round(distance * 100);
        distance = distance / 100;
        return distance;
    }

    private void initClick() {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(WarterActivity.this, mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String warung = detail.get(position).getNamawarung();
                String id = detail.get(position).getIdWarung();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id,warung);
                Intent intent = new Intent(WarterActivity.this,DetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();

    }
}
