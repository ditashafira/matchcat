package dita.shafira.mate.feature.cat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.adapter.CatPhotosAdapter;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.feature.cat.mating.Mating3Activity;
import dita.shafira.mate.feature.cat.mating.Mating4Activity;
import dita.shafira.mate.model.Cat;
import dita.shafira.mate.model.CatPhoto;
import dita.shafira.mate.model.Response;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class EditCatActivity extends AppCompatActivity {

    @BindView(R.id.catparasite)
    EditText catparasite;
    @BindView(R.id.catvaccine)
    EditText catvaccine;
    @BindView(R.id.catname)
    EditText catname;
    @BindView(R.id.mainPhoto)
    ImageView mainphoto;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    CatPhotosAdapter adapter;
    User user;
    Bitmap bitmap;
    int parvac;
    Context context;
    Cat cat;
    void setData(){
        Call<Cat> call = Service.getInstance().getApi().catMeDetail(MyApp.db.userDao().user().get(0).getId(), String.valueOf(cat.getId()));
        call.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, retrofit2.Response<Cat> response) {
                cat = response.body();
                adapter.setCatPhotos((ArrayList<CatPhoto>) cat.getPhotos());
                catname.setText(cat.getName());
                catparasite.setText(cat.getLastParasite());
                catvaccine.setText(cat.getLastVaccine());
                Glide.with(getBaseContext())
                        .load(BASE_URL_STORAGE + cat.getPhoto())
                        .centerCrop()
                        .into(mainphoto);


            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cat);
        ButterKnife.bind(this);
        context = this;
        cat = getIntent().getParcelableExtra("cat");
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CatPhotosAdapter(context,cat);
        recyclerView.setAdapter(adapter);

        setData();
        adapter.setCatPhotos((ArrayList<CatPhoto>) cat.getPhotos());
        catname.setText(cat.getName());
        catparasite.setText(cat.getLastParasite());
        catvaccine.setText(cat.getLastVaccine());
        Glide.with(getBaseContext())
                .load(BASE_URL_STORAGE + cat.getPhoto())
                .centerCrop()
                .into(mainphoto);


        user = MyApp.db.userDao().user().get(0);
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "YY-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                if (parvac == 0) {
                    catvaccine.setText(sdf.format(myCalendar.getTime()));
                } else {
                    catparasite.setText(sdf.format(myCalendar.getTime()));
                }
            }

        };
        catparasite.setOnClickListener(v -> {
            parvac = 1;
            new DatePickerDialog(EditCatActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        catvaccine.setOnClickListener(v -> {
            parvac = 0;
            new DatePickerDialog(EditCatActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }

    @OnClick(R.id.btnSimpan)
    void setBtnSimpan(View view) {
        Call<Response> call = Service.getInstance().getApi().catUpdate(
                String.valueOf(cat.getId()),
                catname.getText().toString(),
                catparasite.getText().toString(),
                catvaccine.getText().toString()

        );
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Intent intent = new Intent(getBaseContext(), Mating4Activity.class);
                intent.putExtra("cat_id",cat.getId());
                Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                startActivity(intent);
                EditCatActivity.this.finish();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.mainPhoto)
    void setBtnSolid(View solid) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "select image"), 200);
    }

    @OnClick(R.id.btn_add)
    void setBtnAdd(View solid) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "select image"), 201);
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() + "_image.jpg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            Uri uri = data.getData();
            bitmap = getDecodedImageFromUri(uri);
            Glide.with(getBaseContext())
                    .load(bitmap)
                    .centerCrop()
                    .into(mainphoto);
            uploadImage(bitmap, 200);
        }
        if (requestCode == 201) {
            Uri uri = data.getData();
            bitmap = getDecodedImageFromUri(uri);
            uploadImage(bitmap, 201);
        }
    }

    private Bitmap getDecodedImageFromUri(Uri uri) {
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Rect rect = new Rect(0, 0, 0, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        options.inSampleSize = getInSampleSize(options, 128, 128);

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, rect, options); //HERE IS PROBLEM - bitmap = null.
        return bitmap;
    }

    private int getInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight &&
                    (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void uploadImage(Bitmap gambarbitmap, int requestCode) {
        File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        Call<Response> call;
        if (requestCode == 200) {
            call = Service.getInstance().getApi().catUpdatePhoto(String.valueOf(cat.getId()), body);
            Log.d("TAGa", "uploadImage: c");
        } else {
            call = Service.getInstance().getApi().catAddPhoto(String.valueOf(cat.getId()), body);
            Log.d("TAGa", "uploadImage: b");
        }
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<dita.shafira.mate.model.Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
             setData();
            }

            @Override
            public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

// finally, kirim map dan body pada param interface retrofit
    }

}