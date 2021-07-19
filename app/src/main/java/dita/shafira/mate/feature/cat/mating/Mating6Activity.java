package dita.shafira.mate.feature.cat.mating;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.PhotoCatAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatPhoto;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class Mating6Activity extends AppCompatActivity {

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
    @BindView(R.id.divider2)
    View view;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
        catVaksin.setText(cat.getLastVaccine());
        catVaksin.setText(cat.getLastParasite());
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            if (cat.getUser().getLatitude()!=null && cat.getUser().getLongitude()!=null) {
                addresses = geocoder.getFromLocation(Double.parseDouble(cat.getUser().getLatitude()), Double.parseDouble(cat.getUser().getLongitude()), 1);
                String cityName = addresses.get(0).getSubAdminArea();
                if (cityName != null) {
                    catLocation.setText(cityName);
                }else{
                    catLocation.setText("-");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.setCatPhotos((ArrayList<CatPhoto>) cat.getPhotos());
        if (cat.getPhotos().size()==0){
            view.setVisibility(View.GONE);
        }


    }
    PhotoCatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_6);
        ButterKnife.bind(this);
        context=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        adapter= new PhotoCatAdapter(context);
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
}