package com.example.crud;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee {
    //database add object
    private DatabaseReference databaseRefence;

    public DAOEmployee() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseRefence = db.getReference(Employee.class.getSimpleName());
    }
    public Task<Void> add(Employee emp){
        return databaseRefence.push().setValue(emp);
    }
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseRefence.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseRefence.child(key).removeValue();
    }

    public Query get(){
        return databaseRefence.orderByKey();
    }
    public Query get(String key){
        if(key == null){
            return databaseRefence.orderByKey().limitToFirst(8);
        }
        return databaseRefence.orderByKey().startAfter(key).limitToFirst(8);
    }
    public Query getPosition(){

        return databaseRefence.orderByChild("position").equalTo("manager");
    }
}

