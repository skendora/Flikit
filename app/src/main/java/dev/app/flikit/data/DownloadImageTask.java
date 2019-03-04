package dev.app.flikit.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = DownloadImageTask.class.getName();


    private Listener listener;


    public DownloadImageTask(final Listener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        final String url = urls[0];
        Bitmap bitmap = null;

        try {

            final InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            BitmapCache.getInstance().putBitmap(url, bitmap);

        } catch (final MalformedURLException malformedUrlException) {
            Log.e(TAG, "Failed to download malformed URL " + url
                    + malformedUrlException.getMessage());
        } catch (final IOException ioException) {
            Log.e(TAG, "Failed to download URL " + url
                    + ioException.getMessage());
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        listener.onImageDownloaded(result);
    }


    public void download(final String... urls) {
        executeOnExecutor(THREAD_POOL_EXECUTOR, urls);
    }


    public interface Listener {

        void onImageDownloaded(final Bitmap bitmap);
    }
}
