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

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button signUp;
    EditText password,phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.signin);
        signUp=findViewById(R.id.signup);
        password=findViewById(R.id.phone);
        phoneNumber=findViewById(R.id.pass);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference tablePlayer=database.getReference("Player");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tablePlayer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phoneNumber.getText().toString()).exists()){
                            Player player=snapshot.child(phoneNumber.getText().toString()).getValue(Player.class);
                            if(player.getPassword().equals(password.getText().toString())){
                                Toast.makeText(MainActivity.this, "Sign in Succecful", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,FindPeople.class);
                                intent.putExtra("current",phoneNumber.getText().toString());
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Failed Sign in", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Does not exist in Database", Toast.LENGTH_SHORT).show();
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