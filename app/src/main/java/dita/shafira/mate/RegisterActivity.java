package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        register=findViewById(R.id.register);
//        register.setOnClickListener(v -> {
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//        });

    }
}