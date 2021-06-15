package dita.shafira.mate.feature.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dita.shafira.mate.R;
import dita.shafira.mate.database.MyApp;
import dita.shafira.mate.model.User;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        User user = MyApp.db.userDao().user().get(0);
        username.setText(user.getName());
        email.setText(user.getEmail());
        location.setText(user.getAddress());

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

    @OnClick(R.id.imageView11)
    void setText(View text) {
        super.onBackPressed();
    }
}