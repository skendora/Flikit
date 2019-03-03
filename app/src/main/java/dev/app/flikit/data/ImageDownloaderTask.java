package dev.app.flikit.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    private HttpCallback callbackListener;

    @Override
    protected void onPreExecute() {
        this.callbackListener.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        HttpURLConnection connection = null;

        String url_string = params[0];

        try {
            URL url = new URL(url_string);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setDoInput(true);
            connection.connect();

            InputStream is = connection.getInputStream();

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            BitmapCache.getInstance().putBitmap(url_string, bitmap);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * execute after http response
     *
     * @param bitmap serialized response
     */
    @Override
    protected void onPostExecute(final Bitmap bitmap) {
        this.callbackListener.onPostExecute(bitmap);

    }


    public interface HttpCallback {
        void onPostExecute(Bitmap result);

        void onPreExecute();
    }

    public void setHttpListener(final HttpCallback callbackListener) {
        this.callbackListener = callbackListener;
    }
}