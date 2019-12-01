package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.grocerystore.model.Store;

public class StoreRow extends AppCompatActivity {


    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_row);

//        removeButton = findViewById(R.id.removeButton);
//
        removeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                // Call remove method

                Log.d("testing", "onClick:  remove activity");

//                // Launch back menu
//                Intent intent = new Intent(NewStore.this, MainActivity.class);
//                startActivity(intent);


            }
        });

    }

}
