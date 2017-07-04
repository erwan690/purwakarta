package purwakarta.kota.kuliner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailWarung {



    @SerializedName("id_warung")
    @Expose
    private String idWarung;
    @SerializedName("namawarung")
    @Expose
    private String namawarung;
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
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("menu")
    @Expose
    private String menu;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("distance")
    @Expose
    private Double distance;

    public String getIdWarung() {
        return idWarung;
    }

    public void setIdWarung(String idWarung) {
        this.idWarung = idWarung;
    }

    public String getNamawarung() {
        return namawarung;
    }

    public void setNamawarung(String namawarung) {
        this.namawarung = namawarung;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


}