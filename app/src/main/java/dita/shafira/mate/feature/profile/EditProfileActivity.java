package dita.shafira.mate.feature.profile;

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
import android.widget.EditText;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.ResponseLogin;
import dita.shafira.mate.model.User;
import dita.shafira.mate.service.Service;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static dita.shafira.mate.service.Service.BASE_URL_STORAGE;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.location)
    EditText location;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.profile_image)
    ImageView photo;
    @BindView(R.id.add_photo)
    ImageView add_button;
    private User user;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        context = this;
        user = MyApp.db.userDao().user().get(0);
        username.setText(user.getName());
        email.setText(user.getEmail());
        location.setText(user.getAddress());
        Log.d("TAG", "onCreate: "+user.getPhoto());
        if (user.getPhoto() != null) {
            Glide.with(context)
                    .load(BASE_URL_STORAGE + user.getPhoto())
                    .centerCrop()
                    .into(photo);
        }
    }

    @OnClick(R.id.btn_save)
    void setBtnSave(View view) {
        Call<ResponseLogin> call = Service.getInstance().getApi().updateProfile(
                user.getId(), username.getText().toString(), location.getText().toString(), password.getText().toString()
        );
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
                MyApp.db.userDao().nukeTable();
                MyApp.db.userDao().login(response.body().getContent().getUser());
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.add_photo)
    void setBtnSolid(View solid) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "select image"), 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            Uri uri = data.getData();
            Bitmap bitmap = getDecodedImageFromUri(uri);
            Glide.with(getBaseContext())
                    .load(bitmap)
                    .centerCrop()
                    .into(photo);
            uploadImage(bitmap);
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

//        BitmapFactory.decodeStream(inputStream, rect, options); //HERE
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


    public void uploadImage(Bitmap gambarbitmap) {
        File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        Call<ResponseLogin> call =
                Service.getInstance().getApi().
                        updateProfilePhoto(
                                MyApp.db.userDao().user().get(0).getId(),
                                body
                        );
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
                MyApp.db.userDao().nukeTable();
                MyApp.db.userDao().login(response.body().getContent().getUser());
                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
// finally, kirim map dan body pada param interface retrofit
    }

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }
}