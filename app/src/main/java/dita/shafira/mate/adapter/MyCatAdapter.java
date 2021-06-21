package dita.shafira.mate.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import dita.shafira.mate.feature.cat.mating.Mating4Activity;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Cat;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class MyCatAdapter extends RecyclerView.Adapter<MyCatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cat> cats;

    public MyCatAdapter(Context context) {
        this.context = context;
        this.cats = new ArrayList<Cat>();
    }

    public void setCats(ArrayList<Cat> cats) {
        this.cats.clear();
        this.cats.addAll(cats);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_cat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(cats.get(position).getName());
        if (cats.get(position).getSex() == 1) {
            holder.sex.setText("Jantan");
        } else {
            holder.sex.setText("Betina");
        }
        holder.race.setText(cats.get(position).getRace().getTitle());
        holder.age.setText(calculateAge(cats.get(position).getBirth()));
        Glide.with(context)
                .load(BASE_URL_STORAGE+cats.get(position).getPhoto())
                .centerCrop()
                .into(holder.photo);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), Mating4Activity.class);
            intent.putExtra("cat_id", cats.get(position).getId());
            Log.d("TAGDi", "onBindViewHolder: "+cats.get(position).getId());
            holder.itemView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView photo;
        @BindView(R.id.textView14)
        TextView name;
        @BindView(R.id.textView15)
        TextView sex;
        @BindView(R.id.textView16)
        TextView race;
        @BindView(R.id.age)
        TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
