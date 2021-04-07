package dita.shafira.mate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.adapter.MatingSearchAdapter;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class Mating3Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView register;
    MatingSearchAdapter adapter;
    Context context;
    ArrayList<Cat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_1);
        ButterKnife.bind(this);
        context=this;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Call<List<Cat>> call = Service.getInstance().getApi().getMyCat("1");
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                Log.d("TAG", "onResponse: " + response.body().get(0).getName());
                list = (ArrayList<Cat>) response.body();
                adapter = new MatingSearchAdapter(context);
                adapter.setCats(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
    }
}