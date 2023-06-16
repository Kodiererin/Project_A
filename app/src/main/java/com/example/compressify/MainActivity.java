package com.example.compressify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity  {

    static int PICK_FILE_REQUEST_CODE=1;
    TextView showText;
    Button Compress;
    Button DeCompress;

    static String FileLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Creating the Constraints
        showText = findViewById(R.id.showText);
        Compress = findViewById(R.id.Compress);
        DeCompress = findViewById(R.id.DeCompress);

        Compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileManager();
            }
        });

        DeCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FileLocation!=null) {
                    Log.d("FileLocation",""+FileLocation);
                    openFile(FileLocation);
                }else{
                    Log.d("FileLocation","Emoty File");
                }
            }
        });
    }

    private void openFile(String filePath) {
        Uri uri = Uri.parse(filePath);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, getMimeType(filePath));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            // Handle exception if no activity can handle the file
            Toast.makeText(this, "No app found to open the file", Toast.LENGTH_SHORT).show();
        }
    }

    private String getMimeType(String url) {
        String mimeType = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        }
        return mimeType;
    }

    private void openFileManager(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(Intent.createChooser(intent,"Select File"),PICK_FILE_REQUEST_CODE);
    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri fileUri = data.getData();
                String filePath = getFileLocationFromUri(fileUri);
                if (filePath != null) {
                    // Use the filePath as needed
                    FileLocation = filePath;
                    Log.d("FileLocationIs", "File Location: " + filePath);
                } else {
                    Log.e("FileLocationIs", "Unable to retrieve file location");
                }
            }
        }

    }
    private String getFileLocationFromUri(Uri uri) {
        String fileLocation = null;
        if (uri != null) {
            fileLocation = uri.getPath();
        }
        return fileLocation;
    }
}