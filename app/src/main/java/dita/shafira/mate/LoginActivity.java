package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    }
}
