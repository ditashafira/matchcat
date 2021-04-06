package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    TextView register;
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
    void setBtnSolid(View solid){
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.register)
    void settext(View text){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    }
