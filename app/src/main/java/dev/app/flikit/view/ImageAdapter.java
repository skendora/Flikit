package dev.app.flikit.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import dev.app.flikit.BR;
import dev.app.flikit.R;
import dev.app.flikit.model.Photo;
import dev.app.flikit.viewmodel.MainViewModel;
import dev.app.flikit.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Photo> photoList;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        public void bind(Photo data) {

            this.binding.setVariable(BR.photoItemVM, data);
            this.binding.executePendingBindings();

        }
    }

    public ImageAdapter(ArrayList<Photo> lists) {
        this.photoList = lists;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_photo, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.bind(photo);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
