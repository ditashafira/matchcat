package dita.shafira.mate.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;

public class MainActivity<typeface> extends AppCompatActivity {

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

    @OnClick(R.id.btnOutline)
    void setBtnLightSolid(View lightSolid){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}