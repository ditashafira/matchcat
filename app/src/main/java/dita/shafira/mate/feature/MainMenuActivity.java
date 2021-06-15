package dita.shafira.mate.feature;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.R;
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    CurvedBottomNavigationView cbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);



        CbnMenuItem notification = new CbnMenuItem(R.drawable.ic_profile, R.drawable.avd_profile, R.id.navigation_profile);
        CbnMenuItem notification2 = new CbnMenuItem(R.drawable.ic_baseline_chat_24, R.drawable.avd_chat, R.id.navigation_chat);
        CbnMenuItem notification1 = new CbnMenuItem(R.drawable.ic_pawprint, R.drawable.avd_pawprint, R.id.navigation_dashboard);

        CbnMenuItem[] navigation_items = {notification,notification2,notification1};

        cbn.setMenuItems(navigation_items, 0);

        cbn.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment));

    }
}
