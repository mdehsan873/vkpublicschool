package com.myjre.javed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class adminpanel extends AppCompatActivity {
    Button upimage,upnotifications,addfac,addtopstd,addasg,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        logout=findViewById(R.id.logout);
        upimage=findViewById(R.id.upimage);
        upnotifications=findViewById(R.id.addnoti);
        addtopstd=findViewById(R.id.addtpstd);
        addfac=findViewById(R.id.addfac);
        addasg=findViewById(R.id.addas);
        upimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminpanel.this,upload_image.class);
                startActivity(intent);
            }
        });
        addtopstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminpanel.this,topperStudent.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(adminpanel.this,MainActivity.class);
                startActivity(intent);
            }
        });
        addfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminpanel.this,uploadfac.class);
                startActivity(intent);
            }
        });
        addasg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://chat.whatsapp.com/BEoHIFAkhz0GyYwqbaZrU5"));
                startActivity(intent);
            }
        });
        upnotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminpanel.this,upload_notifocation.class);
                startActivity(intent);
            }
        });
    }
}
