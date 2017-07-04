package purwakarta.kota.kuliner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import purwakarta.kota.kuliner.adapter.KategoriAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.RecyclerViewClickListener;
import purwakarta.kota.kuliner.helper.RecyclerViewTouchListener;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.DataKategori;
import purwakarta.kota.kuliner.model.Kategori;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KategoriFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private KategoriAdapter mAdapter;
    private List<DataKategori> dataKategoris;
    private SessionManager session;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_kategori, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Kategori");
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.kategori_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        session = new SessionManager(getContext());
        getKategoriall();

    }
    private void getKategoriall() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<Kategori> mService = apiService.getKategori();
        mService.enqueue(new Callback<Kategori>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<Kategori> call, Response<Kategori> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    Kategori severKategori = response.body();
                    if (severKategori != null) {
                        //below is how you can get the list of result

                       dataKategoris = severKategori.getKategoris();
                        mAdapter = new KategoriAdapter(getContext(),dataKategoris);
                        mRecyclerView.setAdapter(mAdapter);
                        initClick();

                    } else {
                        Toast.makeText(getContext(), "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Kategori> call, Throwable t) {
                call.cancel();
                Toast.makeText(getContext(), "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void initClick() {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String kategori = dataKategoris.get(position).getNamaKategori();
                String id = dataKategoris.get(position).getIdKategori();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id,kategori);
                WarungFragment fragment = new WarungFragment();
                replaceFragment(fragment);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
