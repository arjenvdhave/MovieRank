package com.movierank.arjen.movierank.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by arjen on 2/26/16.
 */
    public class ImageDownloadTask extends AsyncTask<String,Void,Bitmap> {


    public interface Callback{
        void onBitmapLoaded(Bitmap bitmap);
    }

    Callback callback;

    public ImageDownloadTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String posterUrlString = params[0];
        if( posterUrlString == null)
            return null;
        Bitmap ret = null;
        try {
            URL posterUrl = new URL(posterUrlString);
            ret = BitmapFactory.decodeStream(posterUrl.openConnection().getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ret;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.callback.onBitmapLoaded(bitmap);
    }
}
