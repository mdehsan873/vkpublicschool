package com.myjre.javed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminlogin extends AppCompatActivity {

    Button adminloged;
    EditText emailid,password;
    String email,pass;
    FirebaseAuth auth;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        adminloged=findViewById(R.id.logged);
        emailid=findViewById(R.id.email);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();

        adminloged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logining();
            }
        });

    }


    private void logining() {
        email = emailid.getText().toString().trim();
        pass = password.getText().toString().trim();
            progress = new ProgressDialog(adminlogin.this);
            progress.setMessage("Please Wait For Login");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progress.dismiss();
                        Intent intent = new Intent(adminlogin.this, adminpanel.class);
                        startActivity(intent);
                    } else {
                        progress.dismiss();
                        Toast.makeText(adminlogin.this, "Check email and password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
