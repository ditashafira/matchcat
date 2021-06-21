package dita.shafira.mate.adapter;

import android.content.Context;
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
import dita.shafira.mate.model.Mating;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mating> matings ;

    public HistoryAdapter(Context context) {
        this.context = context;
        matings = new ArrayList<>();
    }

    public void setMatings(ArrayList<Mating> matings) {
        this.matings.clear();
        this.matings.addAll(matings);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_mating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCatName1.setText(matings.get(position).getCat1().getName());
        holder.tvCatName2.setText(matings.get(position).getCat2().getName());
        if (matings.get(position).getCat1().getSex() == 1) {
            holder.tvCatSex1.setText("Jantan");
        } else {
            holder.tvCatSex1.setText("Betina");
        }
        if (matings.get(position).getCat2().getSex() == 1) {
            holder.tvCatSex2.setText("Jantan");
        } else {
            holder.tvCatSex2.setText("Betina");
        }
        holder.tvCatRace1.setText(matings.get(position).getCat1().getRace().getTitle());
        holder.tvCatRace2.setText(matings.get(position).getCat2().getRace().getTitle());
        Glide.with(context)
                .load(BASE_URL_STORAGE+matings.get(position).getCat1().getPhoto())
                .centerCrop()
                .into(holder.photoCat1);
        Glide.with(context)
                .load(BASE_URL_STORAGE+matings.get(position).getCat2().getPhoto())
                .centerCrop()
                .into(holder.photoCat2);
    }

    @Override
    public int getItemCount() {
        return matings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cat_name_1)
        TextView tvCatName1;
        @BindView(R.id.tv_cat_name_2)
        TextView tvCatName2;
        @BindView(R.id.tv_cat_sex_1)
        TextView tvCatSex1;
        @BindView(R.id.tv_cat_sex_2)
        TextView tvCatSex2;
        @BindView(R.id.tv_cat_race_1)
        TextView tvCatRace1;
        @BindView(R.id.tv_cat_race_2)
        TextView tvCatRace2;
        @BindView(R.id.photo_cat_1)
        ImageView photoCat1;
        @BindView(R.id.photo_cat_2)
        ImageView photoCat2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
