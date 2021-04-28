package dita.shafira.mate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.adapter.MyCatAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCatActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView register;
    MyCatAdapter adapter;
    Context context;
    ArrayList<Cat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cat);
ButterKnife.bind(this);
        context=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Call<List<Cat>> call = Service.getInstance().getApi().getMyCat(MyApp.db.userDao().user().get(0).getId());
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                list= (ArrayList<Cat>) response.body();
                adapter=new MyCatAdapter(context);
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