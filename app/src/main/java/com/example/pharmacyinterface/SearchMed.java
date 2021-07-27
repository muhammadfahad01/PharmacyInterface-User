package com.example.pharmacyinterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SearchMed extends AppCompatActivity {

    String searchString;
    boolean isFormula;
    FirebaseFirestore db;
    LinearLayout layout;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_med);
        searchString=getIntent().getStringExtra("SEARCH_STRING");
        isFormula=getIntent().getBooleanExtra("IS_FORMULA",false);
        layout = findViewById(R.id.inflateLinearLayout);

        Log.d("abcde",searchString+isFormula);
        //search by name, from firebase and display every matching component
        db=FirebaseFirestore.getInstance();
        if (isFormula){
            searchByFormula();
        }
        else {
            searchByName();
        }

    }

    void searchByFormula() {

        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getString("product_formula")!=null) {


                                    if (document.getString("product_formula").contains(searchString)) {

                                        LayoutInflater l = LayoutInflater.from(SearchMed.this);
                                        View v = l.inflate(R.layout.med_item, null);
                                        layout.addView(v);
                                        TextView nameTV = findViewById(R.id.nameMed);
                                        TextView priceTV = findViewById(R.id.priceMed);
                                        //Button addItemBT = findViewById(R.id.add_button);
                                        nameTV.setText(document.getString("product_name"));
                                        priceTV.setText(document.getString("product_price"));
                                        nameTV.setId(100 + i);
                                        priceTV.setId(200 + i);
                                        i++;

                                    }

                                }
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


    }
    void searchByName(){

        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("product_name")!=null) {


                                    if (document.getString("product_name").contains(searchString)) {

                                        LayoutInflater l = LayoutInflater.from(SearchMed.this);
                                        View v = l.inflate(R.layout.med_item, null);
                                        layout.addView(v);
                                        TextView nameTV = findViewById(R.id.nameMed);
                                        TextView priceTV = findViewById(R.id.priceMed);
                                        //Button addItemBT = findViewById(R.id.add_button);
                                        nameTV.setText(document.getString("product_name"));
                                        priceTV.setText(document.getString("product_price"));
                                        nameTV.setId(100 + i);
                                        priceTV.setId(200 + i);
                                        i++;

                                    }
                                }


                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


    }
}