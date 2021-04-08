package dita.shafira.mate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.ResponseCat;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mating1Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView register;
    MatingAdapter adapter;
    Context context;
    ArrayList<Cat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_1);
        ButterKnife.bind(this);
        context=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Call<List<Cat>> call = Service.getInstance().getApi().getMyCat("1");
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                Log.d("TAG", "onResponse: "+response.body().get(0).getName());
                list= (ArrayList<Cat>) response.body();
                adapter=new MatingAdapter(context);
                adapter.setCats(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure: "+t.getMessage());
                Log.d("TAG", "onFailureaadasd: "+call.request().body());
            }
        });

    }

    @OnClick(R.id.imageView15)
    void setBtnSolid(View solid){
        Intent intent = new Intent(this,MainMenuActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
    }
}