package com.myjre.javed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView cont,aboutus,registraion,gala,topstu,acadmiccal,fac,assign;
    Button notifi,adlogin;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont=findViewById(R.id.contact);
        auth=FirebaseAuth.getInstance();
        aboutus=findViewById(R.id.about);
        registraion=findViewById(R.id.regi);
        gala=findViewById(R.id.gallary);
        topstu=findViewById(R.id.topstd);
        acadmiccal=findViewById(R.id.calend);
        notifi=findViewById(R.id.noti);
        adlogin=findViewById(R.id.adlogin);
        fac=findViewById(R.id.faci);
        assign=findViewById(R.id.imageView18);
        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,facality.class);
                startActivity(intent);
            }
        });
        registraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,registration.class);
                startActivity(intent);
            }
        });
    aboutus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this, com.myjre.javed.aboutus.class);
            startActivity(intent);
        }
    });
    adlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(auth.getCurrentUser() == null){
                Intent intent = new Intent(MainActivity.this,adminlogin.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent=new Intent(MainActivity.this,adminpanel.class);
                startActivity(intent);
            }
        }
    });
    cont.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,contactus.class);
            startActivity(intent);
        }
    });
    gala.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,galary.class);
            startActivity(intent);
        }
    });
    topstu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,showtopstd.class);
            startActivity(intent);
        }
    });
    assign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://chat.whatsapp.com/BEoHIFAkhz0GyYwqbaZrU5"));
            startActivity(intent);
        }
    });
    notifi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,shownotification.class);
            startActivity(intent);
        }
    });
    }
}
