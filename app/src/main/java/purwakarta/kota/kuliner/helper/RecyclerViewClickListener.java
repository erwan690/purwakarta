package purwakarta.kota.kuliner.helper;

/**
 * Created by aomcenter on 6/27/2017.
 */

import android.view.View;

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}