package dev.app.flikit.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import dev.app.flikit.data.BitmapCache;
import dev.app.flikit.data.DownloadImageTask;
import dev.app.flikit.data.URIBuilder;
import dev.app.flikit.model.Photo;

public class PhotoViewModel extends BaseObservable {

    private Photo photo;
    private Context mContext;
    private URIBuilder url;

    public PhotoViewModel(Photo photo, Context mContext) {
        this.photo = photo;
        this.mContext = mContext;
        this.url = new URIBuilder();
    }

    public String getPhoto() {
        return url.toPhotoURI(photo);
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        notifyChange();
    }

    @BindingAdapter("bind:imageUrl")
    public static void setImageUrl(final ImageView imageView, String url) {
        try {

            Bitmap bitmap = BitmapCache.getInstance().getBitmap(url);


            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                return;
            }

            new DownloadImageTask(new DownloadImageTask.Listener() {
                @Override
                public void onImageDownloaded(final Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }).download(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
