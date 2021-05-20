package ru.samitin.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    String[]adresses={"samitin.e@gmail.com"};
    String subject="Order from Music Shop";
    String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent orderIntent=getIntent();
        String userName=orderIntent.getStringExtra("userName");

        String goodsName=orderIntent.getStringExtra("goodsName");
        int quantity=orderIntent.getIntExtra("quantity",0);
        double price=orderIntent.getDoubleExtra("price",0);
        double orderPrice=orderIntent.getDoubleExtra("orderPrice",0);
        emailText="Customer name:   "+userName+"\n"+
                "Goods name:  "+goodsName+"\n"+
                "quantity:    "+quantity+"\n"+
                "Price:       "+price+"\n"+
                "Order price: "+orderPrice;
        TextView orderTextView=findViewById(R.id.orderTextWiew);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {

        Intent intent=new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailTo"));
        intent.setType("*/*");
        intent.putExtra(intent.EXTRA_EMAIL,adresses);
        intent.putExtra(intent.EXTRA_SUBJECT,subject);
        intent.putExtra(intent.EXTRA_TEXT,emailText);
        if(intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
        else
            Toast.makeText(this,"ошибка",Toast.LENGTH_SHORT).show();
        Log.d("sumitOrder","Кнопка нажата");
    }
}