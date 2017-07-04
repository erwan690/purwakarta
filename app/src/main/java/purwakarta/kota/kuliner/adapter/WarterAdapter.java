package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.DetailWarung;

/**
 * Created by aomcenter on 7/1/2017.
 */

public class WarterAdapter extends RecyclerView.Adapter <WarterViewHolder> {

    private List<DetailWarung> detailWarungs;
    private Context mcontext;

    public WarterAdapter (Context context,List<DetailWarung> detailWarungList )
    {
        this.detailWarungs = detailWarungList;
        this.mcontext = context;

    }

    @Override
    public WarterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_warter, null);
        WarterViewHolder viewHolder = new WarterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WarterViewHolder holder, int position) {

        DetailWarung detailna = detailWarungs.get(position);
        holder.namaTXT.setText(detailna.getNamawarung());
        holder.alamatTxt.setText(detailna.getAlamat());
        Picasso.with(mcontext).load("http://kulinerpwk.id/maranggi/"+detailna.getGambar()).error(android.R.drawable.stat_notify_error).fit().into(holder.imageWarter);

        holder.jarakTxt.setText(String.valueOf(detailna.getDistance())+" Km");



    }

    @Override
    public int getItemCount() {
        return detailWarungs.size();
    }
}
