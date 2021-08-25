package com.myjre.javed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class upload_notifocation extends AppCompatActivity {
    EditText editText;
    Button upload;
    String text;
    DatabaseReference firebaseDatabase;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notifocation);
        editText=findViewById(R.id.upnotitext);
        upload=findViewById(R.id.notiupbtn);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Notification");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text =editText.getText().toString();
                progressDialog=new ProgressDialog(upload_notifocation.this);
                progressDialog.setMessage("Uploading Please Wait");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                DatabaseReference newpost=firebaseDatabase.push();
                newpost.child("notification").setValue(text).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(upload_notifocation.this,"Uploaded Sucessful",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(upload_notifocation.this,"Uploading Failed Check Your Internet",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }
}