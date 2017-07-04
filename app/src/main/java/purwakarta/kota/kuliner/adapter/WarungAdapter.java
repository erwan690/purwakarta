package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.DataWarung;

/**
 * Created by aomcenter on 6/28/2017.
 */

public class WarungAdapter extends RecyclerView.Adapter<WarungViewHolder> {

    private List<DataWarung> mDataWarungs;
    private Context mContext;
    public WarungAdapter (Context context,List<DataWarung> dataWarungList)
    {
        this.mDataWarungs=dataWarungList;
        this.mContext=context;
    }


    @Override
    public WarungViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_warung, null);
        WarungViewHolder viewHolder = new WarungViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WarungViewHolder holder, int position) {
        DataWarung dataWarung = mDataWarungs.get(position);
        holder.jdWr.setText(dataWarung.getNamaWarung());
        holder.ketWr.setText(dataWarung.getDeskripsi());

        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/"+dataWarung.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.gmWr);

    }

    @Override
    public int getItemCount() {
        return mDataWarungs.size();
    }
}
