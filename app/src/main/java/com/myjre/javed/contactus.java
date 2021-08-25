package com.myjre.javed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class contactus extends AppCompatActivity {
    TextView phone,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.emailare);
        address=findViewById(R.id.schooladd);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+919794444292"));
                startActivity(intent);
            }
        });
    }
}
