package aulaandroid.com.mercadobitcoin.model;

/**
 * Created by celsolago on 14/03/2018.
 */

public class Cotacao {

    private Ticker ticker;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public String toString(){
        return Float.toString(ticker.getSell());

    }

    public String valorCompra(){
        return Float.toString(ticker.getBuy());

    }
}
