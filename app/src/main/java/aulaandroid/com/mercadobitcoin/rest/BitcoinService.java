package aulaandroid.com.mercadobitcoin.rest;

import aulaandroid.com.mercadobitcoin.model.Cotacao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by celsolago on 14/03/2018.
 */

public interface BitcoinService {

    @GET("{moeda}/ticker")
    Call<Cotacao> obterCotacao(@Path("moeda") String moeda);
}
