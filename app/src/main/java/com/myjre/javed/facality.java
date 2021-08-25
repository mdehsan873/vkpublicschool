package com.myjre.javed;

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

public class facality extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private facadapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<upfac> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facality);
        mRecyclerView = findViewById(R.id.facrecyler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<upfac>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Facility");
        mAdapter = new facadapter(facality.this,mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    upfac upload = postSnapshot.getValue(upfac.class);
                    mUploads.add(upload);
                }
                mAdapter = new facadapter(facality.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(facality.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}