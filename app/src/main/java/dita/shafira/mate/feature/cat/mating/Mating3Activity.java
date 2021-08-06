package dita.shafira.mate.feature.cat.mating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.adapter.MatingSearchAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatSearch;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class Mating3Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.blank)
    ImageView blank;

    MatingSearchAdapter adapter;
    Context context;
    ArrayList<CatSearch> list;
    int searchDistance;
    int searchSex;
    int searchAge;
    int searchRace;
    boolean searchFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_3);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);
        context=this;
        searchAge=getIntent().getIntExtra("searchAge",0);
        searchSex=getIntent().getIntExtra("searchSex",1);
        searchRace=getIntent().getIntExtra("searchRace",0);
        searchDistance=getIntent().getIntExtra("searchDistance",0);
        searchFilter=getIntent().getBooleanExtra("searchFilter",false);
        User user = MyApp.db.userDao().user().get(0);
        Log.d("checkTAG", "onCreate: "+user.getId()+" "+searchSex+" "+searchRace+" "+searchDistance);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Call<List<CatSearch>> call = Service.getInstance().getApi().catSearch(
                Integer.parseInt(user.getId()),
                searchSex,
                searchRace,
                searchDistance
        );
        call.enqueue(new Callback<List<CatSearch>>() {
            @Override
            public void onResponse(Call<List<CatSearch>> call, Response<List<CatSearch>> response) {
                list = (ArrayList<CatSearch>) response.body();
                adapter = new MatingSearchAdapter(context);
                if (list.size()!=0){
                    adapter.setCats(list);
                }else{
                    Toast.makeText(getBaseContext(),"tidak ditemukan kucing diarea "+searchDistance +" km didaerah anda",Toast.LENGTH_LONG).show();
                    blank.setVisibility(View.VISIBLE);
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<CatSearch>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }

    @OnClick(R.id.imageView11)
    void setBtnSolid(View solid){
        super.onBackPressed();
    }

}