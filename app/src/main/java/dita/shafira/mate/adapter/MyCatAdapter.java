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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.CompleteCatBiodataActivity;
import dita.shafira.mate.Mating2Activity;
import dita.shafira.mate.Mating4Activity;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Cat;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

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

    public static int calculateAge(String date) {
        LocalDate birthDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getMonths();
        } else {
            return 0;
        }
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
        holder.age.setText(calculateAge(cats.get(position).getBirth()) + "thn/" + cats.get(position).getRace().getTitle());
        Glide.with(context)
                .load(BASE_URL_STORAGE+cats.get(position).getPhoto())
                .centerCrop()
                .into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Mating4Activity.class);
                intent.putExtra("cat_id", cats.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
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
        TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
