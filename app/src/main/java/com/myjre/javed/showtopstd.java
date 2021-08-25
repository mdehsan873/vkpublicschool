package com.myjre.javed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showtopstd extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private topadapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<topupload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtopstd);
        mRecyclerView = findViewById(R.id.stdrecyler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<topupload>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("student");
        mAdapter = new topadapter(showtopstd.this,mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    topupload upload = postSnapshot.getValue(topupload.class);
                    mUploads.add(upload);
                }
                mAdapter = new topadapter(showtopstd.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(showtopstd.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}