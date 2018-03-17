package aulaandroid.com.mercadobitcoin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aulaandroid.com.mercadobitcoin.R;
import aulaandroid.com.mercadobitcoin.model.Cotacao;
import aulaandroid.com.mercadobitcoin.rest.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner spMoeda;
    private String moeda;
    private TextView tvResultado;
    private TextView compra;
    private  List<String> listaMoeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaMoeda = new ArrayList<String>();

        listaMoeda.add("BTC");
        listaMoeda.add("LTC");
        listaMoeda.add("BCH");

        spMoeda = findViewById(R.id.moeda);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaMoeda);

        spMoeda.setAdapter(arrayAdapter);

        spMoeda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                moeda = listaMoeda.get(i);
                Toast.makeText(MainActivity.this, moeda, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickSalvar(View view){

        tvResultado = findViewById(R.id.resultado);
        compra = findViewById(R.id.compra);

        Call<Cotacao> result = RetrofitConfig.getService().obterCotacao(moeda);

        result.enqueue(new Callback<Cotacao>(){
            @Override
            public void onResponse(Call<Cotacao> call, Response<Cotacao> response){
                tvResultado.setText(response.body().toString());
                compra.setText(response.body().valorCompra());

            }

            @Override
            public void onFailure(Call<Cotacao> call, Throwable t){
                Toast.makeText(getBaseContext(), "Erro ao buscar cotação", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
