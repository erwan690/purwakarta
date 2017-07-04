package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 6/27/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Kategori {

    @SerializedName("kategoris")
    @Expose
    private List<DataKategori> kategoris = new ArrayList<DataKategori>();

    public List<DataKategori> getKategoris() {
        return kategoris;
    }

    public void setKategoris(List<DataKategori> kategoris) {
        this.kategoris = kategoris;
    }

}