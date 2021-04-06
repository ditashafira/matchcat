package dita.shafira.mate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends Fragment {
    public DashboardActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.imageView14)
    void setBtnSolid(View solid){
        Intent intent = new Intent(getContext(),Mating1Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imageView13)
    void setBtnSolid2(View solid){
        Intent intent = new Intent(getContext(),ListCatActivity.class);
        startActivity(intent);
    }

}