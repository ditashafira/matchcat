package dita.shafira.mate.feature.cat.mating;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_5);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView11)
    void setText(View text){
        super.onBackPressed();
    }
}