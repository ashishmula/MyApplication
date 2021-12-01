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
    RadioButton rbDelivery,rbPickup;
    final int[] totalPrice={0};
    TextView lblTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner productSpinner=findViewById(R.id.productSpinner);
        NumberPicker pickerQuantity=findViewById(R.id.pickerQuantity);
        rbDelivery=findViewById(R.id.rbDelivery);
        rbPickup=findViewById(R.id.rbPickup);
        TextView lblPrice=findViewById(R.id.lblPrice);
        lblTotal=findViewById(R.id.lblTotal);
        final int[] price = {0};
        ImageView imgProduct=findViewById(R.id.imgProduct);
        fillData();
        ArrayAdapter pickerAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,productNames);
        productSpinner.setAdapter(pickerAdapter);
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imgProduct.setImageResource(products.get(position).getImage());
                lblPrice.setText(Integer.toString(products.get(position).getPrice()*pickerQuantity.getValue()));
                price[0] =products.get(position).getPrice();
                totalPrice[0]=products.get(position).getPrice()*pickerQuantity.getValue();
                handleRb();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pickerQuantity.setMinValue(1);
        pickerQuantity.setMaxValue(10);
        pickerQuantity.setValue(1);
        rbDelivery.setOnClickListener(new RadioButtonEvent());
        rbPickup.setOnClickListener(new RadioButtonEvent());
        pickerQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
               // lblPrice.setText(Integer.toString(newVal*price[0]));
                totalPrice[0]=newVal*price[0];
                handleRb();
            }
        });

    }
    private void handleRb(){
        if(rbDelivery.isChecked()){
            if(totalPrice[0]<1000){
                lblTotal.setText(Integer.toString((int) (totalPrice[0]+(totalPrice[0]*0.1))));
            }
            else{
                lblTotal.setText(Integer.toString(totalPrice[0]));
            }
        }
        else if(rbPickup.isChecked()){
            lblTotal.setText(Integer.toString(totalPrice[0]));
        }
    }
    private void fillData() {
        products.add(new Product("coffee Table",360,R.drawable.img1));
        products.add(new Product("Bar Table",499,R.drawable.img2));
        products.add(new Product("outdoor Table",199,R.drawable.img3));
        products.add(new Product("Dinning Table",599,R.drawable.img4));
        for (Product p:products){
            productNames.add(p.getName());
        }
    }
    private class RadioButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rbDelivery:
                    rbPickup.setChecked(false);
                    if(totalPrice[0]<1000){
                        lblTotal.setText(Integer.toString((int) (totalPrice[0]+(totalPrice[0]*0.1))));
                    }
                    else{
                        lblTotal.setText(Integer.toString(totalPrice[0]));
                    }
                    break;
                case R.id.rbPickup:
                    rbDelivery.setChecked(false);
                    lblTotal.setText(Integer.toString(totalPrice[0]));
            }
        }
    }
}