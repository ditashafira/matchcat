package dita.shafira.mate.feature.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.LoginActivity;
import dita.shafira.mate.feature.MainMenuActivity;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.service.Service;
import dita.shafira.mate.util.GpsTracker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.sc_notification)
    SwitchCompat scNotification;
    @BindView(R.id.sc_hide)
    SwitchCompat scHide;
    private GpsTracker gpsTracker;
    private Context context;

    public SettingActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        context = this;
        boolean isCheck;
        isCheck = MyApp.db.userDao().user().get(0).getStatus() == 1;
        scHide.setChecked(isCheck);
        scHide.setOnCheckedChangeListener((buttonView, isChecked) -> {
            scHide.setClickable(false);
            int check;
            if (isChecked) {
                check = 1;
            } else {
                check = 2;
            }
            Call<ResponseLogin> call = Service.getInstance().getApi().userUpdateStatus(MyApp.db.userDao().user().get(0).getId(), check);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<dita.shafira.mate.model.ResponseLogin> call, Response<dita.shafira.mate.model.ResponseLogin> response) {
                    MyApp.db.userDao().nukeTable();
                    MyApp.db.userDao().login(response.body().getContent().getUser());
                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    scHide.setClickable(true);
                }

                @Override
                public void onFailure(Call<dita.shafira.mate.model.ResponseLogin> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    scHide.setClickable(true);
                }
            });
        });
    }

    @OnClick(R.id.toolbar_back)
    void setView(View view) {
        Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.edit_profile)
    void setView2(View view) {
        Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logout)
    void setView3(View view) {
        MyApp.db.userDao().nukeTable();
        Toast.makeText(getBaseContext(), "Anda telah log out", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
        SettingActivity.this.finish();
    }

    @OnClick(R.id.navigation)
    void setNavigation(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
        alert.setTitle("Sesuaikan lokasi mapping");
        alert.setMessage("Pastikan lokasi benar dan gps menyala");
        alert.setPositiveButton("Sesuaikan", (dialog, which) -> {
            gpsTracker = new GpsTracker(SettingActivity.this);
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
                } else {
                    gpsTracker.showSettingsAlert();
                }
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Batalkan", (dialog, which) -> dialog.dismiss());
        alert.show();
    }
}