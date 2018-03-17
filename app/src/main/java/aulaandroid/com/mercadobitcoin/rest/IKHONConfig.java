package aulaandroid.com.mercadobitcoin.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by celsolago on 14/03/2018.
 */

public abstract class IKHONConfig {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ana-d.ikhon.com.br/acessoarquivo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IKHONService getService(){
        return retrofit.create(IKHONService.class);
    }
}
