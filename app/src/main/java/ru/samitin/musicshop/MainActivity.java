package ru.samitin.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       createSpinner();
       createMap();
       userNameEditText=findViewById(R.id.editTextTextPersonName);

    }

    private void createSpinner(){
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList=new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drams");
        spinnerArrayList.add("keyboard");

        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    private  void createMap(){
        goodsMap=new HashMap();
        goodsMap.put("guitar",500.0);
        goodsMap.put("drams",1500.0);
        goodsMap.put("keyboard",1000.0);
    }

    public void increaseQuantity(View view) {
        TextView quantityTextView=findViewById(R.id.quantityTextView);

        switch (view.getId()){
            case R.id.btnPlus:
                quantity++;
                break;
            case R.id.btnMinus:
                if(quantity>0)
                   quantity--;
                break;
        }
        TextView priceTV=findViewById(R.id.priceTV);
        priceTV.setText(""+quantity*price+"$");
        quantityTextView.setText(""+quantity);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName=spinner.getSelectedItem().toString();
        price=(double)goodsMap.get(goodsName);
        TextView priceTV=findViewById(R.id.priceTV);
        priceTV.setText(""+quantity*price+"$");
        ImageView goodsImageView=findViewById(R.id.goodsImageView);
        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drams":
                goodsImageView.setImageResource(R.drawable.drams);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCart(View view) {
        Order order=new Order();
        order.userName=userNameEditText.getText().toString();
        order.goodsNeme=goodsName;
        order.quantity=quantity;
        order.price=price;
        order.orderPrice=quantity*price;
        Intent orderIntent=new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("userName",order.userName);
        orderIntent.putExtra("goodsName",order.goodsNeme);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("price",order.price);
        orderIntent.putExtra("orderPrice",order.orderPrice);
        startActivity(orderIntent);
    }
}