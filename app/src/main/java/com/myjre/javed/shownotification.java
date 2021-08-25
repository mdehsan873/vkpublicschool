package com.myjre.javed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class shownotification extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    notiadapter notiadapter;
    List<notidata> notidata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownotification);
        recyclerView=findViewById(R.id.notirecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notidata=new ArrayList<notidata>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Notification");
        notiadapter=new notiadapter(shownotification.this,notidata);
        recyclerView.setAdapter(notiadapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    com.myjre.javed.notidata notidata1=postSnapshot.getValue(com.myjre.javed.notidata.class);
                    notidata.add(notidata1);
                }
                notiadapter=new notiadapter(shownotification.this,notidata);
                recyclerView.setAdapter(notiadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(shownotification.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}