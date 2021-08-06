package dita.shafira.mate.feature.cat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.HistoryAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryMatingActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView;
    @BindView(R.id.blank)
    ImageView blank;
    private HistoryAdapter adapter;
    private ArrayList<Mating> matings;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_mating);
        ButterKnife.bind(this);
        matings = new ArrayList<>();

        context=this;
        adapter = new HistoryAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        Call<List<Mating>> call = Service.getInstance().getApi().catMarried(MyApp.db.userDao().user().get(0).getId());
        call.enqueue(new Callback<List<Mating>>() {
            @Override
            public void onResponse(Call<List<Mating>> call, Response<List<Mating>> response) {
                if (response.body().size()!=0){
                    matings.addAll(response.body());
                    adapter.setMatings(matings);
                }else{
                    blank.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Mating>> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.imageView11)
    void setText(View text){
        super.onBackPressed();
    }
}