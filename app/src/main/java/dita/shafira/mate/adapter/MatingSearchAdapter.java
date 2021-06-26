package dita.shafira.mate.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
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
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.adapter.MatingAdapter.catId;
import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class MatingSearchAdapter extends RecyclerView.Adapter<MatingSearchAdapter.ViewHolder> {
    private final ArrayList<CatSearch> cats;
    Context context;
    int userId;

    public MatingSearchAdapter(Context context) {
        this.context = context;
        this.cats = new ArrayList<>();
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
        holder.age.setText(calculateAge(cats.get(position).getBirth()));
        holder.name.setText(cats.get(position).getName());
        holder.sex.setText(cats.get(position).getRace());
        Glide.with(context)
                .load(BASE_URL_STORAGE + cats.get(position).getPhoto())
                .centerCrop()
                .into(holder.photo);
        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(holder.itemView.getContext(), Mating2Activity.class);
//            intent.putExtra("cat_id", cats.get(position).getId());
//            holder.itemView.getContext().startActivity(intent);
        });
        holder.like.setOnClickListener(v -> {
            Call<Response> call = Service.getInstance().getApi().catMating(catId, cats.get(position).getId(), 3, 0);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Mating5Activity.class);
                intent.putExtra("cat_target", (Parcelable) cats.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.chat.setOnClickListener(v -> {
            Call<Response> call = Service.getInstance().getApi().catMating(catId, cats.get(position).getId(), 0, 1);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Call<Response> call2 = Service.getInstance().getApi().catMating(catId, cats.get(position).getId(), 0, 1);
                    call2.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
                            User user = MyApp.db.userDao().user().get(0);
                            Log.d("TAGD", "onResponse: "+response.body().getMating().getId());

                            boolean user1=true;
                            intent.putExtra("chatRoom", response.body().getMating().getId());
                            intent.putExtra("user1", user1);
                            intent.putExtra("status_mate", response.body().getMating().getStatusMate());
                            intent.putExtra("user2lat", response.body().getMating().getCat1().getUser().getLatitude());
                            intent.putExtra("user2long", response.body().getMating().getCat1().getUser().getLongitude());
                            intent.putExtra("user1lat", response.body().getMating().getCat2().getUser().getLatitude());
                            intent.putExtra("user1long", response.body().getMating().getCat2().getUser().getLongitude());
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

    public void setCats(ArrayList<CatSearch> list) {
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
