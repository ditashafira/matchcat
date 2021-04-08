package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @OnClick(R.id.imageView15)
    void setView(View solid){
        Intent intent = new Intent(getBaseContext(),MainMenuActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.textView28)
    void setView2(View solid){
        Intent intent = new Intent(getBaseContext(),EditProfileActivity.class);
        startActivity(intent);
    }
}