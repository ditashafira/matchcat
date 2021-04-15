package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryMatingActivity extends AppCompatActivity {
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_mating);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView15)
    void settext(View text) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}