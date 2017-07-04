package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 7/1/2017.
 */

public class WarterViewHolder extends RecyclerView.ViewHolder{

    ImageView imageWarter;
    TextView namaTXT,alamatTxt,jarakTxt;

    public WarterViewHolder(View itemView) {
        super(itemView);

        imageWarter = (ImageView) itemView.findViewById(R.id.gambar_warter);
        namaTXT = (TextView) itemView.findViewById(R.id.warung_warter);
        alamatTxt =(TextView) itemView.findViewById(R.id.alamat_warter);
        jarakTxt = (TextView) itemView.findViewById(R.id.jarak_warter);

    }
}
