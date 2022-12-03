package com.example.connecthlete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    EditText phoneNumber;
    EditText password;
    EditText unis;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp=findViewById(R.id.si);
        phoneNumber=findViewById(R.id.phoneN);
        password=findViewById(R.id.pwdid);
        unis= findViewById(R.id.uni);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference tablePlayer=database.getReference("Player");



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tablePlayer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phoneNumber.getText().toString()).exists()){
                            Toast.makeText(SignUp.this, "Already Exists", Toast.LENGTH_SHORT).show();
                        }else{
                            Player player=new Player(password.getText().toString(),"",unis.getText().toString());
                            tablePlayer.child(phoneNumber.getText().toString()).setValue(player);
                            Toast.makeText(SignUp.this, "Sign Up Succesfull", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


}