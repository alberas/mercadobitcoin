package aulaandroid.com.mercadobitcoin.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by celsolago on 14/03/2018.
 */

public abstract class RetrofitConfig {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.mercadobitcoin.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static BitcoinService getService(){
        return retrofit.create(BitcoinService.class);
    }
}
