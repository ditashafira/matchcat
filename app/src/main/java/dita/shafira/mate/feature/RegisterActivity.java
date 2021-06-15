package dita.shafira.mate.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    @BindView(R.id.register)
    Button registerBtn;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    void setBtnSolid(View solid) {
        registerBtn.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        if (password.getText().toString().equals(repeat.getText().toString())){
            Call<ResponseLogin> call = Service.getInstance().getApi().register(username.getText().toString(), email.getText().toString(), location.getText().toString(), password.getText().toString());
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.code() == 200) {
                        Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("success")) {
                            MyApp.db.userDao().nukeTable();
                            MyApp.db.userDao().login(response.body().getContent().getUser());
                            Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                            startActivity(intent);
                            RegisterActivity.this.finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    registerBtn.setEnabled(true);
                }
            });
        }else{
            repeat.setError("isi password yang sama");
            registerBtn.setEnabled(true);
        }
    }
}