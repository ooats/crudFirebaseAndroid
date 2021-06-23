package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText nameText, positionText;
    Button submitBtn, openBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.editTextName);
        positionText = findViewById(R.id.editTextPosition);
        submitBtn = findViewById(R.id.buttonSubmit);

        openBtn =findViewById(R.id.buttonOpen);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Functions.class));
            }
        });
        DAOEmployee dao = new DAOEmployee();
        Employee emp_edit = (Employee)getIntent().getSerializableExtra("Edit");
        if(emp_edit != null){
            submitBtn.setText("Update");
            nameText.setText(emp_edit.getName());
            positionText.setText(emp_edit.getPosition());
            openBtn.setVisibility(View.GONE);
        }else{
            submitBtn.setText("Submit");

        }


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String position = positionText.getText().toString();

                Employee emp = new Employee(name, position);
                if (emp_edit == null){
                    dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>(){
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity.this, "Record is inserted", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener( e -> Toast.makeText(MainActivity.this, "Failure: "+e.getMessage(), Toast.LENGTH_LONG).show());
                    nameText.getText().clear();
                    positionText.getText().clear();
                }else{
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("name", name);
                    hashMap.put("position", position);
                    dao.update( emp_edit.getKey(),hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(MainActivity.this, "Record is updated", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });






    }

//    @Override
//    public void onClick(View v) {
//        String name = nameText.getText().toString();
//        String position = positionText.getText().toString();
//        DAOEmployee dao = new DAOEmployee();
//        Employee emp_edit = (Employee)getIntent().getSerializableExtra("Edit");
////        if(emp_edit != null){
////            submitBtn.setText("Update");
////            nameText.setText(emp_edit.getName());
////            positionText.setText(emp_edit.getPosition());
////            openBtn.setVisibility(View.GONE);
////        }else{
////            submitBtn.setText("Submit");
////            openBtn.setVisibility(View.VISIBLE);
////        }
//
//        switch (v.getId()){
//            case R.id.buttonSubmit:
//
//                Employee emp = new Employee(name, position);
//                if (emp_edit == null){
//                    dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>(){
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(MainActivity.this, "Record is inserted", Toast.LENGTH_LONG).show();
//                        }
//                    }).addOnFailureListener( e -> Toast.makeText(MainActivity.this, "Failure: "+e.getMessage(), Toast.LENGTH_LONG).show());
//                    nameText.getText().clear();
//                    positionText.getText().clear();
//                }else{
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    hashMap.put("name", name);
//                    hashMap.put("position", position);
//                    dao.update( emp_edit.getKey(),hashMap).addOnSuccessListener(new OnSuccessListener() {
//                        @Override
//                        public void onSuccess(Object o) {
//                            Toast.makeText(MainActivity.this, "Record is updated", Toast.LENGTH_LONG).show();
//                            finish();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                 }
//
//                break;
//
//            case R.id.buttonOpen:
//                startActivity(new Intent(MainActivity.this, RVActivity.class));
//                break;
//        }
//
//
//
//
//    }
}