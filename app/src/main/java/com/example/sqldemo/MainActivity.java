package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // refernces to buttons and other controls on the layout
    Button btn_add, btn_viewAll;
    EditText et_name,et_age;

    ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       btn_add=findViewById(R.id.btn_add);
       btn_viewAll=findViewById(R.id.btn_viewAll);
       et_age=findViewById(R.id.et_age);
       et_name=findViewById(R.id.et_name);

       lv_customerList=findViewById(R.id.lv_customerList);
         dataBaseHelper=new DataBaseHelper(MainActivity.this);
        customerArrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter((customerArrayAdapter));


        //button listeners for the add and view all button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
CustomerModel customerModel;
                try {
                     customerModel=new CustomerModel(-1, et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()));
                    Toast.makeText(MainActivity.this,customerModel.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error creating customer",Toast.LENGTH_SHORT).show();
                    customerModel=new CustomerModel(-1, "error",0);
                    
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "Success= "+ success, Toast.LENGTH_SHORT).show();
                customerArrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter((customerArrayAdapter));




            }
        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                List<CustomerModel> everyone= dataBaseHelper.getEveryone();
                 customerArrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
lv_customerList.setAdapter((customerArrayAdapter));
                //Toast.makeText(MainActivity.this,everyone.toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }}

