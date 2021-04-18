package dita.shafira.mate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
            EditText email;
    @BindView(R.id.location)
            EditText location;
    @BindView(R.id.password)
            EditText password;
    @BindView(R.id.repeat)
            EditText repeat;



    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    void setBtnSolid(View solid) {
        Call<ResponseLogin> call = Service.getInstance().getApi().register(username.getText().toString(),email.getText().toString(),location.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.code() == 200) {
//                    Log.d("TAG", "onResponse: "+response.body().getStatus());
                    Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    if (response.body().getStatus().equals("success")) {
                        MyApp.db.userDao().nukeTable();
                        MyApp.db.userDao().login(response.body().getContent().getUser());

                        Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}