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
import dita.shafira.mate.feature.cat.EditCatActivity;
import dita.shafira.mate.feature.cat.ListCatActivity;
import dita.shafira.mate.feature.cat.WishListActivity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatPhoto;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class Mating4Activity extends AppCompatActivity {gu

    @BindView(R.id.catname)
    TextView catname;
    @BindView(R.id.catage)
    TextView catage;
    @BindView(R.id.catsex)
    TextView catsex;
    @BindView(R.id.catvaccine)
    TextView catvaccine;
    @BindView(R.id.catparasite)
    TextView catparasite;
    @BindView(R.id.mainPhoto)
    ImageView mainphoto;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.divider2)
            View view;
    PhotoCatAdapter adapter;

    Context context;
    Cat cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_4);
        ButterKnife.bind(this);
        context=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        adapter= new PhotoCatAdapter(context);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        User user = MyApp.db.userDao().user().get(0);
        int catId = intent.getIntExtra("cat_id",0);
        Call<Cat> call = Service.getInstance().getApi().catMeDetail(user.getId(), String.valueOf(catId));
        call.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                cat = response.body();
                catname.setText(cat.getName());
                Glide.with(getBaseContext())
                        .load(BASE_URL_STORAGE + cat.getPhoto())
                        .centerCrop()
                        .into(mainphoto);
                catage.setText(calculateAge(cat.getBirth())+" / "+cat.getRace().getTitle());
                if (cat.getSex() == 1) {
                    catsex.setText("Jantan");
                } else {
                    catsex.setText("Betina");
                }
                if (cat.getLastParasite()==null){
                    catparasite.setText(" - ");
                }else{
                    catparasite.setText(cat.getLastParasite());
                }
                if (cat.getLastVaccine()==null){
                    catvaccine.setText(" - ");
                }else{
                    catvaccine.setText(cat.getLastVaccine());
                }
                if (cat.getPhotos().size()==0){
                    view.setVisibility(View.GONE);
                }
                adapter.setCatPhotos((ArrayList<CatPhoto>) cat.getPhotos());

            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {

            }
        });
    }


    @OnClick(R.id.btnOutline_2)
    void setDelete(View view){
        Call<dita.shafira.mate.model.Response> call = Service.getInstance().getApi().catDelete(cat.getId());
        call.enqueue(new Callback<dita.shafira.mate.model.Response>() {
            @Override
            public void onResponse(Call<dita.shafira.mate.model.Response> call, Response<dita.shafira.mate.model.Response> response) {
                Toast.makeText(getBaseContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), ListCatActivity.class);
                Mating4Activity.this.finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }

    @OnClick(R.id.btnOutline_1)
    void setbutton(View text) {
        Intent intent = new Intent(this, EditCatActivity.class);
        intent.putExtra("cat",cat);
        startActivity(intent);
    }
    @OnClick(R.id.btnOutline_3)
    void setFavorite(View text) {
        Intent intent = new Intent(this, WishListActivity.class);
        Log.d("Taga", "onCreate: "+getIntent().getStringExtra("catId"));
        intent.putExtra("catId", cat.getId());
        startActivity(intent);
    }

    }