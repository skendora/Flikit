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

    /**
     * The client of this task that is waiting for the image to be
     * obtained.
     */
    private Listener listener;

    /**
     * Constructs a new DownloadImageTask with a provided callback
     * listener.
     *
     * @param listener the client that is listening for a callback when
     *                 the image becomes available.
     */
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

    /**
     * Downloads image resources from a list of URLs concurrently.
     *
     * @param urls URLs to the images to download
     */
    public void download(final String... urls) {
        executeOnExecutor(THREAD_POOL_EXECUTOR, urls);
    }

    /**
     * The Listener of a DownloadImageTask will receive a notification
     * when the image becomes available.
     * <p>
     * TODO: provide an error callback.
     */
    public interface Listener {
        /**
         * Called when the DownloadImageTask has successfully downloaded
         * an image.
         *
         * @param bitmap the image that has been downloaded.
         */
        void onImageDownloaded(final Bitmap bitmap);
    }
}
