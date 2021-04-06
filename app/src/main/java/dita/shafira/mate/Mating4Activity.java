package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public class Mating4Activity extends AppCompatActivity {

    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_4);
        ButterKnife.bind(this);
    }
}