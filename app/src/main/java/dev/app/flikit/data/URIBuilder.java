package dev.app.flikit.data;

import dev.app.flikit.model.Photo;

public class URIBuilder {

    private static final String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736" +
            "&format=json&nojsoncallback=1&safe_search=1";



    private int page;
    private String query;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString()
    {
        String url = BASE_URL.concat("&text=" + query).concat("&page=" + page);
        return url;
    }

    public String toPhotoURI(Photo photo)
    {
        return "http://farm".concat(String.valueOf(photo.getFarm())).concat(".static.flickr.com/")
                .concat(photo.getServer()).concat("/").concat(photo.getId())
                .concat("_").concat(photo.getSecret()).concat(".jpg");

    }
}
