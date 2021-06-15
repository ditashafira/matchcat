package dita.shafira.mate.feature.cat.mating;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.slider.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Cat;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;
import static dita.shafira.mate.util.Helper.calculateAgeMonth;

public class Mating2Activity extends AppCompatActivity {
    @BindView(R.id.seekBar)
    Slider seekBar;
    @BindView(R.id.imageView16)
    ImageView photo;
    @BindView(R.id.textView20)
    TextView name;
    @BindView(R.id.textView21)
    TextView sex;
    @BindView(R.id.textView34)
    TextView age;
    @BindView(R.id.textView35)
    TextView race;
    @BindView(R.id.textView29)
    TextView distance;
    @BindView(R.id.switch1)
    SwitchCompat filter;
    int searchVaccine;
    int searchParasite;
    int searchDistance;
    int searchSex;
    int searchAge;
    int searchRace;
    boolean searchFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_2);
        ButterKnife.bind(this);
        Cat cat = getIntent().getExtras().getParcelable("cat_id");
        Glide.with(this)
                .load(BASE_URL_STORAGE+cat.getPhoto())
                .centerCrop()
                .into(photo);
        Log.d("TAG", "onCreate: "+cat.getBirth());
        age.setText(calculateAge(cat.getBirth()));

        name.setText(cat.getName());
        if (cat.getSex() == 1) {
            sex.setText("Jantan");
        } else {
            sex.setText("Betina");
        }
        age.setText(calculateAge(cat.getBirth()));
        race.setText(cat.getRace().getTitle());
        Glide.with(this)
                .load(BASE_URL_STORAGE + cat.getPhoto())
                .centerCrop()
                .into(photo);
        seekBar.addOnChangeListener((slider, value, fromUser) -> {
            distance.setText((int) value + " km");
            searchDistance= (int) value;
            searchAge=calculateAgeMonth(cat.getBirth());
            searchSex=cat.getSex();
            searchRace=cat.getRaceId();
            searchFilter=filter.isChecked();
        });

    }

    @OnClick(R.id.btnLightSolid)
    void setBtnLightSolid(View Lightsolid) {
        Intent intent = new Intent(this, Mating3Activity.class);
        intent.putExtra("searchDistance",searchDistance);
        intent.putExtra("searchAge",searchAge);
        intent.putExtra("searchSex",searchSex);
        intent.putExtra("searchFilter",searchFilter);
        intent.putExtra("searchRace",searchRace);
        startActivity(intent);
    }

    @OnClick(R.id.imageView11)
    void setBtnSolid(View solid) {
        super.onBackPressed();
    }
}