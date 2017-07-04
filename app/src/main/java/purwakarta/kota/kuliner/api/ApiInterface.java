package purwakarta.kota.kuliner.api;

import java.util.List;

import purwakarta.kota.kuliner.model.DetailWarung;
import purwakarta.kota.kuliner.model.GalerryWarung;
import purwakarta.kota.kuliner.model.Kategori;
import purwakarta.kota.kuliner.model.Warung;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {
    @GET("maranggi/api/getkategori")
    Call<Kategori> getKategori();

    @GET("maranggi/api/getwarung/{id}")
    Call<Warung> getWarung(@Path("id") String id );

    @GET("maranggi/api/getdetail/{id}")
    Call<List<DetailWarung>> getDetailna(@Path("id") String id );

    @GET("maranggi/api/getgaleri/{id}")
    Call<List<GalerryWarung>> getGalerina(@Path("id") String id );

    @GET("maranggi/api/getallwarung")
    Call<List<DetailWarung>> getAllWarung();
}
