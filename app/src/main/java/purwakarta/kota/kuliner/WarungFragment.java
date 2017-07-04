package purwakarta.kota.kuliner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import purwakarta.kota.kuliner.adapter.WarungAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.RecyclerViewClickListener;
import purwakarta.kota.kuliner.helper.RecyclerViewTouchListener;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.DataWarung;
import purwakarta.kota.kuliner.model.Warung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WarungFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private WarungAdapter mAdapter;
    private List<DataWarung> mDataWarungs;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_warung, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.warung_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        session= new SessionManager(getContext());

        HashMap<String, String> keyid = session.getKeyId();
        String idkt = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        getActivity().setTitle(nama);
        getWarungan(idkt);

    }

    private void getWarungan(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<Warung> mService = apiService.getWarung(id);
        mService.enqueue(new Callback<Warung>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<Warung> call, Response<Warung> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    Warung severKategori = response.body();
                    if (severKategori != null) {
                        //below is how you can get the list of result

                        mDataWarungs = severKategori.getWarungs();
                        mAdapter = new WarungAdapter(getContext(),mDataWarungs);
                        mRecyclerView.setAdapter(mAdapter);
                        initClick();

                    } else {
                        Toast.makeText(getContext(), "euweuh dataan", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "gagal total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Warung> call, Throwable t) {
                call.cancel();
                Toast.makeText(getContext(), "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
    private void initClick() {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String warung = mDataWarungs.get(position).getNamaWarung();
                String id = mDataWarungs.get(position).getIdWarung();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id,warung);
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();

    }
}
