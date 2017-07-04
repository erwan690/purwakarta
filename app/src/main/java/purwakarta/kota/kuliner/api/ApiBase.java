package purwakarta.kota.kuliner.api;

/**
 * Created by aomcenter on 6/27/2017.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBase {
    //private static final String BASE_URL = "http://192.168.210.1/";
    //private static final String BASE_URL = "http://192.168.56.1/";
    private static final String BASE_URL = "http://kulinerpwk.id/";
    //private static final String BASE_URL = "http://192.168.100.250/";
    private static Retrofit retrofit = null;

    /**
     * @return retrofit
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
