package dita.shafira.mate.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.feature.cat.EditCatActivity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatPhoto;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class CatPhotosAdapter extends RecyclerView.Adapter<CatPhotosAdapter.ViewHolder> {
    private ArrayList<CatPhoto> catPhotos;
    private Context context;
    private int size;
    private Cat catId;

    public CatPhotosAdapter( Context context,Cat cat) {
        this.catPhotos = new ArrayList<>();
        this.context = context;
        this.catId=cat;

    }

    public void setCatPhotos(ArrayList<CatPhoto> catPhotos) {
        this.catPhotos.clear();
        this.catPhotos.addAll(catPhotos);
        size=catPhotos.size();
        Log.d("TAGd", "setCatPhotos: "+size);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_photo_cat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context)
                    .load(BASE_URL_STORAGE+catPhotos.get(position).getPath())
                    .centerCrop()
                    .into(holder.photo);
            holder.deleteIcon.setOnClickListener(v -> {
                Call<Response>call = Service.getInstance().getApi().catRemovePhoto(catPhotos.get(position).getPath());
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, EditCatActivity.class);
                        intent.putExtra("cat",catId);
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            });

    }

    @Override
    public int getItemCount() {
        return size;
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
