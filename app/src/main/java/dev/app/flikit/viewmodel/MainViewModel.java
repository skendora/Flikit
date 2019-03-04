package dev.app.flikit.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import androidx.databinding.ObservableInt;
import dev.app.flikit.data.ApiResponse;
import dev.app.flikit.data.HTTPGetRequestTask;
import dev.app.flikit.data.URIBuilder;
import dev.app.flikit.model.Photo;

import java.util.List;
import java.util.Observable;

public class MainViewModel extends Observable {

    private Context mContext;
    public ObservableInt progress;
    public ObservableInt view;
    private ApiResponse responses;
    private List<Photo> photoList;
    private URIBuilder url;
    private boolean isLoading;

    public MainViewModel(Context mContext, ApiResponse response, List<Photo> photoList, URIBuilder url) {
        this.mContext = mContext;
        this.responses = response;
        this.photoList = photoList;
        this.url = url;
        this.progress = new ObservableInt(View.GONE);
        this.view = new ObservableInt(View.VISIBLE);
        getPhotos();

    }

    public List<Photo> getPhotoList() {
        return this.photoList;
    }

    private void getPhotos() {
        try {
            HTTPGetRequestTask http = new HTTPGetRequestTask();

            http.setHttpListener(new HTTPGetRequestTask.HttpCallback() {
                @Override
                public void onPostExecute(ApiResponse response, int statusCode) {

                    isLoading = false;
                    progress.set(View.GONE);

                    if (response != null) {
                        responses.setPage(response.getPage());
                        responses.setPages(response.getPages());

                        if (statusCode == 200) {
                            photoList.addAll(response.getPhoto());
                            setChanged();
                            notifyObservers();
                        } else {
                            Toast.makeText(mContext, "Failed to Fetch", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Invalid Response", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onPreExecute() {

                    isLoading = true;
                    progress.set(View.VISIBLE);
                }
            });
            http.execute(url.toString());
            //http.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        responses.setPage(0);
        responses.setPages(0);
        photoList.clear();
        setChanged();
        notifyObservers();
    }

    public boolean onQueryTextSubmit(String query) {
        reset();
        url.setQuery(query);

        getPhotos();

        return false;
    }

    public boolean onQueryTextChange(String query) {
        return false;
    }

    public void onGridScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem + visibleItemCount >= totalItemCount) {

            if (!isLoading && responses.getPage() < responses.getPages() - 1) {//Check this logic for endless scrolling
                this.url.setPage(responses.getPage() + 1);
                //Log.d("Flikit", "PAGE = " + responses.getPage());

                getPhotos();

            }
        }
    }
}
