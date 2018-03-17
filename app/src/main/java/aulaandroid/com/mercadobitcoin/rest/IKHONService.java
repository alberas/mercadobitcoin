package aulaandroid.com.mercadobitcoin.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by celsolago on 14/03/2018.
 */

public interface IKHONService {


    @Headers({
            "Authorization: Basic dGVzdGU6dGVzdGU="
    })
    @GET("Arquivo/RetornarArquivo/{id}")
    Call<String> obterArquivo(@Path("id") String id);
}
