package dita.shafira.mate.feature.cat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.CatPhotosAdapter;
import dita.shafira.mate.model.CatPhoto;

public class CompleteCatBiodataActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.vaccine)
    EditText vaccine;
    @BindView(R.id.parasite)
    EditText parasite;
    Context context;
    CatPhotosAdapter adapter;
    int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_cat_biodata);
        ButterKnife.bind(this);
        context=getBaseContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<CatPhoto> catPhotos= new ArrayList<>();
        adapter=new CatPhotosAdapter(context,catPhotos);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "YY-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                if (status==1){
                    vaccine.setText(sdf.format(myCalendar.getTime()));
                }else {
                    parasite.setText(sdf.format(myCalendar.getTime()));
                }
            }

        };
        vaccine.setOnClickListener(v -> {
            status=1;
            new DatePickerDialog(CompleteCatBiodataActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        parasite.setOnClickListener(v -> {
            status=2;
            new DatePickerDialog(CompleteCatBiodataActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

    }

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }
}
