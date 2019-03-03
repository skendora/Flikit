package dev.app.flikit.view;

import androidx.appcompat.app.AppCompatActivity;
import dev.app.flikit.model.Photo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhotoAdapterUnitTest {

    private PhotoAdapter photoAdapter;

    @Before
    public void setUpAdapter() {
        this.photoAdapter = new PhotoAdapter(new AppCompatActivity(), new ArrayList<Photo>());
    }

    @Test
    public void getCount() {
        assertEquals(0, photoAdapter.getPhotoList().size());

        photoAdapter.getPhotoList().add(new Photo());

        assertEquals(1, photoAdapter.getPhotoList().size());
    }


    @Test
    public void getItem() {
        photoAdapter.getPhotoList().add(new Photo());
        photoAdapter.getPhotoList().add(new Photo());

        assertNotNull(photoAdapter.getItem(1));
    }
}
