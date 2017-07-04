package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.DataKategori;

/**
 * Created by aomcenter on 6/27/2017.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriViewHolder> {

    private List<DataKategori> mDataKategoris;
    private Context mContext;

    public KategoriAdapter(Context context,List<DataKategori> dataKategoriList)
    {
        this.mDataKategoris = dataKategoriList;
        this.mContext= context;
    }

    @Override
    public KategoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori, null);
        KategoriViewHolder viewHolder = new KategoriViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KategoriViewHolder holder, int position) {
        DataKategori kategori = mDataKategoris.get(position);
        holder.judul.setText(kategori.getNamaKategori());

        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/"+kategori.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return mDataKategoris.size();
    }
}
