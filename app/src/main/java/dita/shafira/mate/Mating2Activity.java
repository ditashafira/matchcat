package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.adapter.MatingAdapter;

public class Mating2Activity extends AppCompatActivity {

    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.imageView16)
    ImageView photo;
    @BindView(R.id.textView20)
    TextView name;
    @BindView(R.id.textView21)
    TextView sex;
    @BindView(R.id.textView34)
    TextView age;
    @BindView(R.id.textView29)
    TextView distance;
    @BindView(R.id.switch1)
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_2);
        ButterKnife.bind(this);
        distance.setText(seekBar.getProgress());
    }

    @OnClick(R.id.btnLightSolid)
    void setBtnLightSolid(View Lightsolid) {
        Intent intent = new Intent(this, Mating3Activity.class);
        Log.d("TAG", "setBtnLightSolid: ");
        startActivity(intent);
    }
}