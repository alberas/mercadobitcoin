package aulaandroid.com.mercadobitcoin.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

import aulaandroid.com.mercadobitcoin.R;
import aulaandroid.com.mercadobitcoin.rest.IKHONConfig;
import aulaandroid.com.mercadobitcoin.rest.IKHONService;
import aulaandroid.com.mercadobitcoin.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArquivoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arquivo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                alertarMensagem();
                return;
            }
        }
        if (requestCode == 0) {

        }
    }

    private void alertarMensagem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Confirma as permissoes?")
                .setNegativeButton("NAO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Util.validate(ArquivoActivity.this, 0, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE});
            }
        });
        builder.create().show();

    }

    public void onClickSalvar(View view){

        if (Util.validate(ArquivoActivity.this, 0, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE})) {

            Call<String> result = IKHONConfig.getService().obterArquivo("3847537");

            result.enqueue(new Callback<String>(){
                @Override
                public void onResponse(Call<String> call, Response<String> response){

                    try {
                        //final File dwldsPath = new java.io.File((ArquivoActivity.this
                        //     .getApplicationContext().getFileStreamPath("Documento.pdf")
                        //        .getPath()));

                        final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/documento.pdf");
                        byte[] pdfAsBytes = Base64.decode(response.body().toString(), 0);
                        FileOutputStream os;
                        os = new FileOutputStream(dwldsPath, false);
                        os.write(pdfAsBytes);
                        os.flush();
                        os.close();

                        Intent target = new Intent(Intent.ACTION_VIEW);
                        Uri fileURI = FileProvider.getUriForFile(ArquivoActivity.this, ArquivoActivity.this.getApplicationContext().getPackageName() + ".aulaandroid.com.mercadobitcoin", dwldsPath);
                        target.setDataAndType(fileURI,"application/pdf");
                        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        Intent intent = Intent.createChooser(target, "Open File");
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Instruct the user to install a PDF reader here, or som
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t){
                    Toast.makeText(getBaseContext(), "Erro ao buscar cotação", Toast.LENGTH_SHORT).show();
                }
            });
        }
        }

}
