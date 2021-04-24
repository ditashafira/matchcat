package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        register=findViewById(R.id.register);
//        register.setOnClickListener(v -> {
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//        });
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnSolid6)
    void setBtnSolid(View solid) {
        Call<ResponseLogin> call = Service.getInstance().getApi().login(email.getText().toString(), password.getText().toString());
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
                Log.d("TAG", t.getMessage());
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.forget_password)
    void settext(View text) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
