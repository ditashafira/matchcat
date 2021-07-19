package dita.shafira.mate.feature.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.ChatListAdapter;
import dita.shafira.mate.adapter.MatingAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.blank)
    ImageView blank;
    ChatListAdapter adapter;
    Context context;
    ArrayList<Mating> list;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<List<Mating>> call = Service.getInstance().getApi().getListChat(MyApp.db.userDao().user().get(0).getId());
        call.enqueue(new Callback<List<Mating>>() {
            @Override
            public void onResponse(Call<List<Mating>> call, Response<List<Mating>> response) {
                if (response.body().size()!=0){
                    list = (ArrayList<Mating>) response.body();
                    adapter = new ChatListAdapter(context);
                    adapter.setChatList(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    blank.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Mating>> call, Throwable t) {
                blank.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        Call<List<Mating>> call = Service.getInstance().getApi().getListChat(MyApp.db.userDao().user().get(0).getId());
        call.enqueue(new Callback<List<Mating>>() {
            @Override
            public void onResponse(Call<List<Mating>> call, Response<List<Mating>> response) {
                if (response.body().size()!=0){
                    list = (ArrayList<Mating>) response.body();
                    adapter = new ChatListAdapter(context);
                    adapter.setChatList(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Mating>> call, Throwable t) {

            }
        });
        return view;

    }

}