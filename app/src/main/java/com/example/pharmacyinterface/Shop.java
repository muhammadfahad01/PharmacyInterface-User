package com.example.pharmacyinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Shop extends AppCompatActivity implements View.OnClickListener{

    MaterialCardView cardShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        cardShop = findViewById(R.id.card_shop);
        cardShop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_shop:
                shopDetail();
                break;
        }
    }

    public void shopDetail(){
        Intent shopDetail = new Intent(getApplicationContext(),ShopDetail.class);
        startActivity(shopDetail);
    }
}