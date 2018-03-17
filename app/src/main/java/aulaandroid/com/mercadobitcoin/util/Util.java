package aulaandroid.com.mercadobitcoin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class Util {

    /*
    Exemplo de uso
     @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        CameraResultado = Utils.getImageCameraOrGallery(data, this);

        if (CameraResultado != null) {
            image.setImageBitmap(CameraResultado.getPhotoBitmap());
        }

    }

     */


    public static boolean validate(Activity activity, int requestCode, String... permissions) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            boolean nok = ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
            if (nok) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            return true;
        }

        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        ActivityCompat.requestPermissions(activity, newPermissions, requestCode);

        return false;
    }
}
