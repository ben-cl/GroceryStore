package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Store;

public class EditStore extends AppCompatActivity {

    private EditText nameEditField;
    private Button editStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store);

        final DatabaseHandler db = new DatabaseHandler(EditStore.this);


        nameEditField = findViewById(R.id.nameEditField);
        editStore = findViewById(R.id.editStore);


        //
        editStore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent intent = getIntent();

                Store store = (Store)intent.getSerializableExtra("store");


                // Edit Store
                String name = nameEditField.getText().toString();

                store.setName(name);


                //todo
                db.editStore(store);


                // Launch back menu
                Intent intentback = new Intent(EditStore.this, MainActivity.class);
                startActivity(intentback);


            }
        });
    }
}
