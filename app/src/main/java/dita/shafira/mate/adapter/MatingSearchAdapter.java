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
import dita.shafira.mate.Mating2Activity;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Cat;

public class MatingSearchAdapter extends RecyclerView.Adapter<MatingSearchAdapter.ViewHolder> {
    Context context;
    private ArrayList<Cat> cats;

    public MatingSearchAdapter(Context context) {
        this.context = context;
        this.cats= new ArrayList<>();
    }
    public static int calculateAge(String date) {
        LocalDate birthDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_cat_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.age.setText(calculateAge(cats.get(position).getBirth()) + "thn/" + cats.get(position).getRace().getTitle());
        holder.name.setText(cats.get(position).getName());
        holder.sex.setText("Auto laki");
        Glide.with(context)
                .load(cats.get(position).getPhotos1())
                .centerCrop()
                .into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "onBindViewHolder: dikik");
                Intent intent = new Intent(holder.itemView.getContext(), Mating2Activity.class);
                intent.putExtra("cat_id", cats.get(position).getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void setCats(ArrayList<Cat> list) {
        cats.clear();
        cats.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView photo;
        @BindView(R.id.textView14)
        TextView name;
        @BindView(R.id.textView15)
        TextView sex;
        @BindView(R.id.textView30)
        TextView age;
        @BindView(R.id.chat)
        ImageView chat;
        @BindView(R.id.like)
        ImageView like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
