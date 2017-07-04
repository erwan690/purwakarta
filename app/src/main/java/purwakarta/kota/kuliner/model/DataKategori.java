package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 6/27/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKategori {

    @SerializedName("id_kategori")
    @Expose
    private String idKategori;
    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}