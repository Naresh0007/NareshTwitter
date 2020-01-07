package com.naresh.nareshtwitter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.naresh.nareshtwitter.StrictMode.StrictModeClass;
import com.naresh.nareshtwitter.api.ApiClass;
import com.naresh.nareshtwitter.model.Image;
import com.naresh.nareshtwitter.model.SignupRes;
import com.naresh.nareshtwitter.model.User;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {
    ImageView profile;
    Button login;
    String password, email, username, ImageName;
    String ImagePath = "";
    public static String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        profile = findViewById(R.id.profile);
        login = findViewById(R.id.btn_CameraLogin);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
            username = bundle.getString("username");
            password = bundle.getString("password");

        }
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ImagePath.isEmpty()) {
                    Toast.makeText(CameraActivity.this, "Select Image first", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveImageOnly();
                signUp();
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        profile.setImageURI(uri);
        ImagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(ImagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        ApiClass usersAPI = new ApiClass();

        Call<Image> responseBodyCall = usersAPI.calls().uploadImage(body);

        StrictModeClass.StrictMode();
        try {
            Response<Image> imageResponseResponse = responseBodyCall.execute();
            ImageName = imageResponseResponse.body().getFilename();

        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp() {
        User users = new User(email, password, username, ImageName);

        ApiClass usersAPI = new ApiClass();
        final Call<SignupRes> signUpCall = usersAPI.calls().register(users);

        signUpCall.enqueue(new Callback<SignupRes>() {
            @Override
            public void onResponse(Call<SignupRes> call, Response<SignupRes> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CameraActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                SignupRes signUpRes = response.body();
                token = signUpRes.getToken();
                Log.d("token", token);
                Intent intent = new Intent(CameraActivity.this, DescribeActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<SignupRes> call, Throwable t) {
                Toast.makeText(CameraActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
