package com.example.exo_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView MrecyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MrecyclerView = findViewById(R.id.recyclerview);
        MrecyclerView.setHasFixedSize(true);
        MrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("video");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<member,ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<member, ViewHolder>(member.class,
                        R.layout.row,
                        ViewHolder.class,
                        reference) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, member member, int i) {

                        viewHolder.setVideo(getApplication(), member.getTitle(), member.getUrl());

                    }
                };
        MrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}