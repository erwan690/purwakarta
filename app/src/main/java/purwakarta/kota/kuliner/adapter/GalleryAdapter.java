package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.GalerryWarung;


/**
 * Created by aomcenter on 6/30/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    private List<GalerryWarung> warungs;
    private Context mContext;

    public GalleryAdapter (Context context, List<GalerryWarung> galerryWarungs)
    {
        this.warungs = galerryWarungs;
        this.mContext = context;

    }
    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_galery, null);
        GalleryViewHolder viewHolder = new GalleryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {

        GalerryWarung warung = warungs.get(position);

        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/"+warung.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.galeryImg);

    }

    @Override
    public int getItemCount() {
        return warungs.size();
    }
}
