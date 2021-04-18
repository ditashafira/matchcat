package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCatActivity extends AppCompatActivity {

    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cat);
ButterKnife.bind(this);
    }

    @OnClick(R.id.recyclerView)
    void setBtnSolid(View solid){
        Intent intent = new Intent(this,Mating2Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imageView11)
    void setText(View text){
        super.onBackPressed();
    }

    @OnClick(R.id.btnoutline_4)
    void setBtnOutline(View outline){
        Intent intent = new Intent(getBaseContext(),FillCatBiodata.class);
        startActivity(intent);
    }
}