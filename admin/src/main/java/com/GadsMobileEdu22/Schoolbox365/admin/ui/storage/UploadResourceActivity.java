package com.GadsMobileEdu22.Schoolbox365.admin.ui.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.GadsMobileEdu22.Schoolbox365.admin.databinding.ActivityUploadResourceBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadResourceActivity extends AppCompatActivity {

    private static final String TAG = "UploadResourceActivity";
    private static final int CHOOSE_FILE_REQUEST_CODE = 123;
    private StorageReference mStorageRef;
    private Uri mUploadFile;
    private Uri mFileDownloadUrl;
    private ActivityUploadResourceBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUploadResourceBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickUploadFile();
            }
        });
    }

    private void pickUploadFile() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file to upload");
        startActivityForResult(chooseFile, CHOOSE_FILE_REQUEST_CODE);;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getPath(uri);
            mUploadFile = Uri.fromFile(new File(filePath));

            uploadFile();
        }
    }

    private void uploadFile() {
        StorageReference uploadRef =
                mStorageRef.child("resources/" + mUploadFile.getLastPathSegment());

        uploadRef.putFile(mUploadFile)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mFileDownloadUrl = taskSnapshot.getStorage().getDownloadUrl().getResult();
                        Log.d(TAG, "onSuccess: Download Url is: " + mFileDownloadUrl);
                        mBinding.textView3.setText("Download URL is: " + mFileDownloadUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.toString());
                        mBinding.textView3.setText("File upload failed. Reeason: " + e.toString());
                    }
                });
    }

    public String getPath(Uri uri) {

        String path = null;
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if(cursor == null){
            path = uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }
}