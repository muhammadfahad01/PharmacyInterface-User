package com.example.pharmacyinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    LinearLayout linearLayoutShop,linearLayoutProduct;
    ImageView imageItemShop;
    TextView tvItemNameShop;
    Button searchName,searchFormula;

    //shop
    int[] imagesShop = {R.drawable.pills,R.drawable.doctor,R.drawable.medicine2,R.drawable.syringe,R.drawable.prescription,R.drawable.antibiotic};
    String[] nameItemsShop = {"Medicines","Consultation","Medical", "Equipment","Health","Omega 3","Vitamins"};
    //Product
    int[] imagesProduct = {R.drawable.pills,R.drawable.tablet,R.drawable.medicine2,R.drawable.pills2};
    String[] namesItemsProduct = {"Multi Vitamins","Pain Killers","Omega 3","Capsule"};
    String[] pillsItemsProduct = {"90 Tabs","180 Tabs","132 Tabs","60 Capsule"};
    String[] finalPriceProduct = {"Rs.217","Rs.342","Rs.165","Rs.135"};
    String[] priceProduct = {"Rs.287","Rs.392","Rs.185","Rs.165"};
    TextInputLayout searchNametextInput,searchFormulaTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        linearLayoutShop = findViewById(R.id.linear_layout_shop);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        linearLayoutProduct = findViewById(R.id.linear_layout_products);
        searchName = findViewById(R.id.search_name);
        searchNametextInput=findViewById(R.id.text_input_layout_search);
        searchFormulaTextInput=findViewById(R.id.text_input_layout_search_by_formula);
        searchFormula=findViewById(R.id.search_formula);
        searchFormula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=searchFormulaTextInput.getEditText().getText().toString();
                Log.d("abcde",s);
                if (s==null){
                    s="";
                }
                startActivity(new Intent(MainActivity.this, SearchMed.class)
                        .putExtra("SEARCH_STRING",s).putExtra("IS_FORMULA",true));

            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });

        searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=searchNametextInput.getEditText().getText().toString();
                Log.d("abcde",s);
                if (s==null){
                    s="";
                }
                startActivity(new Intent(MainActivity.this, SearchMed.class)
                        .putExtra("SEARCH_STRING",s).putExtra("IS_FORMULA",false));
            }
        });
        // loadShop();
        loadProduct();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.homeB) {
                            findViewById(R.id.scroll_view_menu).setVisibility(View.VISIBLE);
                            findViewById(R.id.orders_view).setVisibility(View.GONE);
                            findViewById(R.id.cart_view).setVisibility(View.GONE);
                            findViewById(R.id.profile_view).setVisibility(View.GONE);
                            return true;
                        }
                        if (item.getItemId() == R.id.order) {

                            findViewById(R.id.scroll_view_menu).setVisibility(View.GONE);
                            findViewById(R.id.orders_view).setVisibility(View.VISIBLE);
                            findViewById(R.id.cart_view).setVisibility(View.GONE);
                            findViewById(R.id.profile_view).setVisibility(View.GONE);
                            return true;


                        }
                        if (item.getItemId() == R.id.cart) {


                            findViewById(R.id.scroll_view_menu).setVisibility(View.GONE);
                            findViewById(R.id.orders_view).setVisibility(View.GONE);
                            findViewById(R.id.cart_view).setVisibility(View.VISIBLE);
                            findViewById(R.id.profile_view).setVisibility(View.GONE);
                            return true;

                        }
                        if (item.getItemId() == R.id.profile) {

                            findViewById(R.id.scroll_view_menu).setVisibility(View.GONE);
                            findViewById(R.id.orders_view).setVisibility(View.GONE);
                            findViewById(R.id.cart_view).setVisibility(View.GONE);
                            findViewById(R.id.profile_view).setVisibility(View.VISIBLE);
                            return true;

                        }

                        return true;

                    }
                });

    }


    public void loadProduct(){
        LayoutInflater inflater = LayoutInflater.from(this);
        int i;
        for (i=0;i<imagesProduct.length;i++){

            View view = inflater.inflate(R.layout.item_product,linearLayoutProduct, false);
            ImageView imageProduct = view.findViewById(R.id.image_product);
            TextView tvName = view.findViewById(R.id.tv_name_product);
            TextView tvPills = view.findViewById(R.id.tv_pills_product);
            TextView tvFinalPrice = view.findViewById(R.id.tv_final_price_product);
            TextView tvPrice = view.findViewById(R.id.tv_price_product);
            imageProduct.setImageResource(imagesProduct[i]);
            tvName.setText(namesItemsProduct[i]);
            tvPills.setText(pillsItemsProduct[i]);
            tvFinalPrice.setText(finalPriceProduct[i]);
            tvPrice.setText(priceProduct[i]);

            linearLayoutProduct.addView(view);

        }
    }

    public void loadShop(){
        LayoutInflater inflater = LayoutInflater.from(this);
        int i;
        for(i=0;i<imagesShop.length;i++){
            View view = inflater.inflate(R.layout.item_shop,linearLayoutShop, false);
            imageItemShop = view.findViewById(R.id.image_item_shop);
            tvItemNameShop = view.findViewById(R.id.tv_item_name_shop);
            imageItemShop.setImageResource(imagesShop[i]);
            tvItemNameShop.setText(nameItemsShop[i]);
            final int aux = i;
            imageItemShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String itemSelection = nameItemsShop[aux];
                    Toast.makeText(getApplicationContext(),"Item"+itemSelection,Toast.LENGTH_SHORT).show();
                    Intent shop = new Intent(MainActivity.this,Shop.class);

                    startActivity(shop);
                }
            });

            linearLayoutShop.addView(view);
        }
    }
}