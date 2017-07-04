package purwakarta.kota.kuliner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.DetailWarung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView alamatTxt,deskripTxt,telpTxt,fasiliTxt,galeriTxt,mapTxt;
    private ImageView gambarImg;
    private Context mContext;
    private SessionManager session;
    private String lat,lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        session= new SessionManager(this);
        HashMap<String, String> keyid = session.getKeyId();
        String idwr = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        setTitle(nama);
        alamatTxt = (TextView) findViewById(R.id.alamat_warung_detail);
        deskripTxt = (TextView) findViewById(R.id.deskripsi_warung_detail);
        telpTxt= (TextView) findViewById(R.id.no_tlp_detail);
        fasiliTxt = (TextView) findViewById(R.id.fasilitas_detail);
        gambarImg = (ImageView) findViewById(R.id.detail_gamber);
        galeriTxt = (TextView) findViewById(R.id.galeri_detail);
        mapTxt = (TextView) findViewById(R.id.map_detail);
        galeriTxt.setOnClickListener(this);
        mapTxt.setOnClickListener(this);
        getDetailWarungan(idwr);

    }




    private void getDetailWarungan(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<DetailWarung>> mService = apiService.getDetailna(id);
        mService.enqueue(new Callback<List<DetailWarung>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<DetailWarung>> call, Response<List<DetailWarung>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<DetailWarung> severWarung = response.body();
                    if (severWarung != null) {
                        alamatTxt.setText(severWarung.iterator().next().getAlamat());
                        deskripTxt.setText(severWarung.iterator().next().getDeskripsi());
                        telpTxt.setText(severWarung.iterator().next().getTelephone());
                        fasiliTxt.setText(severWarung.iterator().next().getFasilitas());
                        lat = severWarung.iterator().next().getLatitude();
                        lot = severWarung.iterator().next().getLongitude();
                        Picasso.with(DetailActivity.this).load("http://kulinerpwk.id/maranggi/"+severWarung.iterator().next().getGambar()).error(android.R.drawable.stat_notify_error).fit().into(gambarImg);


                    } else {
                        Toast.makeText(DetailActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<DetailWarung>> call, Throwable t) {
                call.cancel();
                Toast.makeText(DetailActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.galeri_detail:
                Intent intent = new Intent(DetailActivity.this,GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.map_detail:
                session.setMapwarung(lat,lot);
                Intent intent2 = new Intent(DetailActivity.this,MapsActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
