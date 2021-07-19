package dita.shafira.mate.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.imageView15)
    void imageview(View image){
        super.onBackPressed();
    }

    @OnClick(R.id.sendMail)
    void sendMail(View image){
        if(!email.getText().toString().isEmpty()){
            Call<Response> call = Service.getInstance().getApi().resetPassword(email.getText().toString());
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT);
                }
            });
        }else{
            email.setError("email tidak boleh kosong");
        }

    }
}
