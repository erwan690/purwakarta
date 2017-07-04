package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 6/27/2017.
 */

public class KategoriViewHolder extends RecyclerView.ViewHolder{

    TextView judul;
    ImageView gambar;

    public KategoriViewHolder(View itemView) {
        super(itemView);

        judul = (TextView) itemView.findViewById(R.id.judul_kategori);
        gambar =(ImageView) itemView.findViewById(R.id.gambar_kategori);
    }
}
