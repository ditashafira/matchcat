package dita.shafira.mate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.model.CatPhoto;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class PhotoCatAdapter extends RecyclerView.Adapter<PhotoCatAdapter.ViewHolder> {
    Context context;
    ArrayList<CatPhoto> catPhotos;

    public PhotoCatAdapter(Context context) {
        this.context = context;
        this.catPhotos=new ArrayList<>();
    }

    public void setCatPhotos(ArrayList<CatPhoto> catPhotos) {
        this.catPhotos.clear();
        this.catPhotos.addAll(catPhotos);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_pets_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(BASE_URL_STORAGE + catPhotos.get(position).getPath())
                .centerCrop()
                .into(holder.catPhoto);
    }

    @Override
    public int getItemCount() {
        return catPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cat_photo)
        ImageView catPhoto;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
