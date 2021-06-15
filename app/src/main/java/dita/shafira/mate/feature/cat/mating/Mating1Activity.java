package dita.shafira.mate.feature.cat.mating;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mating1Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    MatingAdapter adapter;
    Context context;
    ArrayList<Cat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_1);
        ButterKnife.bind(this);
        context = this;
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Call<List<Cat>> call = Service.getInstance().getApi().catMe(MyApp.db.userDao().user().get(0).getId());
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                list = (ArrayList<Cat>) response.body();
                adapter = new MatingAdapter(context);
                adapter.setCats(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @OnClick(R.id.imageView11)
    void setBtnSolid(View solid) {
        super.onBackPressed();
    }
}