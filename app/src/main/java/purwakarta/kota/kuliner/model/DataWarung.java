package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 6/28/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWarung {

    @SerializedName("id_warung")
    @Expose
    private String idWarung;
    @SerializedName("id_kategori")
    @Expose
    private String idKategori;
    @SerializedName("nama_warung")
    @Expose
    private String namaWarung;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdWarung() {
        return idWarung;
    }

    public void setIdWarung(String idWarung) {
        this.idWarung = idWarung;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaWarung() {
        return namaWarung;
    }

    public void setNamaWarung(String namaWarung) {
        this.namaWarung = namaWarung;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}