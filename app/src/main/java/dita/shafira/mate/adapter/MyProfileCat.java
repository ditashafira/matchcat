package dita.shafira.mate.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Cat;

public class MyProfileCat extends RecyclerView.Adapter<MyProfileCat.ViewHolder> {
    private Context context;
    private ArrayList<Cat> cats;

    public MyProfileCat(Context context) {
        this.context = context;
        this.cats = new ArrayList<>();
    }

    public void setCats(ArrayList<Cat> cats) {
        this.cats.clear();
        this.cats.addAll(cats);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(cats.get(position).getName());
        Glide.with(context)
                .load(cats.get(position).getPhotos1())
                .centerCrop()
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.catname)
        TextView name;
        @BindView(R.id.imageView10)
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
