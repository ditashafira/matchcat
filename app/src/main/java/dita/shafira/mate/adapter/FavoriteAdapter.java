package dita.shafira.mate.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.cat.mating.Mating5Activity;
import dita.shafira.mate.feature.chat.ChatActivity;
import dita.shafira.mate.model.CatSearch;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.adapter.MatingAdapter.catId;
import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private final ArrayList<Mating> cats;
    Context context;
    int userId;

    public FavoriteAdapter(Context context,int catId) {
        this.context = context;
        this.cats = new ArrayList<>();
        dita.shafira.mate.adapter.MatingAdapter.catId=catId;
        userId = Integer.parseInt(MyApp.db.userDao().user().get(0).getId());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_cat_2, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.age.setText(calculateAge(cats.get(position).getCat2().getBirth()));
        holder.name.setText(cats.get(position).getCat2().getName());
        holder.sex.setText(cats.get(position).getCat2().getRace().getTitle());
        Glide.with(context)
                .load(BASE_URL_STORAGE + cats.get(position).getCat2().getPhoto())
                .centerCrop()
                .into(holder.photo);
        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(holder.itemView.getContext(), Mating2Activity.class);
//            intent.putExtra("cat_id", cats.get(position).getId());
//            holder.itemView.getContext().startActivity(intent);
        });
        holder.like.setOnClickListener(v -> {
            Call<Response> call = Service.getInstance().getApi().catMating(dita.shafira.mate.adapter.MatingAdapter.catId, cats.get(position).getCatId2(), 3, 0);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Mating5Activity.class);
            intent.putExtra("catId", cats.get(position).getCat2().getId());
            holder.itemView.getContext().startActivity(intent);
        });
        holder.chat.setOnClickListener(v -> {
            Call<Response> call = Service.getInstance().getApi().catMating(dita.shafira.mate.adapter.MatingAdapter.catId, cats.get(position).getCatId2(), 0, 1);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Call<Response> call2 = Service.getInstance().getApi().catMating(dita.shafira.mate.adapter.MatingAdapter.catId, cats.get(position).getCatId2(), 0, 1);
                    call2.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
                            User user = MyApp.db.userDao().user().get(0);
                            boolean user1=true;
                            intent.putExtra("mating", response.body().getMating());
                            intent.putExtra("user1", user1);
                            holder.itemView.getContext().startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });

        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void setCats(ArrayList<Mating> list) {
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
