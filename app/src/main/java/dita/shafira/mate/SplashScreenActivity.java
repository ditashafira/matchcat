package dita.shafira.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.List;

import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.MainActivity;
import dita.shafira.mate.feature.MainMenuActivity;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                List<User> user= MyApp.db.userDao().user();
                if (user.size() !=0){
                    Call<Response> call = Service.getInstance().getApi().checkLogin(user.get(0).getRemember_token());
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<dita.shafira.mate.model.Response> call, retrofit2.Response<Response> response) {
                            Intent intent;
                            Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                            if (response.body().getStatus().equals("success")){
                                intent = new Intent(getBaseContext(), MainMenuActivity.class);
                            }else{
                                intent = new Intent(getBaseContext(), MainActivity.class);
                            }
                            startActivity(intent);
                            SplashScreenActivity.this.finish();
                        }
                        @Override
                        public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {
                            Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                }
            }
        }, 3000);

    }
}