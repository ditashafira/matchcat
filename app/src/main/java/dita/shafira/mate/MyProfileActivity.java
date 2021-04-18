package dita.shafira.mate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.adapter.MyProfileCat;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends Fragment {

    @BindView(R.id.profile_image)
    ImageView photo;
    @BindView(R.id.textView6)
    TextView username;
    @BindView(R.id.textView8)
    TextView location;
    @BindView(R.id.textView11)
    TextView usernamebottom;
    @BindView(R.id.textView31)
    TextView email;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MyProfileCat adapter;
    Context context;
    ArrayList<Cat> list;

    public MyProfileActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.activity_myprofile, container, false);
        ButterKnife.bind(this,view);
        context=getContext();
        User user=MyApp.db.userDao().user().get(0);
        username.setText(user.getName());
        email.setText(user.getEmail());
        usernamebottom.setText(MyApp.db.userDao().user().get(0).getName());

        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        Call<List<Cat>> call = Service.getInstance().getApi().getMyCat("1");
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                list= (ArrayList<Cat>) response.body();
                adapter=new MyProfileCat(context);
                adapter.setCats(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("TAG", "onFailure: "+t.getMessage());
                Log.d("TAG", "onFailureaadasd: "+call.request().body());
            }
        });
        return view;
    }
    @OnClick(R.id.textView32)
    void settext(View text){
        Intent intent = new Intent(getContext(),SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textView33)
    void settexkjkt(View text){
        Intent intent = new Intent(getContext(),HistoryMatingActivity.class);
        startActivity(intent);
    }

}