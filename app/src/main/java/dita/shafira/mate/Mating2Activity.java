package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.slider.Slider;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mating2Activity extends AppCompatActivity {
    public static int calculateAge(String date) {
        LocalDate birthDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
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
    @BindView(R.id.textView29)
    TextView distance;
    @BindView(R.id.switch1)
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_2);
        ButterKnife.bind(this);
//        String cat_id = String.valueOf(getIntent().getExtras().getInt("cat_id"));
        Cat cat = getIntent().getExtras().getParcelable("cat_id");
        name.setText(cat.getName());
        sex.setText("auto laki");
        age.setText(calculateAge(cat.getBirth()) + "thn/" + cat.getRace().getTitle());
        Glide.with(this)
                .load(cat.getPhotos1())
                .centerCrop()
                .into(photo);
//        Toast.makeText(getBaseContext(), cat_id, Toast.LENGTH_LONG).show();

//        Call<Cat> call = Service.getInstance().getApi().getMyCatDetail("1", cat_id);
//        call.enqueue(new Callback<Cat>() {
//
//            @Override
//            public void onResponse(Call<Cat> call, Response<Cat> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Cat> call, Throwable t) {
//                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//        seekBar.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
//            @Override
//            public void onStartTrackingTouch(@NonNull Slider slider) {
//                distance.setText((int) slider.getValue()+"km");
//            }
//
//            @Override
//            public void onStopTrackingTouch(@NonNull Slider slider) {
//                distance.setText((int) slider.getValue()+"km");
//            }
//        });
        seekBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                distance.setText((int) value + "km");
            }
        });
    }

    @OnClick(R.id.btnLightSolid)
    void setBtnLightSolid(View Lightsolid) {
        Intent intent = new Intent(this, Mating3Activity.class);
        Log.d("TAG", "setBtnLightSolid: ");
        startActivity(intent);
    }
}