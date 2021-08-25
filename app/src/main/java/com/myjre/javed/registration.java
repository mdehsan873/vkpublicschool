package com.myjre.javed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {
    EditText name,fname,mname,phone,address,adharnum,clas,religion;
    Button submit;
    String number,anumber,names,fnames,mnames,addresss,clase,religions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.name);
        fname=findViewById(R.id.fathername);
        mname=findViewById(R.id.mothername);
        phone=findViewById(R.id.Phonenum);
        address=findViewById(R.id.address);
        adharnum=findViewById(R.id.adhar);
        clas=findViewById(R.id.clas);
        religion=findViewById(R.id.religion);
        submit=findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names=name.getText().toString().trim();
                fnames=fname.getText().toString().trim();
                mnames=mname.getText().toString().trim();
                addresss=address.getText().toString();
                clase=clas.getText().toString();
                religions=religion.getText().toString();
                number=phone.getText().toString();
                anumber=adharnum.getText().toString();

                    String msg="name = "+ names + "\n" +"Father Name =" +fnames +"\n"+ "Mother Name= "+mnames +"\n"+ "Address="+addresss+ "\n" +"Class ="+clase+"\n"+ " Religions ="+religions+ "\n" +"Phone Number ="+number+"\n"+"Adhar Number ="+anumber;
                    Toast.makeText(registration.this,"PLEASE PAY 50 RUPEES AT SCHOOL",Toast.LENGTH_SHORT).show();
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.whatsapp.com/send?phone=+919794444292&text="+msg));

                    startActivity(sendIntent);

            }
        });
    }
}
