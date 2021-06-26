package dita.shafira.mate.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
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
    @BindView(R.id.btnSolid6)
    Button loginBtn;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSolid6)
    void setBtnSolid(View solid) {
        loginBtn.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        Call<ResponseLogin> call = Service.getInstance().getApi().login(email.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                if (response.body().getStatus().equals("success")) {
                    MyApp.db.userDao().nukeTable();
                    MyApp.db.userDao().login(response.body().getContent().getUser());
                    Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                loginBtn.setEnabled(true);
            }
        });
    }

    @OnClick(R.id.forget_password)
    void settext(View text) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
