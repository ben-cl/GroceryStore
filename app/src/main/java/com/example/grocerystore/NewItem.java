package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;

public class NewItem extends AppCompatActivity {


    private EditText nameField;
    private Button addItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final DatabaseHandler db = new DatabaseHandler(NewItem.this);


        nameField = findViewById(R.id.nameItemField);
        addItem = findViewById(R.id.addItem);


        //
        addItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                // Add Item

                //TODO add foreign storeId intent bundle?

                Intent intentSet = getIntent();

                //int storeId = Integer.valueOf(intentSet.getStringExtra("storeId"));

                Store store = (Store)intentSet.getSerializableExtra("store");


                Log.d("test", "onClick: adding item fk"+store.getId());

                //testing

                String name = nameField.getText().toString();

                db.addItem(new Item(store.getId(),name));


                // Launch back menu
                Intent intent = new Intent(NewItem.this, ItemList.class);
                intent.putExtra("store", store);
                startActivity(intent);


            }
        });


    }
}
