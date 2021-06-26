package dita.shafira.mate.feature;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.MyProfileCat;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.cat.HistoryMatingActivity;
import dita.shafira.mate.feature.profile.SettingActivity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class MyProfileFragment extends Fragment {

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
    @BindView(R.id.progress)
    ProgressBar progressBar;
//    @BindView(R.id.textView8)
//    TextView city;
    MyProfileCat adapter;
    Context context;
    ArrayList<Cat> list;

    public MyProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_myprofile, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);
        context = getContext();
        User user = MyApp.db.userDao().user().get(0);
        username.setText(user.getName());
        email.setText(user.getEmail());
        if (user.getPhoto() != null) {
            Glide.with(context)
                    .load(BASE_URL_STORAGE + user.getPhoto())
                    .centerCrop()
                    .into(photo);
        }
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(user.getLatitude()), Double.parseDouble(user.getLongitude()), 1);
            String cityName = addresses.get(0).getSubAdminArea();
            if (!cityName.equals("")){
                location.setText(cityName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        usernamebottom.setText(MyApp.db.userDao().user().get(0).getName());

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        Call<List<Cat>> call = Service.getInstance().getApi().catMe(user.getId());
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                list = (ArrayList<Cat>) response.body();
                adapter = new MyProfileCat(context);
                if (list != null) {
                    adapter.setCats(list);
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    @OnClick(R.id.textView32)
    void settext(View text) {
        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textView33)
    void settexkjkt(View text) {
        Intent intent = new Intent(getContext(), HistoryMatingActivity.class);
        startActivity(intent);
    }

}