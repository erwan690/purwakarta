package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 6/28/2017.
 */

public class WarungViewHolder extends RecyclerView.ViewHolder {

    TextView jdWr,ketWr;
    ImageView gmWr;
    public WarungViewHolder(View itemView) {
        super(itemView);
        jdWr = (TextView) itemView.findViewById(R.id.judul_warung);
        ketWr = (TextView) itemView.findViewById(R.id.ket_warung);
        gmWr = (ImageView) itemView.findViewById(R.id.warung_gambar);
    }
}
