package dita.shafira.mate.feature.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Message;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;
import com.scaledrone.lib.SubscribeOptions;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.MessageAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class ChatActivity extends AppCompatActivity {
    private final String channelID = "4j2YDJyLvZlippLG";
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mate_description)
    TextView tvMate;
    @BindView(R.id.photo_cat_1)
    ImageView photoCat1;
    @BindView(R.id.photo_cat_2)
    ImageView photoCat2;
    @BindView(R.id.popup)
    ConstraintLayout popup;
    User user;
    Room room1;
    int a;
    private boolean user1;
    private int statusMate;
    private Context context;
    private Scaledrone scaledrone;
    private String roomName;
    private MessageAdapter messageAdapter;
    private ListView messagesView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (user1) {
            menu.getItem(4).setVisible(false);
        }
        if (statusMate != 1) {
            menu.getItem(5).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Call<Response> call;
        switch (item.getItemId()) {
            case R.id.show:
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=20.5666,45.345"));
//                startActivity(intent);
                return false;
            case R.id.endchat:
                call = Service.getInstance().getApi().statusChat(Integer.parseInt(roomName), 3);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.nav:
                String lat, lon;
                if (user1) {
                    lat = getIntent().getStringExtra("user1lat");
                    lon = getIntent().getStringExtra("user1long");
                } else {
                    lat = getIntent().getStringExtra("user2lat");
                    lon = getIntent().getStringExtra("user2long");
                }
                String uri = "http://maps.google.com/maps?daddr=" + lat + "," + lon;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                break;
            case R.id.accepted:
                call = Service.getInstance().getApi().statusMate(Integer.parseInt(roomName), 1);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.cancel:
                call = Service.getInstance().getApi().statusMate(Integer.parseInt(roomName), 2);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.blokir:
                call = Service.getInstance().getApi().statusChat(Integer.parseInt(roomName), 2);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnLater)
    void setLater(View view) {
        popup.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnVisit)
    void setVisit(View view) {
        String lat, lon;
        if (user1) {
            lat = getIntent().getStringExtra("user1lat");
            lon = getIntent().getStringExtra("user1long");
        } else {
            lat = getIntent().getStringExtra("user2lat");
            lon = getIntent().getStringExtra("user2long");
        }
        String uri = "http://maps.google.com/maps?daddr=" + lat + "," + lon;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @OnClick(R.id.close)
    void setGone(View view) {
        popup.setVisibility(View.GONE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        context = getBaseContext();

        user = MyApp.db.userDao().user().get(0);
        setSupportActionBar(toolbar);
        messageAdapter = new MessageAdapter(this);
        messagesView = findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        roomName = String.valueOf(getIntent().getIntExtra("chatRoom", 0));
        user1 = getIntent().getBooleanExtra("user1", false);
        if (user1)
            a = 1;
        else
            a = 2;
        statusMate = getIntent().getIntExtra("status_mate", 0);
        if (statusMate == 1) {
            popup.setVisibility(View.VISIBLE);
            tvMate.setText(getIntent().getStringExtra("cat1") + " dan " + getIntent().getStringExtra("cat2") + " nampaknya  saling menyukai");
            Glide.with(context)
                    .load(BASE_URL_STORAGE + getIntent().getStringExtra("catPhoto1"))
                    .centerCrop()
                    .into(photoCat1);
            Glide.with(context)
                    .load(BASE_URL_STORAGE + getIntent().getStringExtra("catPhoto2"))
                    .centerCrop()
                    .into(photoCat2);
        }
        scaledrone = new Scaledrone(channelID);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                Room room = scaledrone.subscribe(roomName, new RoomListener() {
                    @Override
                    public void onOpen(Room room) {
                        Call<Response> call1 = Service.getInstance().getApi().userRead(Integer.parseInt(roomName), a);
                        call1.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onOpenFailure(Room room, Exception ex) {

                    }

                    @Override
                    public void onMessage(Room room, Message message) {
                        boolean belongsToCurrentUser = message.getData().get("user_id").asText().equals(user.getId());
                        final dita.shafira.mate.model.Message fmessage = new dita.shafira.mate.model.Message(message.getData().get("msg").asText(), belongsToCurrentUser);
                        runOnUiThread(() -> {
                            messageAdapter.add(fmessage);
                            messagesView.setSelection(messagesView.getCount() - 1);
                        });

                        Call<Response> call1 = Service.getInstance().getApi().userRead(Integer.parseInt(roomName), a);
                        call1.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });

                    }
                }, new SubscribeOptions(10));
                room.listenToHistoryEvents((room1, message) -> {
                    boolean belongsToCurrentUser = message.getData().get("user_id").asText().equals(user.getId());
                    final dita.shafira.mate.model.Message fmessage = new dita.shafira.mate.model.Message(message.getData().get("msg").asText(), belongsToCurrentUser);
                    runOnUiThread(() -> {
                        messageAdapter.add(fmessage);
                        messagesView.setSelection(messagesView.getCount() - 1);
                    });
                    Call<Response> call1 = Service.getInstance().getApi().userRead(Integer.parseInt(roomName), a);
                    call1.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });
                });
            }

            @Override
            public void onOpenFailure(Exception ex) {

            }

            @Override
            public void onFailure(Exception ex) {
                tryReconnecting(0);
            }

            @Override
            public void onClosed(String reason) {

            }
        });

    }

    @OnClick(R.id.btnSend)
    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            HashMap<String, String> m = new HashMap<String, String>();
            m.put("msg", message);
            m.put("user_id", user.getId());
            scaledrone.publish(roomName, m);
            int a;
            if (user1)
                a = 1;
            else
                a = 2;
            Log.d("TAGD", "sendMessage: " + a);
            Call<Response> call = Service.getInstance().getApi().lastChat(Integer.parseInt(roomName), message, a);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
            editText.getText().clear();
        }
    }

    private void tryReconnecting(final int reconnectAttempt) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final Scaledrone drone = new Scaledrone(channelID);
                drone.connect(new Listener() {
                    @Override
                    public void onOpen() {

                    }

                    @Override
                    public void onOpenFailure(Exception ex) {

                    }

                    @Override
                    public void onFailure(Exception ex) {
                        tryReconnecting(reconnectAttempt + 1);
                    }

                    @Override
                    public void onClosed(String reason) {

                    }
                });
                // set everything up again..
            }
        }, reconnectAttempt * 1000);
    }


//    public void setSupportActionBar(Toolbar supportActionBar) {
//        this.supportActionBar = supportActionBar;
//    }

    @OnClick(R.id.imageView11)
    void setBtnSolid(View solid){
        super.onBackPressed();
    }
}