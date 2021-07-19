package dita.shafira.mate.feature.cat.mating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.PhotoCatAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.chat.ChatActivity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatPhoto;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dita.shafira.mate.adapter.MatingAdapter.catId;
import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class Mating5Activity extends AppCompatActivity {

    @BindView(R.id.mainPhoto)
    ImageView mainPhoto;
    @BindView(R.id.cat_target_name)
    TextView catName;
    @BindView(R.id.cat_target_location)
    TextView catLocation;
    @BindView(R.id.cat_age_race)
    TextView catAgeRace;
    @BindView(R.id.cat_sex)
    TextView catSex;
    @BindView(R.id.cat_vaksin)
    TextView catVaksin;
    @BindView(R.id.cat_parasite)
    TextView catParasite;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.divider2)
    View view;
    PhotoCatAdapter adapter;
    Cat cat;
    Context context;
    private void setData(){
        Glide.with(context)
                .load(BASE_URL_STORAGE + cat.getPhoto())
                .centerCrop()
                .into(mainPhoto);
        catName.setText(cat.getName());
        catAgeRace.setText(calculateAge(cat.getBirth())+"/"+cat.getRace().getTitle());
        if (cat.getSex() == 1) {
            catSex.setText("Jantan");
        } else {
            catSex.setText("Betina");
        }
        if (cat.getLastVaccine()!=null) {
            catVaksin.setText(cat.getLastVaccine());
        }
        catParasite.setText(cat.getLastParasite());
        if (cat.getPhotos().size()==0){
            view.setVisibility(View.GONE);
        }
        adapter.setCatPhotos((ArrayList<CatPhoto>) cat.getPhotos());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_5);
        ButterKnife.bind(this);
        context=this;
        adapter= new PhotoCatAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
        Call<Cat> call = Service.getInstance().getApi().catMeDetail(MyApp.db.userDao().user().get(0).getId(),String.valueOf(getIntent().getIntExtra("catId",0)));
        call.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                cat=response.body();
                setData();
            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_fav)
    void setFav(View view){
        Call<dita.shafira.mate.model.Response> call = Service.getInstance().getApi().catMating(catId, cat.getId(), 3, 0);
        call.enqueue(new Callback<dita.shafira.mate.model.Response>() {
            @Override
            public void onResponse(Call<dita.shafira.mate.model.Response> call, retrofit2.Response<dita.shafira.mate.model.Response> response) {
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.btn_send)
    void setSend(View view){
        Call<dita.shafira.mate.model.Response> call = Service.getInstance().getApi().catMating(catId, cat.getId(), 0, 1);
        call.enqueue(new Callback<dita.shafira.mate.model.Response>() {
            @Override
            public void onResponse(Call<dita.shafira.mate.model.Response> call, retrofit2.Response<dita.shafira.mate.model.Response> response) {
                Call<dita.shafira.mate.model.Response> call2 = Service.getInstance().getApi().catMating(catId, cat.getId(), 0, 1);
                call2.enqueue(new Callback<dita.shafira.mate.model.Response>() {
                    @Override
                    public void onResponse(Call<dita.shafira.mate.model.Response> call, retrofit2.Response<dita.shafira.mate.model.Response> response) {
                        Intent intent = new Intent(context, ChatActivity.class);
                        boolean user1=true;
                        intent.putExtra("mating", response.body().getMating());
                        intent.putExtra("user1", user1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {

            }
        });
    }
}