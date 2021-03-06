package dita.shafira.mate;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.component.BtnSolid;

public class MainActivity extends AppCompatActivity {
//    BtnSolid btnSolid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnSolid)
    void setBtnSolid(View solid){
        Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
    }
//    @OnClick(R.id.register)
//    void setRegister(View solid){
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
//    }


}