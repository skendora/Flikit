package dev.app.flikit.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.databinding.DataBindingUtil;
import dev.app.flikit.R;
import dev.app.flikit.databinding.ItemPhotoBinding;
import dev.app.flikit.model.Photo;
import dev.app.flikit.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

class PhotoAdapter extends BaseAdapter {

    private Context mContext;
    private List<Photo> photoList;
    private ItemPhotoBinding binding;

    /**
     * Constructor to initialize / instantiate all variable / classes
     * @param context application context
     * @param photoList photo list instance
     */
    public PhotoAdapter(Context context, List<Photo> photoList)
    {
        mContext = context;
        this.photoList = photoList;
    }

    /**
     * Overridden method of BaseAdapter
     * @return return photolist size
     */
    @Override
    public int getCount()
    {
        return photoList.size();
    }

    /**
     * Overridden method of BaseAdapter
     * @return photo on specified position
     */
    @Override
    public Photo getItem(int position)
    {
        return this.photoList.get(position);
    }

    /**
     * Overridden method of BaseAdapter
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    /**
     * clear photo list and add all the items
     * notify adapter changes
     */
    public void setPhotoList(List<Photo> photoList)
    {
        this.photoList.clear();
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }

    public List<Photo> getPhotoList()
    {
        return this.photoList;
    }

    /**
     * overridden method of BaseAdapter
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_photo, null);
            binding = DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        }

        else
        {
            binding = (ItemPhotoBinding) convertView.getTag();
        }

        if (binding.getPhotoItemVM() == null)
        {
            binding.setPhotoItemVM(new PhotoViewModel(photoList.get(position),convertView.getContext()));
        }

        else
        {
            binding.getPhotoItemVM().setPhoto(photoList.get(position));
        }

        return binding.getRoot();
    }
}
