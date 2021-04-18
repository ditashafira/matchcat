package dita.shafira.mate;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dita.shafira.mate.model.Race;
import dita.shafira.mate.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FillCatBiodata extends AppCompatActivity {
    String[] strings;
    @BindView(R.id.race)
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_cat_biodata);
        ButterKnife.bind(this);

        Call<List<Race>> call = Service.getInstance().getApi().getRace();
        call.enqueue(new Callback<List<Race>>() {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                strings=new String[response.body().size()+1];
                strings[0]="Silakan Pilih Ras";
                for (int i = 1; i <response.body().size()+1 ; i++) {
                    strings[i]=response.body().get(i-1).getTitle();
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                        R.layout.item_race, strings);

                // mengeset Array Adapter tersebut ke Spinner
                spinner.setAdapter(adapter);
//spinner.setPrompt("hghghgj");
                // mengeset listener untuk mengetahui saat item dipilih
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getBaseContext(),adapter.getItem(position),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Race>> call, Throwable t) {

            }
        });

    }
}
