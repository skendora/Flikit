package dev.app.flikit.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import dev.app.flikit.data.ApiResponse;
import dev.app.flikit.data.URIBuilder;
import dev.app.flikit.model.Photo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainViewModelUnitTest {

    private MainViewModel mainViewModel;

    @Before
    public void setUpViewModel()
    {
        this.mainViewModel = new MainViewModel(new AppCompatActivity(),new ApiResponse(), new ArrayList<Photo>(), new URIBuilder());
    }

    @Test
    public void isNotNull()
    {
        assertNotNull(mainViewModel);
    }

    @Test
    public void getPhotoList()
    {
        assertNotNull(mainViewModel.getPhotoList());
    }

    @Test
    public void onQueryTextSubmit()
    {
        assertFalse(mainViewModel.onQueryTextSubmit(""));
    }

    @Test
    public void onQueryTextChange()
    {
        assertFalse(mainViewModel.onQueryTextChange(""));
    }
}
