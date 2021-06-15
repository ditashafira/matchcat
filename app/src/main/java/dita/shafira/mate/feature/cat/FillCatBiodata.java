package dita.shafira.mate.feature.cat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.Race;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FillCatBiodata extends AppCompatActivity {
    String[] strings;
    int[] rid;
    @BindView(R.id.race)
    EditText spinner;
    //    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.roundedImageView)
    ImageView photo;
    @BindView(R.id.imageView19)
    ImageView add_photo;
    @BindView(R.id.birth)
    EditText birthday;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sex)
    RadioGroup sex;

    User user;
    RequestBody part;
    int sex_id;
    int user_id;
    int race_id;
    File file;
    Bitmap bitmap;
    private RadioButton radioButton;

    @OnClick(R.id.imageView19)
    void setBtnSolid(View solid) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "select image"), 200);
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
                    .into(photo);
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

    public void uploadImage(Bitmap gambarbitmap) {
        File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        Call<dita.shafira.mate.model.Response> call =
                Service.getInstance().getApi().
                        catStore(
                                name.getText().toString(),
                                race_id,
                                birthday.getText().toString(),
                                sex_id,
                                Integer.parseInt(user.getId()),
                                body
                        );
        call.enqueue(new Callback<dita.shafira.mate.model.Response>() {
            @Override
            public void onResponse(Call<dita.shafira.mate.model.Response> call, Response<dita.shafira.mate.model.Response> response) {
                Intent intent = new Intent(getBaseContext(), ListCatActivity.class);
                Toast.makeText(getBaseContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<dita.shafira.mate.model.Response> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
// finally, kirim map dan body pada param interface retrofit
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_cat_biodata);
        ButterKnife.bind(this);
        Calendar myCalendar = Calendar.getInstance();
        user = MyApp.db.userDao().user().get(0);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "YY-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                birthday.setText(sdf.format(myCalendar.getTime()));
            }

        };
        birthday.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FillCatBiodata.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Call<List<Race>> call = Service.getInstance().getApi().catRace();
        call.enqueue(new Callback<List<Race>>() {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                strings = new String[response.body().size()];
                rid = new int[response.body().size()];
                for (int i = 0; i < response.body().size(); i++) {
                    strings[i] = response.body().get(i).getTitle();
                    rid[i] = response.body().get(i).getId();
                }
            }

            @Override
            public void onFailure(Call<List<Race>> call, Throwable t) {

            }
        });

        spinner.setInputType(InputType.TYPE_NULL);
        spinner.setFocusable(false);
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FillCatBiodata.this);
                builder.setTitle("Pilih Ras Kucing");

                //Pass array list di Alert dialog
                builder.setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spinner.setText(strings[which]);
                        race_id = rid[which];
                    }
                });
                // buat dan tampilkan alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }


    @OnClick(R.id.create)
    void settext(View text) {

        int selectedId = sex.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        String a = radioButton.getText().toString();
        if (a.equals("Jantan")) {
            sex_id = 1;
        } else {
            sex_id = 2;
        }
        uploadImage(bitmap);
    }

    @OnClick(R.id.imageView11)
    void imageview(View image) {
        super.onBackPressed();
    }
}
