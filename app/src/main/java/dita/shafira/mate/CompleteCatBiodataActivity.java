package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompleteCatBiodataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_cat_biodata);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView11)
    void setText(View text){
        super.onBackPressed();
    }
}
