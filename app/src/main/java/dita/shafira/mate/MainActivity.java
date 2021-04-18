package dita.shafira.mate;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.component.BtnSolid;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.User;

import static androidx.core.content.res.ResourcesCompat.*;
import static dita.shafira.mate.R.id.btnLightSolid;

public class MainActivity<typeface> extends AppCompatActivity {
//    BtnSolid btnSolid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<User> user=MyApp.db.userDao().user();
        if (user.size() !=0){
            Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
            startActivity(intent);
        }


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
//    @OnClick(R.id.register)
//    void setRegister(View solid){
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
//    }


}