package com.abhishek.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products=new ArrayList<>();
    ArrayList<String>productNames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner productSpinner=findViewById(R.id.productSpinner);
        NumberPicker pickerQuantity=findViewById(R.id.pickerQuantity);
        RadioButton rbDelivery=findViewById(R.id.rbDelivery);
        RadioButton rbPickup=findViewById(R.id.rbPickup);
        TextView lblPrice=findViewById(R.id.lblPrice);
        TextView lblTotal=findViewById(R.id.lblTotal);
        ImageView imgProduct=findViewById(R.id.imgProduct);
        fillData();
        ArrayAdapter pickerAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,productNames);
        productSpinner.setAdapter(pickerAdapter);
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imgProduct.setImageResource(products.get(position).getImage());
                lblPrice.setText(products.get(position).getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillData() {
        products.add(new Product("Bar Table",360,R.drawable.img1));
        products.add(new Product("Dining Table",499,R.drawable.img2));
        products.add(new Product("Desktp",199,R.drawable.img3));
        products.add(new Product("Lounge Table",599,R.drawable.img4));
        for (Product p:products){
            productNames.add(p.getName());
        }
    }
}