package dita.shafira.mate.feature.cat.mating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.slider.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.service.Service;
import dita.shafira.mate.util.GpsTracker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    GpsTracker gpsTracker;
    private Context context;

    @OnClick(R.id.tv_nav)
    void setNav(View nav) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Mating2Activity.this);
        alert.setTitle("Sesuaikan lokasi mapping");
        alert.setMessage("Pastikan lokasi benar dan gps menyala");
        alert.setPositiveButton("Sesuaikan", (dialog, which) -> {
            gpsTracker = new GpsTracker(Mating2Activity.this);
            if (gpsTracker.canGetLocation()) {
                if (gpsTracker.getLongitude() != 0 && gpsTracker.getLatitude() != 0) {
                    Call<ResponseLogin> call = Service.getInstance().getApi().updateLocation(MyApp.db.userDao().user().get(0).getId(), String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()));
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            MyApp.db.userDao().nukeTable();
                            MyApp.db.userDao().login(response.body().getContent().getUser());
                            Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    call.enqueue(new Callback<Response>() {
//                        @Override
//                        public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
//
//                        }
//                    });
                } else {
                    gpsTracker.showSettingsAlert();
                }
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("Batalkan", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_2);
        ButterKnife.bind(this);
        context = this;
        Cat cat = getIntent().getExtras().getParcelable("cat_id");
        Glide.with(this)
                .load(BASE_URL_STORAGE + cat.getPhoto())
                .centerCrop()
                .into(photo);
        Log.d("TAG", "onCreate: " + cat.getBirth());
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
            searchDistance = (int) value;
            searchAge = calculateAgeMonth(cat.getBirth());
            searchSex = cat.getSex();
            searchRace = cat.getRaceId();
            searchFilter = filter.isChecked();
        });

    }

    @OnClick(R.id.btnLightSolid)
    void setBtnLightSolid(View Lightsolid) {
        Intent intent = new Intent(this, Mating3Activity.class);
        intent.putExtra("searchDistance", searchDistance);
        intent.putExtra("searchAge", searchAge);
        intent.putExtra("searchSex", searchSex);
        intent.putExtra("searchFilter", searchFilter);
        intent.putExtra("searchRace", searchRace);
        startActivity(intent);
    }

    @OnClick(R.id.imageView11)
    void setBtnSolid(View solid) {
        super.onBackPressed();
    }
}