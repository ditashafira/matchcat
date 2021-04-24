package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.database.MyApp;

public class SettingActivity extends AppCompatActivity {
public SettingActivity(){

}
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.imageView11)
    void setView(View solid){
        Intent intent = new Intent(getBaseContext(),MainMenuActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.textView28)
    void setView2(View solid){
        Intent intent = new Intent(getBaseContext(),EditProfileActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.textView19)
    void setView3(View solid){
        MyApp.db.userDao().nukeTable();
        Toast.makeText(getBaseContext(),"Anda telah log out" ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
        startActivity(intent);
    }
}