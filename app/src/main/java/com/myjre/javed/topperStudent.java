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

public class topperStudent extends AppCompatActivity {
    ImageView stpic;
    EditText stname,clas;
    ProgressDialog prdia;
    int request=1;
    private StorageReference storageReference;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private Button stdchoose,stdupload;
    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topper_student);
        stname=findViewById(R.id.stdname);
        clas=findViewById(R.id.stdclass);
        stpic=findViewById(R.id.stdimg);
        stdchoose=findViewById(R.id.choosestd);
        stdupload=findViewById(R.id.uploadstd);
        storageReference= FirebaseStorage.getInstance().getReference("Student");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("student");
        stdchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefile();
            }
        });
        stdupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uplaodfile();
                prdia = new ProgressDialog(topperStudent.this);
                prdia.setMessage("Please Wait Image Is uploading");
                prdia.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                prdia.setIndeterminate(true);
                prdia.show();
            }
        });
    }

    private void uplaodfile() {
        if (filepath != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(filepath));
            mUploadTask = fileReference.putFile(filepath).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(topperStudent.this,"Uploading Failed Check Your Internet ", Toast.LENGTH_SHORT).show();
                    prdia.dismiss();
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadurl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    DatabaseReference newpost=mDatabaseRef.push();
                                    newpost.child("name").setValue(stname.getText().toString().trim());
                                    newpost.child("Classes").setValue(clas.getText().toString().trim());
                                    newpost.child("image").setValue(task.getResult().toString());
                                    Toast.makeText(topperStudent.this,"Uploaded Sucessful",Toast.LENGTH_SHORT).show();
                                    prdia.dismiss();
                                }
                            });
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
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
            stpic.setImageURI(filepath);
        }
    }
}
