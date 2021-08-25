package com.myjre.javed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class uploadfac extends AppCompatActivity {
    ImageView imageView;
    EditText name,qual,post;
    private int request;
    private Button choose,upload;
    private ProgressDialog progressDialog;
    private StorageReference storageReference;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private Uri filepath;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_uploadfac);
        imageView=findViewById(R.id.upfacimag);
        name=findViewById(R.id.upfacname);
        qual=findViewById(R.id.upfacqual);
        post=findViewById(R.id.upfacpost);
        choose=findViewById(R.id.upfacchoose);
        upload=findViewById(R.id.upfacupload);
        storageReference= FirebaseStorage.getInstance().getReference("Facility");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Facility");
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefile();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();
                progressDialog = new ProgressDialog(uploadfac.this);
                progressDialog.setMessage("Please Wait Image Is uploading");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }
        });

    }

    private void uploadfile() {
        if (filepath != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(filepath));
            mUploadTask = fileReference.putFile(filepath).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(uploadfac.this,"Uploading Failed Check Your Internet ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setProgress((int)progress);
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadurl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    DatabaseReference newpost=mDatabaseRef.push();
                                    newpost.child("image").setValue(task.getResult().toString());
                                    newpost.child("name").setValue(name.getText().toString().trim());
                                    newpost.child("post").setValue(post.getText().toString().trim());
                                    newpost.child("qualificaion").setValue(qual.getText().toString().trim());
                                    Toast.makeText(uploadfac.this,"Uploaded Sucessful",Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri filepath) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(filepath));
    }

    private void choosefile() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request&&resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filepath=data.getData();
            imageView.setImageURI(filepath);
        }
    }
}