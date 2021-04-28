package dita.shafira.mate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.model.CatPhoto;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class CatPhotosAdapter extends RecyclerView.Adapter<CatPhotosAdapter.ViewHolder> {
    private ArrayList<CatPhoto> catPhotos;
    private Context context;
    private int size;

    public CatPhotosAdapter( Context context,ArrayList<CatPhoto> catPhotos) {
        this.catPhotos = new ArrayList<>();
        this.catPhotos.clear();
        this.catPhotos.addAll(catPhotos);
        this.context = context;
        size=catPhotos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_photo_cat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (size>position){
            Glide.with(context)
                    .load(BASE_URL_STORAGE+catPhotos.get(position).getPath())
                    .centerCrop()
                    .into(holder.photo);
        }else{
            holder.frame_add_photo.setVisibility(View.VISIBLE);
            holder.deleteIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (size == 4) {
            return 4;
        } else {
            return size + 1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.add_photo)
        ImageView frame_add_photo;
        @BindView(R.id.delete)
        ImageView deleteIcon;
        @BindView(R.id.photo)
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
