package dita.shafira.mate.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.cat.mating.Mating2Activity;
import dita.shafira.mate.feature.chat.ChatActivity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.Mating;
import dita.shafira.mate.model.User;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;
import static dita.shafira.mate.util.Helper.calculateAge;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mating> chatList;
    public static int catId;
    User user;

    public ChatListAdapter(Context context) {
        this.context = context;
        this.chatList = new ArrayList<Mating>();
        user = MyApp.db.userDao().user().get(0);
    }

    public void setChatList(ArrayList<Mating> chatList) {
        this.chatList.clear();
        this.chatList.addAll(chatList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        boolean user1=String.valueOf(chatList.get(position).getCat1().getUserId()).equals(user.getId());
        if (user1){
            Glide.with(context)
                    .load(BASE_URL_STORAGE+chatList.get(position).getCat1().getPhoto())
                    .centerCrop()
                    .into(holder.imageView2);
            Glide.with(context)
                    .load(BASE_URL_STORAGE+chatList.get(position).getCat2().getPhoto())
                    .centerCrop()
                    .into(holder.imageView1);
            holder.name.setText(chatList.get(position).getCat2().getName());
            if (chatList.get(position).getUserId1()==1){
                holder.notif.setVisibility(View.GONE);
            }
        }else{
            Glide.with(context)
                    .load(BASE_URL_STORAGE+chatList.get(position).getCat1().getPhoto())
                    .centerCrop()
                    .into(holder.imageView1);
            Glide.with(context)
                    .load(BASE_URL_STORAGE+chatList.get(position).getCat2().getPhoto())
                    .centerCrop()
                    .into(holder.imageView2);
            holder.name.setText(chatList.get(position).getCat1().getName());
            if (chatList.get(position).getUserId2()==1){
                holder.notif.setVisibility(View.GONE);
            }
        }
        holder.lastChat.setText(chatList.get(position).getLastChat());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
            intent.putExtra("chatRoom", chatList.get(position).getId());
            intent.putExtra("user1", user1);
            intent.putExtra("cat1", chatList.get(position).getCat1().getName());
            intent.putExtra("cat2", chatList.get(position).getCat2().getName());
            intent.putExtra("catPhoto1", chatList.get(position).getCat1().getPhoto());
            intent.putExtra("catPhoto2", chatList.get(position).getCat2().getPhoto());
            intent.putExtra("status_mate",chatList.get(position).getStatusMate());
            intent.putExtra("user2lat",chatList.get(position).getCat1().getUser().getLatitude());
            intent.putExtra("user2long",chatList.get(position).getCat1().getUser().getLongitude());
            intent.putExtra("user1lat",chatList.get(position).getCat2().getUser().getLatitude());
            intent.putExtra("user1long",chatList.get(position).getCat2().getUser().getLongitude());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circleImageView1)
        ImageView imageView1;
        @BindView(R.id.circleImageView2)
        ImageView imageView2;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.last_chat)
        TextView lastChat;
        @BindView(R.id.notif)
        ImageView notif;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
