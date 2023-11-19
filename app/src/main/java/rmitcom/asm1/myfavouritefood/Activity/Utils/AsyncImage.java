package rmitcom.asm1.myfavouritefood.Activity.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

// ref: https://stackoverflow.com/questions/5776851/load-image-from-url

public class AsyncImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public AsyncImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String URL = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(URL).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }


    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
