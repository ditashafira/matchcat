package dita.shafira.mate.feature.cat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.FavoriteAdapter;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.blank)
    ImageView blank;
    FavoriteAdapter adapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ButterKnife.bind(this);
        context= this;
        adapter= new FavoriteAdapter(context,getIntent().getIntExtra("catId",0));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        Call<List<Mating>> call = Service.getInstance().getApi().catLove(getIntent().getIntExtra("catId",0));
        Log.d("Taga", "onCreate: "+getIntent().getStringExtra("catId"));
        call.enqueue(new Callback<List<Mating>>() {
            @Override
            public void onResponse(Call<List<Mating>> call, Response<List<Mating>> response) {
                if (response.body().size()!=0){
                    adapter.setCats((ArrayList<Mating>) response.body());
                }else{
                    blank.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Mating>> call, Throwable t) {
                blank.setVisibility(View.VISIBLE);
            }
        });

    }

    @OnClick(R.id.imageView11)
    void setText(View text){
        super.onBackPressed();
    }

}