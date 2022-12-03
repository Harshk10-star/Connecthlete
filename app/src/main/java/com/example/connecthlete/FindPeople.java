package com.example.connecthlete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindPeople extends Activity implements AdapterView.OnItemSelectedListener {
    private TextView txtHelloWorld;
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_people);
        Intent intent=getIntent();
        String user=intent.getStringExtra("current");
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.font_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button goDisplay=findViewById(R.id.finalSc);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Player");
        goDisplay.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                databaseReference.child(user).child("sport").setValue(item); //cahngeing sports assingment
               Intent intents=new Intent(FindPeople.this,Results.class);
               intents.putExtra("current",user);
               intents.putExtra("sport",item);
               startActivity(intents);
               finish();
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}