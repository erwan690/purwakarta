package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 6/30/2017.
 */

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    ImageView galeryImg;
    public GalleryViewHolder(View itemView) {
        super(itemView);

        galeryImg = (ImageView) itemView.findViewById(R.id.item_galeri);
    }
}
