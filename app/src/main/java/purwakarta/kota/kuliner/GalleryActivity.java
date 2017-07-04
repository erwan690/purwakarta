package purwakarta.kota.kuliner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import purwakarta.kota.kuliner.adapter.GalleryAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.GalerryWarung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    private SessionManager session;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        session= new SessionManager(this);
        HashMap<String, String> keyid = session.getKeyId();
        String idwr = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        setTitle(nama);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_galeri);
        int numberOfColumns = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        getGaleri(idwr);
    }

    private void getGaleri(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<GalerryWarung>> mService = apiService.getGalerina(id);
        mService.enqueue(new Callback<List<GalerryWarung>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<GalerryWarung>> call, Response<List<GalerryWarung>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<GalerryWarung> warungList = response.body();
                    if (warungList != null) {
                        //below is how you can get the list of result
                        mAdapter = new GalleryAdapter(GalleryActivity.this,warungList);
                        mRecyclerView.setAdapter(mAdapter);
                        //initClick();

                    } else {
                        Toast.makeText(GalleryActivity.this, "euweuh dataan", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(GalleryActivity.this, "gagal total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<GalerryWarung>> call, Throwable t) {
                call.cancel();
                Toast.makeText(GalleryActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
}
